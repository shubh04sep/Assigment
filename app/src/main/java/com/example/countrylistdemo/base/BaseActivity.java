package com.example.countrylistdemo.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class BaseActivity extends AppCompatActivity {


    protected BaseActivity mThis;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Initialize Places.
        //  Places.initialize(getApplicationContext(), getString(R.string.api_key));

// Create a new Places client instance.
        // PlacesClient placesClient = Places.createClient(this);


        mThis = this;
    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();

        } else {
            onHeaderBackPress();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * This method invokes when user press back button of header or device as well.
     */
    public void onHeaderBackPress() {
        super.onBackPressed();
    }

//Show the alertDialog as per requirement


    protected void setFragment(Fragment fragment, int container, boolean isAddToBackStack) {
        String fragmentName = fragment.getClass().getSimpleName();
        if (isAddToBackStack) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, fragmentName)
                    .addToBackStack(fragmentName)
                    .commitAllowingStateLoss();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, fragmentName)
                    .commitAllowingStateLoss();
        }
    }

    /**
     * This method set icon on header right most icon
     *
     * @param drawable icon that needs to set
     */
    public void setHeaderRightMostIcon(int drawable) {
    }


    /**
     * need to override this method in child class to get the click of header right most icon
     */
    public void onHeaderRightMostIconClicked() {
    }

    public void setHeaderRightMostIconVisibility(boolean isVisible) {
    }


    public BaseActivity getBaseActivity() {
        return mThis;
    }


}