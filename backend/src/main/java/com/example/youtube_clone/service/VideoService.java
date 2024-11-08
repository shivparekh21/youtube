package com.example.youtube_clone.service;

import com.example.youtube_clone.dto.UploadVideoResponse;
import com.example.youtube_clone.dto.VideoDto;
import com.example.youtube_clone.model.Video;
import com.example.youtube_clone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    //Possible because of AwsConfig, The AwsConfig class in this setup is a Spring configuration class that provides a configured instance of S3Client as a Spring Bean.
    // This allows you to inject S3Client wherever needed in your application, like in S3Service.
    private final S3Service s3Service;

/*
    public VideoService(S3Service s3Service) {
        this.s3Service = s3Service;
    }
*/


    public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
        String videoUrl = s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
        Video saveVideo = videoRepository.save(video);
        return new UploadVideoResponse(saveVideo.getId(), saveVideo.getVideoUrl());
    }

    //Find the video by videoId and if not found throw an exception
    //findById return an Optional<Video> if not present use orElseThrow()
    //Map the fields
    //Save the video to database
    public VideoDto editVideo(VideoDto videoDto) {

        Video savedVideo = getVideoById(videoDto.getId());

        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        savedVideo.setTags(videoDto.getTags());
        savedVideo.setVideoStatus(videoDto.getVideoStatus());

        videoRepository.save(savedVideo);
        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        Video savedVideoThumbnail = getVideoById(videoId);
        String thumbnailUrl = s3Service.uploadFile(file);
        savedVideoThumbnail.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(savedVideoThumbnail);

        return thumbnailUrl;
    }

    public Video getVideoById(String videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(()-> new IllegalArgumentException("Video not found"+ videoId));
    }
}
