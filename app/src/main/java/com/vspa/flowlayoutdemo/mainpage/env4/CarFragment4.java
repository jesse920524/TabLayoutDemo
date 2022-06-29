package com.vspa.flowlayoutdemo.mainpage.env4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.vspa.flowlayoutdemo.R;
import com.vspa.flowlayoutdemo.mainpage.MainPageVM;
import com.vspa.flowlayoutdemo.mainpage.env3.CarFragment3;

/**
 * @author fuy-neu@reachauto.com
 * @package com.vspa.flowlayoutdemo.mainpage.env4
 * @date 2022/6/14 16:00
 * @desc 4.0 车辆Fragment
 */
public class CarFragment4 extends Fragment {
    private static final String TAG = "CarFragment4";

    public static CarFragment4 newInstance(){
        CarFragment4 instance = new CarFragment4();
        return instance;
    }

    View root;
    private MainPageVM vm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.frag_car4, container, false);
        Log.d(TAG, "onCreateView: ");
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(requireActivity())
                .get(MainPageVM.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        vm.remoteDataChanged()
                .observe(this, observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        vm.remoteDataChanged().removeObserver(observer);
    }

//    private Observer<String> observer = s -> Log.d(TAG, "onStart: CarFragment4收到httpResponse: " + s);
    private Observer<String> observer = new Observer<String>() {
    @Override
    public void onChanged(String s) {
        Log.d(TAG, "onStart: CarFragment4收到httpResponse: " + s);
    }
};
}
