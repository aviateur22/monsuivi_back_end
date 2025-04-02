package com.ctoutweb.monsuivi.infra.util;

import java.util.UUID;

public class TextUtil {
  /**
   * Génération d'un String Aléatoire de type UUID
   * @return String
   */
  public static String getRandomNameUUID() {
    byte[] timeNow = ("time now" +" " + System.currentTimeMillis()).getBytes();
    return UUID.nameUUIDFromBytes(timeNow).toString();
  }

  /**
   * Nettoie le nom d'un document envoyé par un utilisateur
   * @param fileName String - nom du document original
   * @return String
   */
  public static String sanitizeDocumentName(String fileName) {
    int maxLength = 255;

    // Supprssion des charare supplementatire
    if(fileName.length() > maxLength)
      fileName = fileName.substring(0, maxLength);

    return fileName.replaceAll("[<>:\"/\\\\|?*]", "_")
            .replaceAll("\\s+", "_")
            .replaceAll("\\.+$", "");
  }
}
