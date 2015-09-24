package io.github.chepeatio.p_nju_login.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Window;

import io.github.chepeatio.p_nju_login.R;
import io.github.chepeatio.p_nju_login.ui.base.BaseActivity;
import util.ExecutingLogin;

/**
 * Created by Kedar on 2015/9/23.
 *
 * TODO 闪屏界面，根据指定时间进行跳转
 * 		在activity_splash.xml中加入background属性并传入图片资源ID即可
 */
public class SplashActivity extends BaseActivity {

    private static final long DELAY_TIME = 2000L;
    private boolean isRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

/*        if (!isWiFiActive()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("请先将您手机的WLAN连入校园网络，再打开本应用！");
            builder.setTitle("打开WLAN");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    myApplication.exit();
                }
            });
        }*/

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        isRemember = pref.getBoolean("remember_password", false);
        String account = pref.getString("account", "");
        String password = pref.getString("password", "");
        if (isRemember) {
            Runnable post = new ExecutingLogin(account, password);
            Thread postThread = new Thread(post);
            postThread.start();
        }
        redirectByTime();
    }

    /**
     * 根据时间进行页面跳转
     */
    private void redirectByTime() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (isRemember) {
                    // 本地有账号密码，将跳转到状态页面
                    redictToActivity(SplashActivity.this, StateActivity.class, null);
                } else {
                    // 本地没有账号密码，跳转到登陆页面
                    redictToActivity(SplashActivity.this, SettingActivity.class, null);
                }
                finish();
            }
        }, DELAY_TIME);
    }

    /**
     * 判断WIFI是否打开
     */
    private boolean isWiFiActive() {
        ConnectivityManager connectivity = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] infos = connectivity.getAllNetworkInfo();
            if (infos != null) {
                for(NetworkInfo ni : infos){
                    if(ni.getTypeName().equals("WIFI") && ni.isConnected()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
