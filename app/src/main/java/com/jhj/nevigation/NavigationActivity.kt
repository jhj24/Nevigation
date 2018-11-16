package com.jhj.nevigation

import android.os.Bundle
import com.jhj.navigation.base.BaseNavigationActivity
import com.jhj.navigation.base.BaseNavigationFragment
import com.jhj.navigation.layoutres.NavigationBarLayout
import com.jhj.navigation.layoutres.NavigationLayout
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : BaseNavigationActivity() {

    override val layoutRes: NavigationLayout
        get() = object : NavigationLayout {

            override fun getNavigationLayoutRes(): Int {
                return R.layout.activity_navigation
            }

            override fun getNavigationViewPagerId(): Int {
                return R.id.viewPager
            }

            override fun getNavigationBottomBarId(): Int {
                return R.id.layout_navigation
            }

        }

    override val layoutBottomBarRes: NavigationBarLayout
        get() = object : NavigationBarLayout {
            override fun getNavigationBarLayoutRes(): Int {
                return R.layout.layout_buttom_item;
            }

            override fun getNavigationBarDefaultImageId(): Int {
                return R.id.imageViewDefault
            }

            override fun getNavigationBarSelectedImageId(): Int {
                return R.id.imageViewDefault
            }

            override fun getNavigationBarDefaultTextId(): Int {
                return R.id.itemTitleDefault
            }

            override fun getNavigationBarSelectedTextId(): Int {
                return R.id.itemTitleSelected
            }

        }
    override val fragmentList: List<BaseNavigationFragment>
        get() {
            val list = arrayListOf<BaseNavigationFragment>()
            list.add(getFragment(0, "首页", R.mipmap.main_home0))
            list.add(getFragment(1, "通讯录", R.mipmap.main_contacts_0))
            list.add(getFragment(2, "功能", R.mipmap.main_function0))
            list.add(getFragment(3, "我的", R.mipmap.main_me0))
            return list;
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager.offscreenPageLimit = 1

    }

    private fun getFragment(status: Int, title: String, image: Int): NavigationFragment {
        val fragment = NavigationFragment()
        val bundle = Bundle()
        bundle.putInt("status", status)
        bundle.putString("title", title)
        bundle.putInt("imageDefault", image)
        fragment.arguments = bundle
        return fragment
    }

}