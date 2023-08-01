package com.hcardenas.hebcodingexcercise.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageObjectDTO {

    private UUID id;
    private String label;

    private Set<String> objectsDetected;


}
