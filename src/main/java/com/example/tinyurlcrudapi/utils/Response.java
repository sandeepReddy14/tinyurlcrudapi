package com.example.tinyurlcrudapi.utils;

import com.example.tinyurlcrudapi.model.UrlData;

public class Response {
    private UrlData urlData;
    private ErrorCode errorCode;

    public Response(UrlData urlData, ErrorCode errorCode) {
        this.urlData = urlData;
        this.errorCode = errorCode;
    }

    public Response(UrlData urlData) {
        this.urlData = urlData;
    }

    public Response(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Response() {
    }

    public UrlData getUrlData() {
        return urlData;
    }

    public void setUrlData(UrlData urlData) {
        this.urlData = urlData;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
