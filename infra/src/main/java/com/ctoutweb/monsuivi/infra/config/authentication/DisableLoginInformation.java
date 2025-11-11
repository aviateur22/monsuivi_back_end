package com.ctoutweb.monsuivi.infra.config.authentication;

import com.ctoutweb.monsuivi.infra.util.DateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class DisableLoginInformation {

    @Value("${zone.id}")
    String zoneId;

    /**
     * Vérification si un process de login peut avoir lieu
     *
     * @param actualLoginTime L'heure de connsion de l'utilsateur
     * @param utcDelayLoginUntil L'heure d'autorisation de la prochaine connexion
     *
     * @return True si le login peut avoir lieu
     */
    public boolean isLoginEnable(ZonedDateTime actualLoginTime, ZonedDateTime utcDelayLoginUntil) {
        return actualLoginTime.isAfter(getLoginTimeWithIntegratedZoneId(utcDelayLoginUntil));
    }

    /**
     * Renvoie l'heure initialement en UTC avec l'integration de la zoneId
     *
     * @param utcDelayLoginUntil L'heure d'autorisation de la prochaine connexion
     *
     * @return L'heure de connexion avec la bonne zoneid
     */
    public ZonedDateTime getLoginTimeWithIntegratedZoneId(ZonedDateTime utcDelayLoginUntil) {
        return DateUtil.uctToZonedDateTime(ZoneId.of(zoneId), utcDelayLoginUntil);
    }
}
