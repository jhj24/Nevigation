package com.jhj.navigation.base

import android.os.Bundle
import android.support.annotation.ColorInt
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
import com.jhj.navigation.model.NavigationModel
import com.jhj.navigation.pagechangelistener.GradientPageChangeListener

/**
 * 底部导航栏成渐变色变化的基础 Activity
 *
 * Created by jhj on 18-11-15.
 */
abstract class BaseGradientNavigationActivity : AppCompatActivity() {


    abstract val rootLayout: NavigationRootLayout
    abstract val navigationBarLayout: GradientNavigationBarLayout
    abstract val navigationList: List<NavigationModel>

    open val pageChangeListener: ViewPager.OnPageChangeListener? = null

    @ColorInt
    open val imageSelectedColor: Int? = null

    private val navigationBarItemList = arrayListOf<NavigationBarItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootLayout.getNavigationLayoutRes())
        val inflater = LayoutInflater.from(this)
        val viewPager = findViewById<ViewPager>(rootLayout.getNavigationViewPagerId())
        val navigationBar = findViewById<ViewGroup>(rootLayout.getNavigationBarId())

        navigationList.forEachIndexed { index, navigationModel ->

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
                viewPager.setCurrentItem(index, false)
            }
            navigationBar.addView(view)

            navigationModel.navigationBarText?.let {
                navigationBarItem.textViewSelected?.text = it
                navigationBarItem.textViewDefault?.text = it
            }
            navigationModel.navigationBarImageResource?.let {
                navigationBarItem.imageViewDefault?.setImageResource(it)
            }
            navigationBarItemList.add(navigationBarItem)

        }

        viewPager.adapter = MyPageAdapter(supportFragmentManager, navigationList.map { it.fragment })
        val listener = GradientPageChangeListener(viewPager, navigationList.map { it.fragment }, navigationBarItemList)
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