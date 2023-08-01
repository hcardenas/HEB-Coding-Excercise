package com.hcardenas.hebcodingexcercise.model.imagga;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImaggaResults {

    private List<ImaggaTags> tags;

}
