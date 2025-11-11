package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.config.authentication.LoginStatus;

public interface LoginManagerService {

    /**
     * Vérification qu'un utilisateur puisse se connecter
     *
     * @param userId L'identifiant de l'utilisateur que se connecte
     *
     * @return True si l'utilisateur peut se connecter
     */
    LoginStatus isUserLoginAuthorized(long userId);

    /**
     * Gestion de la connexion d'un utilisateur qui est en cours de connexion
     *
     * @param userId L'identifiant de l'utilisateur que se connecte
     * @param plainTextPasswordSendByUser Le mot de passe en claire fournis par l'utilsateur lors de la locannexion
     * @param hashPassword Le mot de passe hashé récupéré en base
     */
    LoginStatus manageUserLogin(long userId, String plainTextPasswordSendByUser, String hashPassword);

    /**
     * Mise a jour des informations de connexion d'un utilisateur.
     * Cette methode est appelé uniquement si l'utilisateur n'est pas restreint à la connexion
     *
     * @param userId L'identifiant de l'utilisateur
     *
     * @param isAuthenticationValid Validité de l'authentification de l'utilisateur qui se connecte
     */
    void updateUserLoginInformation(long userId, boolean isAuthenticationValid);
}
