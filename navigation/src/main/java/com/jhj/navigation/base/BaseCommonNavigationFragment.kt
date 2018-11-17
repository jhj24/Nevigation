package com.jhj.navigation.base

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 基础 Fragment
 */
abstract class BaseCommonNavigationFragment : Fragment() {

    abstract val navigationText: String?

    @get:DrawableRes
    abstract val imageResource: Int?

    abstract val fragmentLayoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentLayoutRes, container, false)
    }
}