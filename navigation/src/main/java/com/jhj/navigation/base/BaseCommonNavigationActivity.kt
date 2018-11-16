package com.jhj.navigation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.jhj.navigation.layoutres.CommonNavigationBarLayout
import com.jhj.navigation.layoutres.NavigationLayout
import com.jhj.navigation.pagechangelistener.CommonPageChangeListener

/**
 * 普通基础 Activity
 *
 * Created by jhj on 18-11-15.
 */
abstract class BaseCommonNavigationActivity : AppCompatActivity() {


    abstract val layoutRes: NavigationLayout
    abstract val layoutBottomBarRes: CommonNavigationBarLayout
    abstract val fragmentList: List<BaseCommonNavigationFragment>

    private var pageChangeListener: ViewPager.OnPageChangeListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes.getNavigationLayoutRes())
        val viewPager = findViewById<ViewPager>(layoutRes.getNavigationViewPagerId())
        val navigationBar = findViewById<ViewGroup>(layoutRes.getNavigationBarId())
        viewPager.adapter = MyPageAdapter(supportFragmentManager, fragmentList)
        val listener = CommonPageChangeListener(viewPager, fragmentList, layoutBottomBarRes, navigationBar)
        listener.setOnPageChangeListener(pageChangeListener)
        viewPager.addOnPageChangeListener(listener)
    }


    /**
     * ViewPager.addPageChangeListener运行后的监听器
     */
    fun setOnPageChangerListener(listener: ViewPager.OnPageChangeListener) {
        this.pageChangeListener = listener
    }


    class MyPageAdapter(fragmentManager: FragmentManager, val fragmentList: List<Fragment>) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(p0: Int): Fragment {
            return fragmentList[p0]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }
    }


}