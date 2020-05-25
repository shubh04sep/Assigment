package com.example.countrylistdemo.api;


public class DependencyUtil {

    private static ApiService sApiServiceInstance;


    public static ApiService getAppService() {
        if (sApiServiceInstance == null) {
            sApiServiceInstance = AppRetrofit.getInstance().getApiService();
        }
        return sApiServiceInstance;
    }

    public static ApiService getAppServices() {
        if (sApiServiceInstance == null) {
        }
        return sApiServiceInstance;
    }




    public static AppExecutors getAppExecuter() {
        return new AppExecutors();
    }
}
