package com.jhj.nevigation.common

import android.os.Bundle
import com.jhj.navigation.base.BaseCommonNavigationActivity
import com.jhj.navigation.layout.CommonNavigationBarLayout
import com.jhj.navigation.layout.NavigationRootLayout
import com.jhj.navigation.model.NavigationModel
import com.jhj.nevigation.fragment.CommonFragment
import com.jhj.nevigation.R

class CommonTextNavigationActivity : BaseCommonNavigationActivity() {
    override val navigationList: List<NavigationModel>
        get() {
            val list = arrayListOf<NavigationModel>()
            list.add(NavigationModel(getFragment(0, "首页"), "首页", R.drawable.bg_contacts_selected))
            list.add(NavigationModel(getFragment(1, "通讯录"), "通讯录", R.drawable.bg_contacts_selected))
            list.add(NavigationModel(getFragment(2, "功能"), "功能", R.drawable.bg_function_selected))
            list.add(NavigationModel(getFragment(3, "我的"), "我的", R.drawable.bg_me_selected))
            return list
        }


    override val rootLayout: NavigationRootLayout
        get() = object : NavigationRootLayout {

            override fun getNavigationLayoutRes(): Int {
                return R.layout.activity_navigation_text
            }

            override fun getNavigationViewPagerId(): Int {
                return R.id.viewPager
            }

            override fun getNavigationBarId(): Int {
                return R.id.layout_navigation
            }

        }

    override val navigationBarLayout: CommonNavigationBarLayout
        get() = object : CommonNavigationBarLayout {
            override fun getNavigationBarImageViewId(): Int? {
                return null
            }

            override fun getNavigationBarTextViewId(): Int? {
                return R.id.itemTitleDefault
            }

            override fun getNavigationBarLayoutRes(): Int {
                return R.layout.layout_top_text_item
            }

        }


    private fun getFragment(status: Int, title: String): CommonFragment {
        val fragment = CommonFragment()
        val bundle = Bundle()
        bundle.putInt("status", status)
        bundle.putString("title", title)
        fragment.arguments = bundle
        return fragment
    }

}