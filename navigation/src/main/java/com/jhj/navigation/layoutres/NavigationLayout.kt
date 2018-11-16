package com.jhj.navigation.layoutres

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes

/**
 * ViewPager　和 导航栏所在的布局
 *
 * Created by jhj on 18-11-15.
 */
interface NavigationLayout {

    /**
     * ViewPager　和 导航栏所在的布局res
     */
    @LayoutRes
    fun getNavigationLayoutRes(): Int

    @IdRes
    fun getNavigationViewPagerId(): Int

    @IdRes
    fun getNavigationBarId(): Int
}