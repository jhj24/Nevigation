package com.jhj.navigation.model

import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment

data class NavigationModel(
        val fragment: Fragment,
        val navigationBarText: String? = null,
        val navigationBarImageResource: Int? = null
)