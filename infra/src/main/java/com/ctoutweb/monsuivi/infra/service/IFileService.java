package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.model.image.IImageToSave;

public interface IFileService {

  /**
   * Upload un fichier
   * @param documentToSave IDocumentToSave - docuement a sauvegarder
   * @return String path d'acces du fichier
   */
  String uploadFile(IImageToSave documentToSave);

  /**
   * Recupération de l'image
   * au format base 64
   * @param filePath String - path de l'image
   * @return String Imagebase64
   */
  String downloadFile(String filePath);
}
