package com.example.youtube_clone.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class S3Service implements FileService {

    private final S3Client s3Client;
    private final String bucketName = "youtube-clone-video-upload"; // Replace with your S3 bucket name

    @Value("${aws.region}")
    private String region;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        // Generate a unique key for the file in S3
        String filenameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String key = UUID.randomUUID().toString() + filenameExtension;

        // Metadata for the file
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("x-amz-meta-original-filename", file.getOriginalFilename());

        // Build the PutObjectRequest with metadata and public read ACL
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .metadata(metadata)
//                .acl(ObjectCannedACL.PUBLIC_READ) // Make the file publicly readable
                .build();

        try {
            // Upload file to S3
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            // Return the public URL for the uploaded file
            return generateFileUrl(bucketName, key);
        } catch (S3Exception | IOException e) {
            System.err.println("Failed to upload file: " + e.getMessage());
            return null;
        }
    }

    // Method to generate the public URL of the uploaded file
    private String generateFileUrl(String bucketName, String key) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, key);
    }
}
