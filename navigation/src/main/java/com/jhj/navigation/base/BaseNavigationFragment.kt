package com.jhj.navigation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseNavigationFragment : Fragment() {

    abstract val title: String

    abstract val iconDefault: Int

    abstract val bottomBarRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(bottomBarRes, container, false)
    }
}