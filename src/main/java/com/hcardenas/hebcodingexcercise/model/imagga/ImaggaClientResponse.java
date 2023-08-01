package com.hcardenas.hebcodingexcercise.model.imagga;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImaggaClientResponse {



    private ImaggaResults result;
    private ImaggaStatus status;


}
