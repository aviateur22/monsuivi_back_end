package com.ctoutweb.monsuivi.infra.service;

public interface ICryptoService {
    /**
     * Hash une chaine
     *
     * @param textToHash String - Text a hasher
     *
     * @return Strong - Text hashé
     */
    public String hashText(String textToHash);

    /**
     * Vérification d'un hash et text
     *
     * @param hash String
     * @param plainText String
     *
     * @return True si la comparaison du hash et du text en claire est valide
     */
    public boolean isHashValid(String plainText, String hash);
}
