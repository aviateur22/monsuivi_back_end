package com.ctoutweb.monsuivi.infra.helper;

import com.ctoutweb.monsuivi.infra.model.image.IImageToSave;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class CompressFileHelper {

  private static final Logger LOGGER = LogManager.getLogger();
  public IImageToSave compress(IImageToSave imageToSave)  {
    try {
      LOGGER.debug(()->"[CompressFileHelper]-[compress] - Compression du fichier");
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      Thumbnails.of(imageToSave.getRegisterFileStream())
              .size(800, 800)
              .outputQuality(0.1)
              .toOutputStream(outputStream);

      byte[] compressedBytes = outputStream.toByteArray();
      outputStream.close();

      LOGGER.debug(()->String.format("[CompressFileHelper]-[getFieSize] - taille du fichier: %s", compressedBytes.length));

      imageToSave.setFileStream(new ByteArrayInputStream(compressedBytes));
      imageToSave.setFileSize(compressedBytes.length);
      return imageToSave;
    } catch (IOException exception) {
      LOGGER.error(()->"[CompressFileHelper]-[compress] - Erreur dans le compression du fichier");
      return imageToSave;
    }
  }
}
