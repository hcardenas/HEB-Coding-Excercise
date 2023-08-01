package com.hcardenas.hebcodingexcercise.model.imagga;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImaggaTags {

    private Long confidence;

    @JsonProperty("tag")
    private ImaggaTag tag;

}
