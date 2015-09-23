package io.github.chepeatio.p_nju_login;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import util.Constant;
import util.ExecutingLogin;

/**
 * Created by Kedar on 2015/9/19.
 * Edited by Kedar on 2015/9/20.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private EditText accountEdit;
    private EditText passwordEdit;
    private CheckBox rememberCheck;
    private Button loginButton;
    private static TextView responseText;

    public static Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.SHOW_RESPONSE:
                    // handle the response on UI
                    String response = (String)msg.obj;
                    responseText.setText(response);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountEdit = (EditText)findViewById(R.id.account);
        passwordEdit = (EditText)findViewById(R.id.password);
        rememberCheck = (CheckBox)findViewById(R.id.store);
        loginButton = (Button)findViewById(R.id.login);
        responseText = (TextView)findViewById(R.id.response);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {

            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();

            Runnable post = new ExecutingLogin(account, password);
            Thread postThread = new Thread(post);
            postThread.start();
        }
    }
}
