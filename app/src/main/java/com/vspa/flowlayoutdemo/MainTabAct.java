package com.vspa.flowlayoutdemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vspa.flowlayoutdemo.mainpage.MainPageActivity;

/**
 * @author fuy-neu@reachauto.com
 * @package com.vspa.flowlayoutdemo
 * @date 2022/6/10 13:08
 * @desc TODO
 */
public class MainTabAct extends AppCompatActivity {
    private static final String TAG = "MainTabAct";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mt);
        AppManager.getAppManager().addActivity(this);
        findViewById(R.id.btn).setOnClickListener(v->{
            Intent i = new Intent(this, UserAct.class);
            startActivity(i);
        });

        MainPageActivity.actionStart(this);
    }
}
