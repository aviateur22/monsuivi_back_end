package com.ctoutweb.monsuivi.infra.constant;

public class Constant {

  // PATH GENERIQUE DES SAUVEGARDE FICHIER SUR AWS
  public static final String AWS_S3_SAVE_PATH = "https://%s.s3.%s.amazonaws.com/%s";

  // Catégorie des produits a vendre
  public static final String BOOK_CATEGORY = "bk";
  public static final String GAME_CATEGORY = "ga";
  public static final String CLOTHING_CATEGORY = "cl";

  // Status des produits
  public static final String FOR_SALE = "fs";
  public static final String SOLD = "so";
  public static final boolean IS_PRODUCT_ACTIF_AT_CREATION = true;

  // Fichier
  public static final byte[] PDF_MAGIC_NUMBER = {0x25, 0x50, 0x44, 0x46};
  public static final byte[] PNG_MAGIC_NUMBER = {(byte) 0x89, 0x50, 0x4E, 0x47};
  public static final byte[] JPEG_MAGIC_NUMBER = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF};

}
