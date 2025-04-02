package com.ctoutweb.monsuivi.infra.model.document.impl;

import com.ctoutweb.monsuivi.infra.model.document.IImageToSave;

import java.io.InputStream;

public class ImageToSaveImpl implements IImageToSave {
  private InputStream fileStream;
  private long fileSize;
  private String fileExtension;
  private String randomFileName;
  @Override
  public InputStream getRegisterFileStream() {
    return fileStream;
  }

  @Override
  public Long getFileSize() {
    return fileSize;
  }

  @Override
  public String getFileExtension() {
    return fileExtension;
  }

  @Override
  public String getRandomFileName() {
    return randomFileName;
  }

  @Override
  public void setFileStream(InputStream fileStream) {
    this.fileStream = fileStream;
  }

  @Override
  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  @Override
  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  @Override
  public void setRandomFileName(String randomFileName) {
    this.randomFileName = randomFileName;
  }
}
