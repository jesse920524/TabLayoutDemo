package com.vspa.flowlayoutdemo.mainpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.vspa.flowlayoutdemo.R;

/**
 * @author fuy-neu@reachauto.com
 * @package com.vspa.flowlayoutdemo.mainpage
 * @date 2022/6/14 15:56
 * @desc 首页 container of CarHomeFragment
 */
public class MainPageActivity extends AppCompatActivity {
    private static final String TAG = "MainPageActivity";

    public static void actionStart(@NonNull Context context){
        Intent intent = new Intent(context, MainPageActivity.class);
        context.startActivity(intent);
    }

    private String[] vehicleList;//车辆列表
    private FragmentContainerView fragmentContainerView;
    private DialogInterface.OnClickListener dlgClickListener;
    private TextView tvCurrVehicle;
    private SwipeRefreshLayout srl;

    private MainPageVM vm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main_page);
        vehicleList = new String[]{"3.0.1", "3.0.2", "4.0.1", "4.0.2"};
        dlgClickListener = (dialog, which) -> {
            Log.d(TAG, "onCreate: " + which);
            tvCurrVehicle.setText(String.format("当前车辆: %s", vehicleList[which]));
            dialog.dismiss();
            vm.switchVehicle(vehicleList[which]);
        };

        tvCurrVehicle = findViewById(R.id.tv_curr_vehicle);
        tvCurrVehicle.setText(String.format("当前车辆: %s", vehicleList[0]));
        findViewById(R.id.btn_switch_vehicle)
                .setOnClickListener(v->{
                    new AlertDialog.Builder(this)
                            .setItems(vehicleList, dlgClickListener).show();
                });
        srl = findViewById(R.id.srl);
        srl.setOnRefreshListener(() -> {
            Log.d(TAG, "refresh:");
            //模拟调接口
            vm.requestRemoteData();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        vm = new ViewModelProvider(this).get(MainPageVM.class);
        vm.remoteDataChanged()
                .observe(this, data->{
                    Log.d(TAG, "onCreate: MainPageActivity收到http response: "+ data);
                    if (null != srl && srl.isRefreshing()) srl.setRefreshing(false);
                });
    }
}
