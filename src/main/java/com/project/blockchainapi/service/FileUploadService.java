package com.project.blockchainapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadFile(MultipartFile file, String email) throws Exception;
}
