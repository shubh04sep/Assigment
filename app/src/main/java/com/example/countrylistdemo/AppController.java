package com.example.countrylistdemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.example.countrylistdemo.base.BaseActivity;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppController extends Application implements Application.ActivityLifecycleCallbacks {
    private static AppController instance;

    private BaseActivity activity;

    /**
     * Gets create.
     *
     * @return the create
     */
    public static synchronized AppController getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerActivityLifecycleCallbacks(this);

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{4}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }
        return code;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {


    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity instanceof BaseActivity)
            this.activity = (BaseActivity) activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d("onActivityPaused", "ads");
    }

    @Override
    public void onActivityStopped(Activity activity) {

        Log.d("onActivityStopped", "ads");
        /*smsVerifyCatcher.onStop();*/
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d("onActivityDestroyed", "ads");

    }


    public BaseActivity getActivity() {
        return activity;
    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("Service status", "Running");
                return true;
            }
        }
        Log.i("Service status", "Not running");
        return false;
    }


}
