package com.vspa.flowlayoutdemo.mainpage;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executors;
import java.util.logging.Handler;

/**
 * @author fuy-neu@reachauto.com
 * @package com.vspa.flowlayoutdemo.mainpage
 * @date 2022/6/14 16:59
 * @desc TODO
 */
public class MainPageVM extends ViewModel {
    MutableLiveData<String> strData = new MutableLiveData<>();
    MutableLiveData<String> switchVehicleData = new MutableLiveData<>();

    MutableLiveData<ChildEvent> childEventData = new MutableLiveData<>();

    /**模拟接口请求*/
    public void requestRemoteData(){
        Executors.newSingleThreadExecutor().execute(()->{
            try {
                Thread.sleep(1000);
                String s = "http result";
                strData.postValue(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    /**接口请求监听*/
    public LiveData<String> remoteDataChanged(){
        return strData;
    }

    /**切车事件*/
    public void switchVehicle(String vehicle){
        switchVehicleData.setValue(vehicle);
    }

    /**切车事件 监听*/
    public LiveData<String> switchVehicleDataChanged(){
        return switchVehicleData;
    }

    /**子fragment发送事件给parent*/
    public void send2Parent(ChildEvent event){
        childEventData.postValue(event);
    }

    /**监听子fragment发送的事件*/
    public LiveData<ChildEvent> chileDataChanged(){ return childEventData; }

    public static class ChildEvent{
        public Class<? extends Fragment> clazz;
        public String msg;

        public ChildEvent(Class<? extends Fragment> clazz, String msg) {
            this.clazz = clazz;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "ChildEvent{" +
                    "clazz=" + clazz.getSimpleName() +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
