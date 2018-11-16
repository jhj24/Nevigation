package com.jhj.navigation.layoutres

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes

interface NavigationLayout {

    @LayoutRes
    fun getNavigationLayoutRes(): Int

    @IdRes
    fun getNavigationViewPagerId(): Int

    @IdRes
    fun getNavigationBottomBarId(): Int
}