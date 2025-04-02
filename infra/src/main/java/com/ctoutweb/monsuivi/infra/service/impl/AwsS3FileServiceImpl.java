package com.ctoutweb.monsuivi.infra.service.impl;

import com.ctoutweb.monsuivi.infra.constant.Constant;
import com.ctoutweb.monsuivi.infra.service.IFileService;
import com.ctoutweb.monsuivi.infra.model.document.IImageToSave;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class AwsS3FileServiceImpl implements IFileService {
  @Value("${aws.access.key}")
  private String accessKey;

  @Value("${aws.secret.key}")
  private String secretKey;

  @Value("${aws.bucket.name}")
  private String bucketName;

  @Value("${aws.region}")
  private String region;

  S3Client client;
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
    PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(documentToSave.getRandomFileName()).build();
    client.putObject(putObjectRequest, RequestBody.fromInputStream(documentToSave.getRegisterFileStream(), documentToSave.getFileSize()));

    // Renvoie le path du fichier sur AWS
    return String.format(Constant.AWS_S3_SAVE_PATH, bucketName, region, documentToSave.getRandomFileName());
  }
}
