package com.zhoumushui.zygotebubblebadge;

import android.app.Application;
import android.content.Context;

import com.zhoumushui.zygotebubblebadge.util.MyUncaughtExceptionHandler;


public class MyApp extends Application {

    private Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        MyUncaughtExceptionHandler myUncaughtExceptionHandler = MyUncaughtExceptionHandler
                .getInstance();
        myUncaughtExceptionHandler.init(context);

        super.onCreate();
    }

}
