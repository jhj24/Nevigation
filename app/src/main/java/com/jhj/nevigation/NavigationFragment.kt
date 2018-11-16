package com.jhj.nevigation

import android.os.Bundle
import android.view.View
import com.jhj.navigation.base.BaseNavigationFragment
import kotlinx.android.synthetic.main.fragment_navigation.view.*

class NavigationFragment : BaseNavigationFragment() {


    override val title: String
        get() = arguments?.getString("title") ?: "标题"
    override val iconDefault: Int
        get() = arguments?.getInt("imageDefault") ?: R.mipmap.main_me0
    override val bottomBarRes: Int
        get() = R.layout.fragment_navigation


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val status = arguments?.getInt("status")
        view.textView.text = status.toString()

    }

}