# Nevigation
导航栏

分两种导航栏

- 默认样式，滑动到指定位置时，设置选中为选中颜色
- 渐变样式，随手指滑动界面的百分比逐渐变化

导航的实现关键在于监听器的实现

- CommonPageChangeListener(val viewPager: ViewPager,val fragmentList: List<Fragment>,val navigationBarItemList: List<NavigationBarItem>)

- GradientPageChangeListener(val viewPager: ViewPager,val fragmentList: List<Fragment>,val navigationBarItemList: List<NavigationBarItem>)

```
data class NavigationBarItem(
        var textViewDefault: TextView? = null,  // 默认样式，设置如下颜色selector样式；  渐变色时，TextView的textColor为默认样式
        var textViewSelected: TextView? = null, // 模式样式，为null即可； 渐变色时，TextView的textColor为选中样式
        var imageViewDefault: ImageView? = null // 默认样式时，设置如下图片selector颜色； 渐变色时，设置默认图片；
)
```


- 颜色selector样式
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <!--选中的颜色-->
    <item android:color="#00A0E0" android:state_selected="true" />

    <!--默认的颜色-->
    <item android:color="#999999" />

</selector>
```
- 图片selector样式
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <!--选中的颜色-->
    <item android:drawable="@drawable/main_contacts_1" android:state_selected="true" />

    <!--默认的颜色-->
    <item android:drawable="@drawable/main_contacts_0" />

</selector>
```
实现

- 继承`BaseCommonNavigationActivity` 或 `BaseGradientNavitationActivity` 实现里面的方法即可

- 继承`FragmentActivity`或其子类，设置`addOnPageChangeListener()` 监听器,


