package com.ctoutweb.monsuivi.infra.service.impl;

import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.exception.ServiceException;
import com.ctoutweb.monsuivi.infra.service.IFileService;
import com.ctoutweb.monsuivi.infra.model.image.IImageToSave;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

@Service
public class AwsS3FileServiceImpl implements IFileService {
  private static final Logger LOGGER = LogManager.getLogger();
  @Value("${aws.access.key}")
  private String accessKey;

  @Value("${aws.secret.key}")
  private String secretKey;

  @Value("${aws.bucket.name}")
  private String bucketName;

  @Value("${aws.region}")
  private String region;

  private final InfraFactory factory;
  S3Client client;
  public AwsS3FileServiceImpl(InfraFactory factory) {
    this.factory = factory;
  }

  @PostConstruct
  public void checkCredentials() {

    var credentials = AwsBasicCredentials.create(accessKey, secretKey);
    client = S3Client
            .builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
            .build();
  }
  @Override
  public String uploadFile(IImageToSave documentToSave) {
    PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(
            String.format("%s.%s", documentToSave.getRandomFileName(), documentToSave.getFileExtension()))
            .build();
    client.putObject(putObjectRequest, RequestBody.fromInputStream(documentToSave.getRegisterFileStream(), documentToSave.getFileSize()));

    // Renvoie le path du fichier sur AWS
    return String.format("%s.%s", documentToSave.getRandomFileName(), documentToSave.getFileExtension());
  }

  @Override
  public void streamFile(String filePath, HttpServletResponse httpResponse) {
    LOGGER.error(()->"[AwsS3FileServiceImpl]-[downloadFile] - Début récupération fichier AWS");
    GetObjectRequest getObjectRequest = GetObjectRequest
            .builder()
            .bucket(bucketName)
            .key(filePath)
            .responseContentType("image/png")
            .build();

    try(ResponseInputStream<GetObjectResponse> response = client.getObject(getObjectRequest);
        OutputStream out = httpResponse.getOutputStream()) {
      httpResponse.setContentType("image/jpeg");
      httpResponse.setHeader("Content-Disposition", "inline; filename=\"article-" + filePath + ".jpg\"");

      byte[] buffer = new byte[8192];
      int bytesRead;
      while ((bytesRead = response.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
      }

      out.flush();

    } catch (Exception exception) {
      LOGGER.error(()->"[AwsS3FileServiceImpl]-[downloadFile] - Echec récupération fichier AWS");
      LOGGER.error(()->exception);
      httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
      throw new ServiceException("Echec récupération image");
    }
  }
}
