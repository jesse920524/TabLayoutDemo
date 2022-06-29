package com.vspa.flowlayoutdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fuy-neu@reachauto.com
 * @package com.vspa.flowlayoutdemo
 * @date 2022/6/9 14:06
 * @desc TODO
 */
public class SplashAct extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    FlowLayout flowLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        AppManager.getAppManager().addActivity(this);
        flowLayout = findViewById(R.id.fl);
        flowLayout.setMaxRows(1);
        init();
    }

    private void init(){
        List<String> list = new ArrayList();
//        list.add("92# 充200每升减0.1元  相当于：¥ 8.23 /L");
        list.add("充1000送100");
        list.add("充2000送300");
        list.add("11");
        list.add("充2000送300");
        list.add("11");
        list.add("充2000送300");
        list.add("充2000送300");

        for(String it : list){
            TextView tv = new TextView(this);
            tv.setText(it);
            tv.setBackgroundResource(R.drawable.bg_refuel_label);
            tv.setTextSize(12);
            tv.setTextColor(Color.parseColor("#FF6546"));
            tv.setHeight(dp2px(getApplicationContext(), 28));
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(dp2px(getApplicationContext(), 17), 0, dp2px(getApplicationContext(), 17), 0);
            flowLayout.addView(tv);
        }

        new Handler().postDelayed(()->{
            SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
            boolean isLogin = sp.getBoolean("islogin", false);
            Intent i;
            if (!isLogin){
                i = new Intent(this, LoginAct.class);
            }else{
                i = new Intent(this, MainTabAct.class);
            }
            startActivity(i);
            finish();
        }, 1000);
    }

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
