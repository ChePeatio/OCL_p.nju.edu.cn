package io.github.chepeatio.p_nju_login.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import io.github.chepeatio.p_nju_login.R;
import io.github.chepeatio.p_nju_login.ui.base.BaseActivity;
import util.Constant;

/**
 * Created by Kedar on 2015/9/23.
 *
 * TODO 查看基本状态的页面
 */
public class StateActivity extends BaseActivity {

    private static TextView state;
    public static Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.SHOW_RESPONSE:
                    // handle the response on UI
                    String response = (String)msg.obj;
                    state.setText(response);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        state = (TextView)findViewById(R.id.state);
    }
}
