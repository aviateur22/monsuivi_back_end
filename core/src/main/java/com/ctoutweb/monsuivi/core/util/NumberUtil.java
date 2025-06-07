package com.ctoutweb.monsuivi.core.util;

import com.ctoutweb.monsuivi.core.exception.CoreException;

public class NumberUtil {
  private NumberUtil() {
    throw new IllegalStateException("Le classe Number ne peut pas être instanciée");
  }

  /**
   * Convertion d'un mois chiffre en text
   * @param month short - Le mois a convertir
   * @return String - Le moi en lettre
   */
  public static String getMonthFromNumber(short month) {
    return switch (month) {
      case  1 -> "janvier";
      case  2 -> "février";
      case  3 -> "mars";
      case  4 -> "avril";
      case  5 -> "mai";
      case  6 -> "juin";
      case  7 -> "juillet";
      case  8 -> "aôut";
      case  9 -> "septembre";
      case  10 -> "octobre";
      case  11 -> "novembre";
      case  12 -> "décembre";
      default -> throw new CoreException(String.format("Impossible de convertir le moi %s en La valeur", month));
    };
  }
}
