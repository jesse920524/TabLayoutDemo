package com.vspa.flowlayoutdemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executors;

/**
 * @author fuy-neu@reachauto.com
 * @package com.vspa.flowlayoutdemo
 * @date 2022/6/10 13:08
 * @desc TODO
 */
public class UserAct extends AppCompatActivity {
    private static final String TAG = "UserAct";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_user);
        AppManager.getAppManager().addActivity(this);
        findViewById(R.id.btn)
                .setOnClickListener(v -> {
                    getSharedPreferences("sp", MODE_PRIVATE)
                            .edit()
                            .putBoolean("islogin", false)
                            .apply();

                    AppManager.getAppManager().finishAllActivity();
                    Intent i = new Intent(this, LoginAct.class);
                    startActivity(i);

//                    //开个线程结束其他Activity
//                    Executors.newSingleThreadExecutor()
//                            .execute(()-> AppManager.getAppManager().finishOthers(LoginAct.class));
                });

    }
}
