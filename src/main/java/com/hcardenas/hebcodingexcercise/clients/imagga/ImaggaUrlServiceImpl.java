package com.hcardenas.hebcodingexcercise.clients.imagga;


import com.hcardenas.hebcodingexcercise.exceptions.ImaggaClientErrorException;
import com.hcardenas.hebcodingexcercise.model.imagga.ImaggaClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImaggaUrlServiceImpl implements ImaggaUrlService{

    private final WebClient imaggaWebClient;

    private final static String URL = "/v2/tags";
    private final static String IMAGE_PARAM_URL = "image_url";
    private final static String IMAGE_PARAM_BASE64 = "image_base64";


    @Override
    public ImaggaClientResponse executeApiWithUrl(String url) {

        ImaggaClientResponse response =
                imaggaWebClient.get()
                        .uri(uriBuilder -> uriBuilder
                            .path(URL)
                            .queryParam(IMAGE_PARAM_URL, url).build()
                            )
                .retrieve()
                .onStatus(HttpStatus::isError, error -> Mono.error(
                        new ImaggaClientErrorException(error.statusCode(), "imaggaClient failed using URL")
                ))
                .bodyToMono(ImaggaClientResponse.class).block();


        return response;
    }

    @Override
    public ImaggaClientResponse executeApiWithBase64(Byte[] image) {
        ImaggaClientResponse response =
                imaggaWebClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path(URL)
                                .queryParam(IMAGE_PARAM_BASE64, (Object[]) image).build()
                        )
                        .retrieve()
                        .onStatus(HttpStatus::isError, error -> Mono.error(
                                new ImaggaClientErrorException(error.statusCode(), "imaggaClient failed using Base64")
                        ))
                        .bodyToMono(ImaggaClientResponse.class).block();


        return response;
    }

}
