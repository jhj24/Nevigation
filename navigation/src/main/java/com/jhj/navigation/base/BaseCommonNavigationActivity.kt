package com.jhj.navigation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jhj.navigation.layoutres.CommonNavigationBarLayout
import com.jhj.navigation.layoutres.NavigationRootLayout
import com.jhj.navigation.model.NavigationBarItem
import com.jhj.navigation.pagechangelistener.CommonPageChangeListener

/**
 * 普通基础 Activity
 *
 * Created by jhj on 18-11-15.
 */
abstract class BaseCommonNavigationActivity : AppCompatActivity() {


    abstract val rootLayout: NavigationRootLayout
    abstract val navigationBarLayout: CommonNavigationBarLayout
    abstract val fragmentList: List<BaseCommonNavigationFragment>

    open val pageChangeListener: ViewPager.OnPageChangeListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootLayout.getNavigationLayoutRes())

        val navigationBarItemList = arrayListOf<NavigationBarItem>()
        val viewPager = findViewById<ViewPager>(rootLayout.getNavigationViewPagerId())
        val navigationBar = findViewById<ViewGroup>(rootLayout.getNavigationBarId())
        val inflater = LayoutInflater.from(this)
        viewPager.adapter = MyPageAdapter(supportFragmentManager, fragmentList)

        for (i in fragmentList.indices) {
            val fragment = fragmentList[i]
            val view = inflater.inflate(navigationBarLayout.getNavigationBarLayoutRes(), navigationBar, false)
            val textDefault = navigationBarLayout.getNavigationBarTextViewId()?.let {
                view.findViewById<TextView>(it)
            }
            val imageViewDefault = navigationBarLayout.getNavigationBarImageViewId()?.let {
                view.findViewById<ImageView>(it)
            }
            val navigationBarItem = NavigationBarItem(textViewDefault = textDefault, imageViewDefault = imageViewDefault)

            view.setOnClickListener {
                viewPager.setCurrentItem(i, false)
            }
            navigationBar.addView(view)
            navigationBarItem.textViewDefault?.text = fragment.navigationText
            fragment.imageResource?.let {
                navigationBarItem.imageViewDefault?.setImageResource(it)
            }
            navigationBarItemList.add(navigationBarItem)
        }


        val listener = CommonPageChangeListener(viewPager, fragmentList, navigationBarItemList)
        listener.setOnPageChangeListener(pageChangeListener)
        viewPager.addOnPageChangeListener(listener)
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