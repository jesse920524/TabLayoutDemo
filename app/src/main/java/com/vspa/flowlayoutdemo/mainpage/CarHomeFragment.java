package com.vspa.flowlayoutdemo.mainpage;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.vspa.flowlayoutdemo.R;
import com.vspa.flowlayoutdemo.TabLayoutExtKt;
import com.vspa.flowlayoutdemo.TextScaleConfig;
import com.vspa.flowlayoutdemo.TextScaleTabViewConfig;
import com.vspa.flowlayoutdemo.ToolsExtKt;
import com.vspa.flowlayoutdemo.mainpage.env3.BTFragment;
import com.vspa.flowlayoutdemo.mainpage.env3.CarFragment3;
import com.vspa.flowlayoutdemo.mainpage.env4.CarFragment4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fuy-neu@reachauto.com
 * @package com.vspa.flowlayoutdemo.mainpage
 * @date 2022/6/14 15:57
 * @desc 父Fragment. Container of 3.0 CarFragment/BTFragment, 4.0 CarFragment/BTFragment
 */
public class CarHomeFragment extends Fragment {
    private static final String TAG = "CarHomeFragment";

    public static CarHomeFragment newInstance(){
        Bundle bundle = new Bundle();
        CarHomeFragment fragment = new CarHomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    View root;
    private MainPageVM vm;
    private ViewPager2 viewPager2;
    private List<Fragment> fragmentList;
    private FragmentStateAdapter adapter;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.frag_car_home, container, false);
        viewPager2 = root.findViewById(R.id.vp);
        tabLayout = root.findViewById(R.id.tab_layout);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(requireActivity())
                .get(MainPageVM.class);
        fragmentList = new ArrayList<>();
        /**默认3.0有bt*/
        fragmentList.add(CarFragment3.newInstance());
        fragmentList.add(BTFragment.newInstance());
        adapter = new FragmentStateAdapter(requireActivity()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getItemCount() {
                return fragmentList.size();
            }

            @Override
            public long getItemId(int position) {
                return fragmentList.get(position).hashCode();
            }

            @Override
            public boolean containsItem(long itemId) {
                boolean b = false;
                for (Fragment it: fragmentList){
                    if (it.hashCode() == itemId){
                        b = true;
                        break;
                    }
                }
                return b;
            }
        };
        viewPager2.setAdapter(adapter);

//        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
//                        TextView tabOne = (TextView) LayoutInflater.from(requireContext()).inflate(R.layout.tab_custom, null);
//                tabOne.setText(generateTabText(tab.getPosition()));
//                tabOne.setGravity(Gravity.CENTER);
//                tabOne.setTextColor(Color.parseColor("#333333"));
//                tabOne.setTextSize(12);
//                tab.setCustomView(tabOne);
//        }).attach();
        TabLayoutExtKt.createMediatorByTextView(tabLayout, viewPager2, new TextScaleTabViewConfig(
                new TextScaleConfig(ToolsExtKt.getDp(14),
                        ToolsExtKt.getDp(12),
                        Color.parseColor("#333333"),
                        Color.parseColor("#999999"),
                        false)) {
            @NonNull
            @Override
            public String getText(int position) {
                return generateTabText(position);
            }

            @Override
            public void onVisibleTextViewInit(@NonNull TextView tv) {
                //empty impl
            }
        }).attach();
    }

    private String generateTabText(int position){
        switch (position){
            default:
            case 0:
                return "远控车况";
            case 1:
                return "手机钥匙";
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        vm.remoteDataChanged()
                .observe(this, s -> Log.d(TAG, "CarHomeFragment收到http response:" + s));

        vm.switchVehicleDataChanged()
                .observe(this, data -> {
                    /**3.0.1: 有CarFragment3, BTFragment
                     * 3.0.2: 有CarFragment3
                     * 4.0.1: 有CarFragment4, BTFragment
                     * 4.0.2: 有CarFragment4*/
                    // TODO: 2022/6/14 1. 反射remove fragment
                    // TODO: 2022/6/14 2. fragment变化时, tablayout 联动
                    fragmentList.clear();
                    adapter.notifyDataSetChanged();
                    switch (data){
                        default:
                        case "3.0.1":
                            fragmentList.add(CarFragment3.newInstance());
                            fragmentList.add(BTFragment.newInstance());
                            adapter.createFragment(0);
                            adapter.createFragment(1);
                            break;
                        case "3.0.2":
                            fragmentList.add(CarFragment3.newInstance());
                            adapter.createFragment(0);
                            break;
                        case "4.0.1":
                            fragmentList.add(CarFragment4.newInstance());
                            fragmentList.add(BTFragment.newInstance());
                            adapter.createFragment(0);
                            adapter.createFragment(1);
                            break;
                        case "4.0.2":
                            fragmentList.add(CarFragment4.newInstance());
                            adapter.createFragment(0);
                            break;
                    }

                    adapter.notifyDataSetChanged();
                });

        vm.chileDataChanged()
                .observe(this, data->{
                    Log.d(TAG, "onStart: 收到子Fragment发来的事件: " + data);
                });
    }

}
