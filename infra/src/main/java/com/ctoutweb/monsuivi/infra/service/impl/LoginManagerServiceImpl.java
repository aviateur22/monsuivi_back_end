package com.ctoutweb.monsuivi.infra.service.impl;

import com.ctoutweb.monsuivi.infra.config.authentication.DisableLoginInformation;
import com.ctoutweb.monsuivi.infra.config.authentication.LoginStatus;
import com.ctoutweb.monsuivi.infra.repository.IDisableLoginRepository;
import com.ctoutweb.monsuivi.infra.repository.ILoginRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.DisableLoginEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.LoginEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import com.ctoutweb.monsuivi.infra.service.ICryptoService;
import com.ctoutweb.monsuivi.infra.service.LoginManagerService;
import com.ctoutweb.monsuivi.infra.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.ctoutweb.monsuivi.infra.constant.LoginConstant.*;

@Service
public class LoginManagerServiceImpl implements LoginManagerService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final ICryptoService cryptoService;
    private final ILoginRepository loginRepository;
    private final IDisableLoginRepository disableLoginRepository;
    private final DisableLoginInformation disableLoginInformation;

    @Value("${zone.id}")
    String zoneId;

    public LoginManagerServiceImpl(
            ICryptoService cryptoService,
            ILoginRepository loginRepository,
            IDisableLoginRepository disableLoginRepository, DisableLoginInformation disableLoginInformation) {
        this.cryptoService = cryptoService;
        this.loginRepository = loginRepository;
        this.disableLoginRepository = disableLoginRepository;
        this.disableLoginInformation = disableLoginInformation;
    }

    @Override
    public LoginStatus isUserLoginAuthorized(long userId) {
        ZonedDateTime actualLoginTime = LocalDateTime.now().atZone(ZoneId.of(zoneId));

        return disableLoginRepository.findFirstBySellerId(userId)
                .map(disableLogin -> {
                    ZonedDateTime loginUnavailableUntilUtc = disableLogin.getDelayLoginUntil();
                    ZonedDateTime loginUnavailableUntilZoned = disableLoginInformation.getLoginTimeWithIntegratedZoneId(loginUnavailableUntilUtc);

                    if(disableLoginInformation.isLoginEnable(actualLoginTime, loginUnavailableUntilUtc)) {
                        disableLoginRepository.delete(disableLogin);
                        return new LoginStatus(true, null);
                    }


                    return new LoginStatus(
                            false,
                            String.format("Vous pourrez vous reconnecter à partir du %s", DateUtil.toDateHour(loginUnavailableUntilZoned))
                    );
                })
                .orElse(new LoginStatus(true, null));
    }

    @Override
    public void updateUserLoginInformation(long userId, boolean isAuthenticationValid) {
        final ZonedDateTime loginTime = LocalDateTime.now().atZone(ZoneId.of(zoneId));
        final  boolean HAS_TO_BE_ChECK = true;
        final SellerEntity userLogin = new SellerEntity(userId);

        LoginEntity loginInformation = new LoginEntity(HAS_TO_BE_ChECK, isAuthenticationValid, loginTime, userLogin);
        loginRepository.save(loginInformation);
    }

    @Override
    public LoginStatus manageUserLogin(long userId, String plainTextPasswordSendByUser, String hashPassword) {
        boolean isAuthenticationValid = cryptoService.isHashValid(plainTextPasswordSendByUser,hashPassword);

        updateUserLoginInformation(userId, isAuthenticationValid);

        if(isAuthenticationValid)
            return new LoginStatus(true, null);

        String failedLoginMessage = manageFailedLogin(userId);
        return new LoginStatus(false, failedLoginMessage);
    }


    public String manageFailedLogin(long userId) {

        // List des dernieres connexions du client
        List<LoginEntity> lastUserLoginList = loginRepository
                .findBySellerOrderByLoginAtDesc(new SellerEntity(userId))
                .stream()
                .limit(USER_LOGIN_COUNT)
                .toList();

        long remainingLoginAttempt = remainingLoginAttempts(lastUserLoginList);

        if(remainingLoginAttempt > 0)
            return String.format("Login ou mot de passe invalide. Il vous reste %s tentative de connexion", remainingLoginAttempt);

        resetUserConnexionStatus(lastUserLoginList);
        return disableLoginForPeriod(userId);
    }

    private long remainingLoginAttempts(List<LoginEntity> lastUserLoginList) {
        // Récupérartion du nombre de connexion invalide
        long loginAttemptErrorCount = lastUserLoginList
                .stream()
                .filter(login-> !login.getIsLoginSuccess() && login.getHasToBeCheck())
                .count();

        // Calcul du nombre de connexion restante en cas d'erreur
        return FAILED_LOGIN_ATTEMPT_AVAILABLE - loginAttemptErrorCount;
    }


    /**
     * Désecative la connexion utilisateur et renvoie un message associé a ce nouvel etat
     *
     * @param userId L'identifiant de l'utilisateur qui se connecte
     *
     * @return Le message associé a la désactivation
     */
    public String disableLoginForPeriod(long userId) {
        // Calcul heure de déblocage de connexion
        ZonedDateTime loginDisableUntil = LocalDateTime.now()
                .plusMinutes(LOGIN_DELAY_IN_MINUTE)
                .truncatedTo(ChronoUnit.MINUTES)
                .atZone(ZoneId.of(zoneId));

        DisableLoginEntity disableLoginToSave = new DisableLoginEntity(new SellerEntity(userId), loginDisableUntil);

        disableLoginRepository
            .findFirstBySellerId(userId)
            .ifPresentOrElse(disableLogin -> {
                disableLoginRepository.delete(disableLogin);
                disableLoginRepository.save(disableLoginToSave);
                }, () -> {
                disableLoginRepository.save(disableLoginToSave);
            });

        return String.format("Login ou mot de passe invalide. Vous pourrez essayer de vous reconnecter à partir du %s", DateUtil.toDateHour(loginDisableUntil));
    }

    /**
     * Mise a jour des status de control des login d'un user
     *
     * @param lastUserLoginList Liste des dernieres connexions d'un utilsateur a mettre à jour
     */
    public void resetUserConnexionStatus(List<LoginEntity> lastUserLoginList) {

        lastUserLoginList.forEach(userLogin -> userLogin.setHasToBeCheck(false));
        loginRepository.saveAll(lastUserLoginList);
    }
}
