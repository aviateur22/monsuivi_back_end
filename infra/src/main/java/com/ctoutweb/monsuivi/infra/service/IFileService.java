package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.model.document.IImageToSave;

public interface IFileService {

  /**
   * Upload un fichier
   * @param documentToSave IDocumentToSave - docuement a sauvegarder
   * @return String path d'acces du fichier
   */
  String uploadFile(IImageToSave documentToSave);
}
