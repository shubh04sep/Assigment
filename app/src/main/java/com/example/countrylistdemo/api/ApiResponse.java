package com.example.countrylistdemo.api;

import androidx.annotation.Nullable;


import java.io.IOException;
import java.net.UnknownHostException;

import retrofit2.Response;


/**
 * Generic class to hold response from Apis
 *
 * @param <T> : respective response model class
 */
public class ApiResponse<T> {

    public final int code;
    @Nullable
    public final T body;
    @Nullable
    public final String errorMessage;
    public String TAG = "Api Response";

    public ApiResponse(Throwable error) {
        if (error instanceof UnknownHostException)
            code = ApiResponseStatusCode.INTERNET_ERROR;
        else
            code = ApiResponseStatusCode.DATABASE_ERROR;
        body = null;
        errorMessage = error.getMessage();
    }

    public ApiResponse(Response<T> response) {
        code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                    Lg.e(TAG, "error while parsing response");
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }

    }

    public boolean isSuccessful() {
        return code >= ApiResponseStatusCode.SUCCESS_200 && code < ApiResponseStatusCode.SUCCESS_300;
    }
}
