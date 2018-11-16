package com.jhj.navigation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 基础 Fragment
 */
abstract class BaseGradientNavigationFragment : Fragment() {

    abstract val title: String

    abstract val iconDefault: Int

    abstract val navigationBarLayoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(navigationBarLayoutRes, container, false)
    }
}