# <center> 智导互联自定义TabLayout指南</center>



> author: 傅堯
>
> latest update: 2022年6月28日



## Preview

此文档旨在为智导互联项目TabLayout的自定义提供指南. 

在近日(2022年6月29日)的3.0/4.0融合中, CarHomeFragment由原来的com.honda.thridlibrary.flycoTabLayout_Lib.SlidingTabLayout替换为原生TabLayout自定义的方式, 后者与ViewPager2有良好的相性与扩展性, 更符合业务需求.



## TabLayout与ViewPager2联动

```java
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
```

## 自定义TabLayout

```xml
    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tab_layout"
        android:layout_width="200dp"
        android:layout_height="36dp"
        app:tabIndicator="@drawable/bg_mainpage_tab_indicator"//indicator样式
        app:tabIndicatorColor="#333333"//indicator颜色
        android:layout_centerHorizontal="true"
        app:tabSelectedTextColor="#333333"//选中文字颜色
        app:tabTextColor="#999999"//非选中文字颜色
        app:tabRippleColor="@null"//点击波纹
/>
```

* Indicator的尺寸, 圆角, 是用layer-list实现的.

* indicator的颜色, 由原生apitabIndicatorColor指定.

* tab的文字样式(大小, 颜色), 是通过类TablayoutExt.kt实现的. 此类基于Kotlin扩展函数: 在不染指TabLayout继承结构的基础上, 为TabLayout类附加功能.

  

## 参考

[ViewPager2&TabLayout：拓展出一个文本选中放大效果 - 掘金 (juejin.cn)](https://juejin.cn/post/7009562779895988255)

[【Android】TabLayout 自定义指示器 Indicator 样式 - 掘金 (juejin.cn)](https://juejin.cn/post/6844903860587200526#heading-7)

[优雅地修改 TabLayout 指示线 Indicator 的宽度 - 掘金 (juejin.cn)](https://juejin.cn/post/6844903834163085326)



