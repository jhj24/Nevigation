package com.jhj.navigation.layoutres

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes

/**
 * 底部导航栏成渐变色的　Layout　信息
 *
 * Created by jhj on 18-11-15.
 */
interface GradientNavigationBarLayout {

    @LayoutRes
    fun getNavigationBarLayoutRes(): Int

    @IdRes
    fun getNavigationBarImageViewId(): Int?

    @IdRes
    fun getNavigationBarDefaultTextViewId(): Int?

    @IdRes
    fun getNavigationBarSelectedTextViewId(): Int?

}