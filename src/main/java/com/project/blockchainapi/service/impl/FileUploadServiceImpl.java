package com.project.blockchainapi.service.impl;

import com.project.blockchainapi.exception.InternalServerException;
import com.project.blockchainapi.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${minio.bucketName}")
    private String minioBucketName;

    private final MinioClient minioClient;

    @Override
    public String uploadFile(MultipartFile file, String email) throws Exception {
        try {
            // Generate a unique file name (e.g., using UUID) or use the original file name
            String fileName = "avatar/" + email + "." +FilenameUtils.getExtension(file.getOriginalFilename());
            // Upload the file to the bucket
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioBucketName)
                    .object(fileName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build());
            return fileName;
        } catch (MinioException e) {
            // Handle MinioException or other exceptions
            throw new InternalServerException("Failed to upload file to MinIO: " + e.getMessage(), e);
        }

    }
}