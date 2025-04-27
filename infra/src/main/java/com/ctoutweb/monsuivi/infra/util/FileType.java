package com.ctoutweb.monsuivi.infra.util;

import static com.ctoutweb.monsuivi.infra.constant.Constant.*;

public enum FileType {
  PDF(PDF_MAGIC_NUMBER, "pdf"),
  PNG(PNG_MAGIC_NUMBER, "png"),
  JPEG(JPEG_MAGIC_NUMBER,"jpeg");

  private byte[] magicBytes;

  private String fileExtension;

  FileType(byte[] magicBytes, String fileExtension) {
    this.magicBytes = magicBytes;
    this.fileExtension = fileExtension;
  }

  /**
   * Renvoie l'extension
   * @return String
   */
  public String getFileExtension() {
    return this.fileExtension;
  }

  /**
   * Vérification extension d'un fichier
   * @param fileMagicBytes byte[]
   * @return boolean
   */
  public boolean isFileExtensionValid(byte[] fileMagicBytes) {
    if(fileMagicBytes.length < magicBytes.length)
      return  false;

    for (int i=0; i < fileMagicBytes.length; i++) {
      if (Byte.toUnsignedInt(fileMagicBytes[i]) != magicBytes[i])
        return false;
    }
    return true;
  }
}
