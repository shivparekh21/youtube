package com.example.youtube_clone.service;

import org.springframework.web.multipart.MultipartFile;
//in case if we dont want to upload file to s3 and maintain it own my own server we created FileService Interface

public interface FileService {
    String uploadFile(MultipartFile file);

}
