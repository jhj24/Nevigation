package com.jhj.navigation.layoutres

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes

/**
 * 普通的底部导航栏　Layout　信息
 *
 * Created by jhj on 18-11-15.
 */
interface CommonNavigationBarLayout {

    @LayoutRes
    fun getNavigationBarLayoutRes(): Int

    @IdRes
    fun getNavigationBarImageViewId(): Int?

    @IdRes
    fun getNavigationBarTextViewId(): Int?

}