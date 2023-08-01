package com.hcardenas.hebcodingexcercise.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDetectionRequest {
    private Byte[] image;
    private String url;
    private String label;
    private Boolean isObjectDetectionEnabled;
}
