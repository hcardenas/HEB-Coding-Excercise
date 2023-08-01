package com.hcardenas.hebcodingexcercise.clients.imagga;

import com.hcardenas.hebcodingexcercise.model.imagga.ImaggaClientResponse;

public interface ImaggaUrlService {

    ImaggaClientResponse executeApiWithUrl(String url);
    ImaggaClientResponse executeApiWithBase64(Byte[] image);
}
