package com.shj.http;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class LoginActivity extends Activity {
    private EditText usernameEt;
    private EditText pwdEt;
    private Button loginButton;
    private static final String URL_SERVER = "http://114.115.179.78:8888/hiot/auth/login?";
    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI, calling
     * {@link #managedQuery(Uri, String[], String, String[], String)} to retrieve
     * cursors for data being displayed, etc.
     *
     * <p>You can call {@link #finish} from within this function, in
     * which case onDestroy() will be immediately called after {@link #onCreate} without any of the
     * rest of the activity lifecycle ({@link #onStart}, {@link #onResume}, {@link #onPause}, etc)
     * executing.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        usernameEt = findViewById(R.id.username);
        pwdEt = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEt.getText().toString();
                String password = pwdEt.getText().toString();
                if (TextUtils.isEmpty(username)|| TextUtils.isEmpty(password)){
                    return;
                }else {
                    login(username,password);
                }
            }
        });
    }

    private  void login(final String username,final String password){
        new  Thread(){
            @Override
            public void run() {
                super.run();
                InputStream inputStream = null;
                InputStreamReader inputStreamReader = null;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(URL_SERVER);
                    URLConnection urlConnection = url.openConnection();
                    HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;

                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setRequestMethod("POST");

                    String params = "username="+username+"&password="+password+"&loginCode=app";
                    httpURLConnection.getOutputStream().write(params.getBytes("UTF-8"));
                    httpURLConnection.getInputStream();

                    inputStream = httpURLConnection.getInputStream();
                    inputStreamReader = new InputStreamReader(inputStream);
                    bufferedReader = new BufferedReader(inputStreamReader);

                    String line = null;
                    StringBuffer result = new StringBuffer();

                    while ((line = bufferedReader.readLine())!=null){
                        line = new String(line.getBytes(),"UTF-8");
                        result.append(line);
                    }
                    if (httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                        resolveResult(result.toString());
                    }
                }catch (Exception e){
                    try {
                        inputStream.close();
                        inputStreamReader.close();
                        bufferedReader.close();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private  void resolveResult(String result){
        try{
            JSONObject jsonObject = new JSONObject(result);
            int status = jsonObject.getInt("status");
            String msg = jsonObject.getString("msg");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
                    if (status==1){
                        startActivity(new Intent(LoginActivity.this,LoginSuccessActivity.class));
                        finish();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
