package com.example.countrylistdemo.api;


import com.example.countrylistdemo.ui.general.vo.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * All API services, with their Url, Response type, Request type and Request method(eg. GET, POST)
 */
public interface ApiService {
    //method for getting general data
    @GET(ApiConstant.ALL)
    Call<GeneralResponse> general();

}
