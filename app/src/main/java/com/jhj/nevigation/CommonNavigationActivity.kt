package com.jhj.nevigation

import android.os.Bundle
import com.jhj.navigation.base.BaseCommonNavigationActivity
import com.jhj.navigation.base.BaseCommonNavigationFragment
import com.jhj.navigation.layoutres.CommonNavigationBarLayout
import com.jhj.navigation.layoutres.NavigationRootLayout
import kotlinx.android.synthetic.main.activity_navigation.*

class CommonNavigationActivity : BaseCommonNavigationActivity() {

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

    override val navigationBarLayout: CommonNavigationBarLayout
        get() = object : CommonNavigationBarLayout {
            override fun getNavigationBarImageViewId(): Int? {
                return null
            }

            override fun getNavigationBarTextViewId(): Int? {
                return R.id.itemTitleDefault
            }

            override fun getNavigationBarLayoutRes(): Int {
                return R.layout.layout_buttom_item1;
            }

        }
    override val fragmentList: List<BaseCommonNavigationFragment>
        get() {
            val list = arrayListOf<BaseCommonNavigationFragment>()
            list.add(getFragment(0, "首页", R.drawable.bg_image_selected))
            list.add(getFragment(1, "通讯录", R.mipmap.main_contacts_0))
            list.add(getFragment(2, "功能", R.mipmap.main_function0))
            list.add(getFragment(3, "我的", R.mipmap.main_me0))
            return list
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager.offscreenPageLimit = 3
        viewPager.currentItem = 2


    }

    private fun getFragment(status: Int, title: String, image: Int): CommonNavigationFragment {
        val fragment = CommonNavigationFragment()
        val bundle = Bundle()
        bundle.putInt("status", status)
        bundle.putString("title", title)
        bundle.putInt("imageDefault", image)
        fragment.arguments = bundle
        return fragment
    }

}