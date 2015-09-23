package io.github.chepeatio.p_nju_login.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.github.chepeatio.p_nju_login.MyApplication;

/**
 * Created by Kedar on 2015/9/23.
 * <p/>
 * TODO Activity的基类
 */
public class BaseActivity extends Activity {

    protected static String TAG ;

    protected MyApplication myApplication;

    @Override
    protected void onCreate(Bundle bundle) {
        // TODO Auto-generated method stub
        super.onCreate(bundle);
        TAG = this.getClass().getSimpleName();
        initConfigure();
    }

    /**
     * 获取Application，并将Activity加入到管理队列中
     */
    private void initConfigure() {
        if(myApplication == null){
            myApplication = MyApplication.getInstance();
        }
        myApplication.addActivity(this);
    }

    /**
     * Activity跳转
     */
    public void redictToActivity(Context context,Class<?> targetActivity,Bundle bundle){
        Intent intent = new Intent(context, targetActivity);
        if(null != bundle){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

}
