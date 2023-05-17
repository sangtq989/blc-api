package com.project.blockchainapi.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class BeanFactory {

    @Value("${minio.endpoint}")
    private String minioEndpoint;

    @Value("${minio.accessKey}")
    private String minioAccessKey;

    @Value("${minio.secretKey}")
    private String minioSecretKey;

    @Value("${minio.bucketName}")
    private String minioBucketName;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SpringTemplateEngine springTemplateEngine(SpringResourceTemplateResolver springResourceTemplateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(springResourceTemplateResolver);
        return templateEngine;
    }

    @Bean
    @Primary
    public SpringResourceTemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
        emailTemplateResolver.setPrefix("classpath:/templates/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return emailTemplateResolver;
    }

    @Bean
    public MinioClient generateMinioClient() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        // Create a MinioClient with your credentials and endpoint
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioEndpoint)
                .credentials(minioAccessKey, minioSecretKey)
                .build();

        // Check if the bucket exists, create it if necessary
        boolean isBucketExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioBucketName).build());
        if (!isBucketExist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioBucketName).build());
        }
        String newPolicy = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::block0\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:PutObject\",\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\"],\"Resource\":[\"arn:aws:s3:::block0/*\"]}]}" + minioBucketName + "/*\"}]}";

        // Update bucket policy to enable download
        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                .bucket(minioBucketName)
                .config(newPolicy)
                .build());


        return minioClient;
    }

}
