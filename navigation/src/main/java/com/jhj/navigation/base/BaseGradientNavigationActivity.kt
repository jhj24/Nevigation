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
import com.jhj.navigation.layoutres.GradientNavigationBarLayout
import com.jhj.navigation.layoutres.NavigationRootLayout
import com.jhj.navigation.model.NavigationBarItem
import com.jhj.navigation.pagechangelistener.GradientPageChangeListener

/**
 * 底部导航栏成渐变色变化的基础 Activity
 *
 * Created by jhj on 18-11-15.
 */
abstract class BaseGradientNavigationActivity : AppCompatActivity() {


    abstract val rootLayout: NavigationRootLayout
    abstract val navigationBarLayout: GradientNavigationBarLayout
    abstract val fragmentList: List<BaseGradientNavigationFragment>

    open val pageChangeListener: ViewPager.OnPageChangeListener? = null
    open val imageSelectedColor: Int? = null

    private val navigationBarItemList = arrayListOf<NavigationBarItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootLayout.getNavigationLayoutRes())
        val inflater = LayoutInflater.from(this)
        val viewPager = findViewById<ViewPager>(rootLayout.getNavigationViewPagerId())
        val navigationBar = findViewById<ViewGroup>(rootLayout.getNavigationBarId())


        for (i in fragmentList.indices) {
            val fragment = fragmentList[i]
            val view = inflater.inflate(navigationBarLayout.getNavigationBarLayoutRes(), navigationBar, false)
            val textDefault = navigationBarLayout.getNavigationBarDefaultTextViewId()?.let {
                view.findViewById<TextView>(it)
            }
            val textSelected = navigationBarLayout.getNavigationBarSelectedTextViewId()?.let {
                view.findViewById<TextView>(it)
            }
            val imageViewDefault = navigationBarLayout.getNavigationBarImageViewId()?.let {
                view.findViewById<ImageView>(it)
            }
            val navigationBarItem = NavigationBarItem(textDefault, textSelected, imageViewDefault)

            view.setOnClickListener {
                viewPager.setCurrentItem(i, false)
            }
            navigationBar.addView(view)
            navigationBarItem.textViewSelected?.text = fragment.navigationText
            navigationBarItem.textViewDefault?.text = fragment.navigationText
            fragment.imageDefault?.let {
                navigationBarItem.imageViewDefault?.setImageResource(it)
            }
            navigationBarItemList.add(navigationBarItem)
        }


        val listener = GradientPageChangeListener(viewPager, fragmentList, navigationBarItemList)
        viewPager.adapter = MyPageAdapter(supportFragmentManager, fragmentList)
        listener.setGradientResultColor(imageSelectedColor)
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