package com.example.countrylistdemo.ui.general;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.countrylistdemo.base.AbsentLiveData;
import com.example.countrylistdemo.base.BaseViewModel;
import com.example.countrylistdemo.ui.general.vo.BlankRequest;
import com.example.countrylistdemo.ui.general.vo.GeneralResponse;

public class GeneralViewModel extends BaseViewModel {

    private final GeneralRepository generalRepository;

    final MutableLiveData<BlankRequest> request = new MutableLiveData<>();
    private LiveData<GeneralResponse> mgeneralResponse;

    public GeneralViewModel(@NonNull Application application) {
        super(application);
        generalRepository = new GeneralRepository();
//        mgeneralResponse = generalRepository.general();

        mgeneralResponse = Transformations.switchMap(request, new Function<BlankRequest, LiveData<GeneralResponse>>() {
            @Override
            public LiveData<GeneralResponse> apply(BlankRequest bookingsRequest) {
                if (bookingsRequest == null) {
                    return AbsentLiveData.create();
                } else
                    return generalRepository.general(bookingsRequest);
            }
        });


    }

    public void setmGeneralRequest(BlankRequest blankRequest) {
        this.request.setValue(blankRequest);
    }


    public LiveData<GeneralResponse> getMgeneralResponse() {
        return mgeneralResponse;
    }


}
