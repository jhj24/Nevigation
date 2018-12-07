# Nevigation

通过ViewPager的滑动监听器`addPagerChangeListener()`来设置导航栏，在此封装了有两种样式的滑动监听

### 1. 样式

- 默认样式，滑动到指定位置时，设置选中为选中颜色

```java
CommonPageChangeListener( ViewPager,List<Fragment>,List<NavigationBarItem>)
```

- 渐变样式，随手指滑动界面的百分比逐渐变化

```java
GradientPageChangeListener(ViewPager,List<Fragment>,List<NavigationBarItem>)
```


#### 2. NavigationBarItem 中参数解析

``` kotlin
data class NavigationBarItem(
     var textViewDefault: TextView? = null, 
     var textViewSelected: TextView? = null, 
     var imageViewDefault: ImageView? = null 
)
```

|| 默认样式 | 渐变样式
|------|------|------
|textViewDefault|文字TextView控件，设置textColor为selector样式 | 未选中时的TextView控件，设置textColor未选中时颜色
|textViewSelected|为null即可 |选中时的TextView控件，设置textColor选中时颜色
|imageViewDefault|图片ImageView控件，图片selector样式 |图片ImageView控件，设置默认图片

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

### 3. listener公开方法

- setOnPageChangeListener(listener: ViewPager.OnPageChangeListener?) // 滑动监听
- setGradientResultColor(@ColorRes color: Int?) // 渐变导航栏，图片选择时的颜色
- setItemSelectedImgBigger(isBigger: Boolean) // item选中时，图片是否变大




