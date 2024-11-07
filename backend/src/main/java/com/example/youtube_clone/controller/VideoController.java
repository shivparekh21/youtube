package com.example.youtube_clone.controller;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadVideo(@RequestParam ("file") MultipartFile file) {
        videoService.uploadVideo(file);
    }
}
