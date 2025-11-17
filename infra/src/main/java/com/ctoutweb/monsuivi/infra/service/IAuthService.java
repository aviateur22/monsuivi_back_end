package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.dto.LoginDto;
import com.ctoutweb.monsuivi.infra.dto.response.LoginResponseDto;
import com.ctoutweb.monsuivi.infra.dto.RegisterSellerDto;
import com.ctoutweb.monsuivi.infra.dto.response.response.IResponseMessage;
import com.ctoutweb.monsuivi.infra.exception.AuthenicationException;

public interface IAuthService {

    /**
     * Connexion d'un utilisateur
     *
     * @param loginDto Les données de connexion avec email + mot de passe
     *
     * @return Si connexion authorisée renvoie un JWT ,utilisateur id et roles
     * @throws AuthenicationException renvoyé si connexion est bloqué temporairement ou si mot de passe invalide
     */
    LoginResponseDto login(LoginDto loginDto) throws AuthenicationException;

    /**
     * Enregistrement d'un nouveau vendeur
     *
     * @param dto Données email et mot de passe pour enregistrement d'un vendeur
     *
     * @return Le message d'enregistrement
     */
    IResponseMessage registerSeller(RegisterSellerDto dto);

    /**
     * Déconnexion d'un utilisateur
     *
     * @param sellerId L'identifiant de l'utilisateur
     *
     * @return Le message de deconnexion
     */
    IResponseMessage logout(long sellerId);
}
