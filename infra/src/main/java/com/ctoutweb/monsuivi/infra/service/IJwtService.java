package com.ctoutweb.monsuivi.infra.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ctoutweb.monsuivi.infra.config.authentication.UserPrincipal;
import com.ctoutweb.monsuivi.infra.model.jwt.JwtGenerated;


public interface IJwtService {

  /**
   * Génération d'un JWT incluant les données UserPrincipal
   *
   * @param userPrincipal UserPrincipal - Personne authentifié générant un Jwt
   *
   * @return Le jwt généré
   */
  public JwtGenerated generate(UserPrincipal userPrincipal);

  /**
   * Decoded un JWT
   *
   * @param token String - Jwt à verifier
   *
   * @return Le jwt decodé
   */
  public DecodedJWT validateAndDecode(String token);

  /**
   * Suppression d'un JWT a partir d'un email
   *
   * @param email L'email corresponsant au jwt
   */
  public void deleteJwtByUserEmail(String email);

  /**
   * Sauvegarde d'un JWT
   *
   * @param userId Long Id de la personne qui se connecte
   * @param jwt IJwtIssue JWT issue
   * @param email String Email de la personne
   */
  public void saveJwt(Long userId, JwtGenerated jwt, String email);

}
