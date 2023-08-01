package com.hcardenas.hebcodingexcercise.controller;


import com.hcardenas.hebcodingexcercise.model.ImageDetectionRequest;
import com.hcardenas.hebcodingexcercise.service.ImageObjectDetection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ImagesController {

    private final ImageObjectDetection objectDetection;

    @GetMapping("/images")
    public ResponseEntity<?> getAllImages(@RequestParam(required = false) final List<String> objects) {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/images/{imageId}")
    public ResponseEntity<?> getImageWithId(@PathVariable final UUID imageId) {

        return ResponseEntity.ok().build();
    }

    @PostMapping(
            path = "/images",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> saveImage(@RequestBody ImageDetectionRequest image) {

        objectDetection.saveImage(image);

        return ResponseEntity.ok().build();
    }

}
