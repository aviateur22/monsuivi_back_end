package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.model.image.IImageToSave;
import jakarta.servlet.http.HttpServletResponse;

public interface IFileService {

  /**
   * Upload un fichier
   * @param documentToSave IDocumentToSave - docuement a sauvegarder
   * @return String path d'acces du fichier
   */
  String uploadFile(IImageToSave documentToSave);

  /**
   * Renvoie un fichier par stream
   * @param filePath String - path de l'image
   */
  void streamFile(String filePath, HttpServletResponse httpResponse);
}
