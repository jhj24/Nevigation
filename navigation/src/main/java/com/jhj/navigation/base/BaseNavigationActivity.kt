package com.jhj.navigation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.jhj.navigation.layoutres.NavigationBarLayout
import com.jhj.navigation.layoutres.NavigationLayout
import com.jhj.navigation.pagechangelistener.GradientPageChangeListener

abstract class BaseNavigationActivity : AppCompatActivity() {


    abstract val layoutRes: NavigationLayout
    abstract val layoutBottomBarRes: NavigationBarLayout
    abstract val fragmentList: List<BaseNavigationFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes.getNavigationLayoutRes())
        val viewPager = findViewById<ViewPager>(layoutRes.getNavigationViewPagerId())
        val navigationBar = findViewById<LinearLayout>(layoutRes.getNavigationBottomBarId())
        viewPager.adapter = MyPageAdapter(supportFragmentManager, fragmentList)
        viewPager.addOnPageChangeListener(GradientPageChangeListener(viewPager, fragmentList, layoutBottomBarRes, navigationBar))
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