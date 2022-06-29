package com.vspa.flowlayoutdemo.mainpage.env3;

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

/**
 * @author fuy-neu@reachauto.com
 * @package com.vspa.flowlayoutdemo.mainpage.env3
 * @date 2022/6/14 16:00
 * @desc 3.0蓝牙Fragment
 */
public class BTFragment extends Fragment {
    private static final String TAG = "BTFragment";

    public static BTFragment newInstance(){
        BTFragment instance = new BTFragment();
        return instance;
    }

    private View root;
    private MainPageVM vm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        root = inflater.inflate(R.layout.frag_bt3, container, false);
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
//        vm.remoteDataChanged()
//                .observe(this, observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        vm.remoteDataChanged()
                .removeObserver(observer);
    }

    private Observer<String> observer = s -> Log.d(TAG, "onStart: BTFragment收到http response: " + s);
}
