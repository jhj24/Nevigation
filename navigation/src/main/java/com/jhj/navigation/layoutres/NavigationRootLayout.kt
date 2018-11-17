package com.jhj.navigation.layoutres

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes

/**
 * ViewPager　和 导航栏所在的布局
 *
 * Created by jhj on 18-11-15.
 */
interface NavigationRootLayout {

    /**
     * activity 布局的 layoutRes
     */
    @LayoutRes
    fun getNavigationLayoutRes(): Int

    /**
     * ViewPager 的 id
     */
    @IdRes
    fun getNavigationViewPagerId(): Int


    /**
     * 导航栏的父布局　id
     */
    @IdRes
    fun getNavigationBarId(): Int
}