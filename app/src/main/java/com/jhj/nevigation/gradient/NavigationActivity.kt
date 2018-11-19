package com.jhj.nevigation.gradient

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.jhj.navigation.base.BaseGradientNavigationActivity
import com.jhj.navigation.layout.GradientNavigationBarLayout
import com.jhj.navigation.layout.NavigationRootLayout
import com.jhj.navigation.model.NavigationModel
import com.jhj.nevigation.fragment.CommonFragment
import com.jhj.nevigation.R

class NavigationActivity : BaseGradientNavigationActivity() {


    override val rootLayout: NavigationRootLayout
        get() = object : NavigationRootLayout {

            override fun getNavigationLayoutRes(): Int {
                return R.layout.activity_navigation
            }

            override fun getNavigationViewPagerId(): Int {
                return R.id.viewPager
            }

            override fun getNavigationBarId(): Int {
                return R.id.layout_navigation
            }

        }

    override val navigationBarLayout: GradientNavigationBarLayout
        get() = object : GradientNavigationBarLayout {
            override fun getNavigationBarLayoutRes(): Int {
                return R.layout.layout_buttom_item;
            }

            override fun getNavigationBarImageViewId(): Int {
                return R.id.imageViewDefault
            }

            override fun getNavigationBarDefaultTextViewId(): Int {
                return R.id.itemTitleDefault
            }

            override fun getNavigationBarSelectedTextViewId(): Int {
                return R.id.itemTitleSelected
            }

        }

    override val navigationList: List<NavigationModel>
        get() {
            val list = arrayListOf<NavigationModel>()
            list.add(NavigationModel(getFragment(0, "首页"), "首页", R.mipmap.main_home0))
            list.add(NavigationModel(getFragment(1, "通讯录"), "通讯录", R.mipmap.main_contacts_0))
            list.add(NavigationModel(getFragment(2, "功能"), "功能", R.mipmap.main_function0))
            list.add(NavigationModel(getFragment(3, "我的"), "我的", R.mipmap.main_me0))
            return list
        }

    override val imageSelectedColor: Int?
        get() = ContextCompat.getColor(this, R.color.colorAccent)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun getFragment(status: Int, title: String): Fragment {
        val fragment = CommonFragment()
        val bundle = Bundle()
        bundle.putInt("status", status)
        bundle.putString("title", title)
        fragment.arguments = bundle
        return fragment
    }

}