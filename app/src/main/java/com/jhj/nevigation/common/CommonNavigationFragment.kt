package com.jhj.nevigation.common

import android.os.Bundle
import android.view.View
import com.jhj.navigation.base.BaseCommonNavigationFragment
import com.jhj.nevigation.R
import kotlinx.android.synthetic.main.fragment_navigation.view.*

class CommonNavigationFragment : BaseCommonNavigationFragment() {


    override val navigationText: String
        get() = arguments?.getString("title") ?: "标题"
    override val imageResource: Int
        get() = arguments?.getInt("imageDefault") ?: R.mipmap.main_me0
    override val fragmentLayoutRes: Int
        get() = R.layout.fragment_navigation


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val status = arguments?.getInt("status")
        view.textView.text = status.toString()

    }

}