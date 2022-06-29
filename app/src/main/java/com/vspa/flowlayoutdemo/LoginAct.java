package com.vspa.flowlayoutdemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * @author fuy-neu@reachauto.com
 * @package com.vspa.flowlayoutdemo
 * @date 2022/6/10 13:07
 * @desc TODO
 */
public class LoginAct extends AppCompatActivity {
    private static final String TAG = "LoginAct";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        AppManager.getAppManager().addActivity(this);

        findViewById(R.id.btn_login)
                .setOnClickListener(v -> {
                    getSharedPreferences("sp", MODE_PRIVATE)
                            .edit()
                            .putBoolean("islogin", true)
                            .apply();

                    Intent i = new Intent(this, MainTabAct.class);
                    startActivity(i);
                    finish();
                });
    }
}
