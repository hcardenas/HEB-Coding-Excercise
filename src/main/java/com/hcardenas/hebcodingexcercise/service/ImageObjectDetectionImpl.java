package com.hcardenas.hebcodingexcercise.service;

import com.hcardenas.hebcodingexcercise.clients.imagga.ImaggaUrlServiceImpl;
import com.hcardenas.hebcodingexcercise.model.ImageDetectionRequest;
import com.hcardenas.hebcodingexcercise.model.ImageObjectDTO;
import com.hcardenas.hebcodingexcercise.model.imagga.ImaggaClientResponse;
import com.hcardenas.hebcodingexcercise.model.persistence.Image;
import com.hcardenas.hebcodingexcercise.model.persistence.ObjectDescr;
import com.hcardenas.hebcodingexcercise.persistence.ImageRepository;
import com.hcardenas.hebcodingexcercise.persistence.ObjectDescrRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class ImageObjectDetectionImpl implements ImageObjectDetection {

    private final ImaggaUrlServiceImpl imaggaUrlServiceImpl;

    private final ImageRepository imageRepository;

    private final ObjectDescrRepository objectDescrRepository;

    @Override
    public List<ImageObjectDTO> getImages() {

        List<ImageObjectDTO> resultList = List.of();
        imageRepository.findAll().stream().forEach(item -> resultList.add(
                ImageObjectDTO.builder()
                        .id(item.getId())
                        .objectsDetected(Set.of(new HashSet<>(item.getObjects()).toString()))
                        .label(item.getLabel())
                        .build()
        ));

        return resultList;
    }

    @Override
    public List<ImageObjectDTO> getImagesList(List<String> descriptions) {
        List<ImageObjectDTO> resultList = List.of();

        imageRepository.findAll().stream().filter(item -> item.getObjects().contains(descriptions)).forEach(item -> resultList.add(
                ImageObjectDTO.builder()
                        .id(item.getId())
                        .objectsDetected(Set.of(new HashSet<>(item.getObjects()).toString()))
                        .label(item.getLabel())
                        .build()
        ));

        return resultList;
    }

    @Override
    public ImageObjectDTO getImageWithId(UUID id) {
        ImageObjectDTO resultDTO = ImageObjectDTO.builder().build();
        Optional<Image> result = imageRepository.findById(id);
        if (result.isEmpty()) return resultDTO;

        resultDTO.setObjectsDetected(Set.of(new HashSet<>(result.get().getObjects()).toString()));
        resultDTO.setId(result.get().getId());
        resultDTO.setLabel(result.get().getLabel());

        return  resultDTO;
    }

    @Override
    public ImageObjectDTO saveImage(ImageDetectionRequest image) {
        ImaggaClientResponse imaggaClientResponse = null;
        if (image.getIsObjectDetectionEnabled()) {
            if (!image.getUrl().isEmpty()) {
                imaggaClientResponse = imaggaUrlServiceImpl.executeApiWithUrl(image.getUrl());
            } else if (Arrays.stream(image.getImage()).findAny().isPresent()) {
                imaggaClientResponse = imaggaUrlServiceImpl.executeApiWithBase64(image.getImage());
            }
        }

        Image newImage = Image.builder()
                .label(image.getLabel())
                .base64Image(image.getImage())
                .objects(null)
                .build();

        Image imageResult = imageRepository.save(newImage);


        if (imaggaClientResponse != null) {
            final Set<ObjectDescr> objectDescrs = new HashSet<>();
            imaggaClientResponse.getResult().getTags().stream().forEach(item -> {
                objectDescrs.add(ObjectDescr.builder()
                                .uuid(imageResult.getId())
                                .image(imageResult)
                                .description(item.getTag().getEn())
                        .build());
            });

            objectDescrRepository.saveAll(objectDescrs);
            imageResult.setObjects(objectDescrs);
        }


        return ImageObjectDTO.builder()
                .label(imageResult.getLabel())
                .id(imageResult.getId())
                .build();
    }


}
