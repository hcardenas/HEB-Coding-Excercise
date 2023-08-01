package com.hcardenas.hebcodingexcercise.service;

import com.hcardenas.hebcodingexcercise.model.ImageDetectionRequest;
import com.hcardenas.hebcodingexcercise.model.ImageObjectDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImageObjectDetection {

    List<ImageObjectDTO> getImages();

    List<ImageObjectDTO> getImagesList(List<String> descriptions);

    ImageObjectDTO getImageWithId(UUID id);

    ImageObjectDTO saveImage(ImageDetectionRequest image);

}
