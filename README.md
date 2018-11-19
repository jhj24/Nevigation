# Nevigation
### ViewPager导航栏

### 1. 两种导航栏

- 默认样式，滑动到指定位置时，设置选中为选中颜色
- 渐变样式，随手指滑动界面的百分比逐渐变化

### 2. 方法解析


#### 2.1 监听器
- 默认样式
```java
CommonPageChangeListener(val viewPager: ViewPager,val fragmentList: List<Fragment>,val navigationBarItemList: List<NavigationBarItem>)
```

- 渐变样式
```java
GradientPageChangeListener(val viewPager: ViewPager,val fragmentList: List<Fragment>,val navigationBarItemList: List<NavigationBarItem>)
```
#### 2.2 NavigationBarItem 中参数解析

``` kotlin
data class NavigationBarItem(
     var textViewDefault: TextView? = null, 
     var textViewSelected: TextView? = null, 
     var imageViewDefault: ImageView? = null 
)
```

|| 默认样式 | 渐变样式
|------|------|------
|textViewDefault|字体selector样式，如下 | 未选中时的textColor
|textViewSelected|为null即可 |选中时的textColor
|imageViewDefault|图片selector样式，如下 |未选中时图片

- 字体selector样式
``` xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--选中的颜色-->
    <item android:color="#00A0E0" android:state_selected="true" />
    <!--默认的颜色-->
    <item android:color="#999999" />

</selector>
``` 

- 图片selector样式
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--选中的颜色-->
    <item android:drawable="@drawable/main_contacts_1" android:state_selected="true" />
    <!--默认的颜色-->
    <item android:drawable="@drawable/main_contacts_0" />

</selector>
```

### 实现

- 继承`FragmentActivity`或其子类，设置`addOnPageChangeListener()` 监听器,

- 继承`BaseCommonNavigationActivity` 或 `BaseGradientNavitationActivity` 实现里面的方法即可




