package com.example.countrylistdemo.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.countrylistdemo.R;
import com.example.countrylistdemo.api.Lg;
import com.example.countrylistdemo.base.BaseActivity;
import com.example.countrylistdemo.databinding.ActivityCountryListBinding;
import com.example.countrylistdemo.ui.general.GeneralViewModel;
import com.example.countrylistdemo.ui.general.vo.BlankRequest;
import com.example.countrylistdemo.ui.general.vo.GeneralResponse;

public class CountryListActivity extends BaseActivity {
    private ActivityCountryListBinding mBinding;
    private GeneralViewModel generalVM;
    private CountryListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_country_list);
        generalVM = ViewModelProviders.of(this).get(GeneralViewModel.class);
        getServiceCategoryList();
    }

    private void getServiceCategoryList() {
        generalVM.setmGeneralRequest(new BlankRequest());
        if (!generalVM.getMgeneralResponse().hasActiveObservers()) {
            generalVM.getMgeneralResponse().observe(this, new Observer<GeneralResponse>() {
                @Override
                public void onChanged(GeneralResponse generalResponse) {
                    if (generalResponse != null)
                        handleGeneralResponse(generalResponse);
                }
            });
        }
        /*if (!generalVM.getMgeneralResponse().hasActiveObservers()) {
            generalVM.getMgeneralResponse().observe(this, this::handleGeneralResponse);
        }*/
    }

    private void handleGeneralResponse(GeneralResponse generalResponse) {

        initRecyclerView(generalResponse);

    }

    private void initRecyclerView(GeneralResponse generalResponse) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBinding.countryListRecylerview.setLayoutManager(linearLayoutManager);
        mAdapter = new CountryListAdapter(this, generalResponse);
        mBinding.countryListRecylerview.setAdapter(mAdapter);
    }
}
