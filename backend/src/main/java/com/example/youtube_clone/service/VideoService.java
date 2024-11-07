package com.example.youtube_clone.service;

import com.example.youtube_clone.model.Video;
import com.example.youtube_clone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    //Possible because of AwsConfig, The AwsConfig class in this setup is a Spring configuration class that provides a configured instance of S3Client as a Spring Bean.
    // This allows you to inject S3Client wherever needed in your application, like in S3Service.
    private final S3Service s3Service;

//    public VideoService(S3Service s3Service) {
//        this.s3Service = s3Service;
//    }


    public void uploadVideo(MultipartFile multipartFile) {
        String videoUrl = s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);

        videoRepository.save(video);
    }
}
