package com.example.youtube_clone.dto;

import com.example.youtube_clone.model.Comment;
import com.example.youtube_clone.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {
    private String id;
    private String title;
    private String description;
    private Set<String> tags;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
}
