package com.kb.util;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@PropertySource({"classpath:/application.properties"})
@Slf4j
@RequiredArgsConstructor
@Service
public class S3Uploader {

    private final AmazonS3Client amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 파일 삭제
    public void deleteFile(String fileUrl) {
        String[] urlParts = fileUrl.split("/");
        String fileBucket = urlParts[2].split("\\.")[0];

        if (!fileBucket.equals(bucket)) {
            throw new AmazonS3Exception("Bucket doesn't match");
        }

        String objectKey = String.join("/", Arrays.copyOfRange(urlParts, 3, urlParts.length));

        if (!amazonS3.doesObjectExist(bucket, objectKey)) {
            throw new AmazonS3Exception("Object doesn't exist");
        }

        try {
            amazonS3.deleteObject(bucket, objectKey);
        } catch (AmazonS3Exception e) {
            log.error("File delete fail : " + e.getMessage());
            throw new AmazonS3Exception("File delete fail");
        } catch (SdkClientException e) {
            log.error("AWS SDK client error : " + e.getMessage());
            throw new AmazonS3Exception("AWS SDK client error");
        }

        log.info("File delete complete: " + objectKey);
    }

    // 단일 파일 저장
    public String saveFile(MultipartFile file) {
        String randomFilename = generateRandomFilename(file);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3.putObject(bucket, randomFilename, file.getInputStream(), metadata);
        } catch (AmazonS3Exception e) {
            log.error("Amazon S3 error while uploading file: " + e.getMessage());
            throw new AmazonS3Exception("Amazon S3 error while uploading file: " + e.getMessage());
        } catch (SdkClientException e) {
            log.error("AWS SDK client error while uploading file: " + e.getMessage());
            throw new AmazonS3Exception("AWS SDK client error while uploading file: " + e.getMessage());
        } catch (IOException e) {
            log.error("IO error while uploading file: " + e.getMessage());
            throw new AmazonS3Exception("IO error while uploading file: " + e.getMessage());
        }

        log.info("File upload completed: " + randomFilename);

        return amazonS3.getUrl(bucket, randomFilename).toString();
    }

    // 랜덤파일명 생성 (파일명 중복 방지)
    private String generateRandomFilename(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = validateFileExtension(originalFilename);
        String randomFilename = UUID.randomUUID() + "." + fileExtension;
        return randomFilename;
    }

    // 파일 확장자 체크
    private String validateFileExtension(String originalFilename) {
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        List<String> allowedExtensions = Arrays.asList("jpg", "png", "gif", "jpeg");

        if (!allowedExtensions.contains(fileExtension)) {
            throw new AmazonS3Exception("File extension not supported: " + fileExtension);
        }
        return fileExtension;
    }
}