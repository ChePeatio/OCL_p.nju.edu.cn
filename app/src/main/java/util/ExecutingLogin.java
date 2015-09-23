package util;

import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import io.github.chepeatio.p_nju_login.ui.SettingActivity;
import io.github.chepeatio.p_nju_login.ui.StateActivity;

/**
 * Created by Kedar on 2015/9/19.
 *
 * TODO 独立线程负责向服务器POST用户的账号密码
 */
public class ExecutingLogin implements Runnable {

    String username;
    String password;

    public ExecutingLogin(String account, String password) {
        this.username = account;
        this.password = password;
    }

    @Override
    public void run() {
        HttpURLConnection connection = null;
        OutputStreamWriter out = null;
        BufferedReader br = null;

        try {
            // 设置HttpURLConnection的POST实例
            URL url = new URL("http://p.nju.edu.cn/portal_io/login");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");

            // POST连接请求
            out = new OutputStreamWriter(connection.getOutputStream());
            out.write("username=" + username + "&password=" + password);
            out.flush();

            // 读取返回信息
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }

            // 将服务器返回的结果存放到Message中
            Message message = new Message();
            message.what = Constant.SHOW_RESPONSE;
            message.obj = sb.toString();
            StateActivity.handler.sendMessage(message);

        } catch (Exception ex) {
            Log.e("executingLogin", ex.toString());
        } finally {
            if (connection != null)
                connection.disconnect();
            try {
                if (br != null)
                    br.close();
                if (out != null)
                    out.close();
            } catch (IOException ioe) {
                Log.e("ExecutingLogin", ioe.toString());
            }
        }
    }
}
