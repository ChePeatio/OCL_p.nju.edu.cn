package io.github.chepeatio.p_nju_login;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import util.ActivityManagerUtils;

/**
 * Created by Kedar on 2015/9/23.
 *
 * TODO Application的基类
 */
public class MyApplication extends Application {

    public static String TAG;
    private static Context context; //方便获取全局Context
    private static MyApplication myApplication = null;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        TAG = this.getClass().getSimpleName();
        context = getApplicationContext();
        //由于Application类本身已经单例，所以直接按以下处理即可。
        myApplication = this;
    }

    public static Context getContext() {
        return context;
    }

    public static MyApplication getInstance(){
        return myApplication;
    }

    public void addActivity(Activity ac){
        ActivityManagerUtils.getInstance().addActivity(ac);
    }

    public void exit(){
        ActivityManagerUtils.getInstance().removeAllActivity();
    }

    public Activity getTopActivity(){
        return ActivityManagerUtils.getInstance().getTopActivity();
    }
}
