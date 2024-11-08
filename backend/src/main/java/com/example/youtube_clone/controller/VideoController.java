package com.example.youtube_clone.controller;

import com.example.youtube_clone.dto.UploadVideoResponse;
import com.example.youtube_clone.dto.VideoDto;
import com.example.youtube_clone.model.Video;
import com.example.youtube_clone.service.S3Service;
import com.example.youtube_clone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    // Upload Video and set videoUrl
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam ("file") MultipartFile file) {
        return videoService.uploadVideo(file);
    }

    // Upload Thumbnail
    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam ("file") MultipartFile file, @RequestParam("videoId") String videoId) {
        return videoService.uploadThumbnail(file, videoId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto) {
        return videoService.editVideo(videoDto);
    }
}
