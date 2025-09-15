package com.investment.assets.exception;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler
        implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if(response.getStatusCode().is4xxClientError()
            || response.getStatusCode().is5xxServerError())
            return  false;

        return hasError(response.getStatusCode());
    }
}
