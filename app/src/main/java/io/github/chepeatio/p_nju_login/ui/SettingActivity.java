package io.github.chepeatio.p_nju_login.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import io.github.chepeatio.p_nju_login.R;
import io.github.chepeatio.p_nju_login.ui.base.BaseActivity;
import util.Constant;
import util.ExecutingLogin;

/**
 * Created by Kedar on 2015/9/19.
 *
 * TODO 用户账号密码设置页面
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private SharedPreferences pref;

    private EditText accountEdit;
    private EditText passwordEdit;
    private CheckBox rememberCheck;
    private Button loginButton;

    public static Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.SHOW_RESPONSE:
                    // handle the response on UI
                    String response = (String)msg.obj;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        accountEdit = (EditText)findViewById(R.id.account);
        passwordEdit = (EditText)findViewById(R.id.password);
        rememberCheck = (CheckBox)findViewById(R.id.store);
        loginButton = (Button)findViewById(R.id.login);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {

            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            SharedPreferences.Editor editor = pref.edit();

            if (rememberCheck.isChecked()) {
                editor.putBoolean("remember_password", true);
                editor.putString("account", account);
                editor.putString("password", password);
            } else {
                editor.clear();
            }
            editor.apply();

            Runnable post = new ExecutingLogin(account, password);
            Thread postThread = new Thread(post);
            postThread.start();
        }
    }
}
