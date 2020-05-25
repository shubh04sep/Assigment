package com.example.countrylistdemo.ui.general;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.countrylistdemo.api.ApiService;
import com.example.countrylistdemo.api.DependencyUtil;
import com.example.countrylistdemo.ui.general.vo.BlankRequest;
import com.example.countrylistdemo.ui.general.vo.GeneralResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralRepository {

    private final ApiService apiService;

    public GeneralRepository() {
        this.apiService = DependencyUtil.getAppService();
    }

    public LiveData<GeneralResponse> general(BlankRequest request) {
        final MutableLiveData<GeneralResponse> data = new MutableLiveData<>();
        apiService.general().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }


    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
