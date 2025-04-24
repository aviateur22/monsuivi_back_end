package com.ctoutweb.monsuivi.infra.model.image;


import java.io.InputStream;

public interface IImageToSave {
  InputStream getRegisterFileStream();
  Long getFileSize();
  String getFileExtension();
  String getRandomFileName();
  void setFileStream(InputStream fileStream);
  void setFileSize(long fileSize);
  void setFileExtension(String fileExtension);
  void setRandomFileName(String randomFileName);

}
