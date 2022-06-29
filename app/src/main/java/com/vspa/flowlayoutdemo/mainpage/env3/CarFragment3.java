package com.vspa.flowlayoutdemo.mainpage.env3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.vspa.flowlayoutdemo.R;
import com.vspa.flowlayoutdemo.mainpage.MainPageVM;

/**
 * @author fuy-neu@reachauto.com
 * @package com.vspa.flowlayoutdemo.mainpage.env3
 * @date 2022/6/14 15:59
 * @desc 3.0 车辆Fragment
 */
public class CarFragment3 extends Fragment {
    private static final String TAG = "CarFragment3";

    public static CarFragment3 newInstance(){
        CarFragment3 instance = new CarFragment3();
        return instance;
    }

    View root;
    private MainPageVM vm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        root = inflater.inflate(R.layout.frag_car3, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(requireActivity())
                .get(MainPageVM.class);
        root.findViewById(R.id.btn_send)
                .setOnClickListener(v->{
                    vm.send2Parent(new MainPageVM.ChildEvent(CarFragment3.class, "早点下班"));
                });
//        observer = s -> Log.d(TAG, "onStart: CarFragment3收到httpResponse: " + s);
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

    private Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            Log.d(TAG, "onStart: CarFragment3收到httpResponse: " + s);
        }
    };
}
