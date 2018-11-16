package com.jhj.navigation.layoutres

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes

interface NavigationBarLayout {

    @LayoutRes
    fun getNavigationBarLayoutRes(): Int

    @IdRes
    fun getNavigationBarDefaultImageId(): Int

    @IdRes
    fun getNavigationBarSelectedImageId(): Int

    @IdRes
    fun getNavigationBarDefaultTextId(): Int

    @IdRes
    fun getNavigationBarSelectedTextId(): Int

}