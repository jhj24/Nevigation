package com.jhj.nevigation

import android.os.Bundle
import android.view.View
import com.jhj.navigation.base.BaseCommonNavigationFragment
import kotlinx.android.synthetic.main.fragment_navigation.view.*

class CommonNavigationFragment : BaseCommonNavigationFragment() {


    override val title: String
        get() = arguments?.getString("title") ?: "标题"
    override val iconDrawableRes: Int
        get() = arguments?.getInt("imageDefault") ?: R.mipmap.main_me0
    override val navigationBarLayoutRes: Int
        get() = R.layout.fragment_navigation


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val status = arguments?.getInt("status")
        view.textView.text = status.toString()

    }

}