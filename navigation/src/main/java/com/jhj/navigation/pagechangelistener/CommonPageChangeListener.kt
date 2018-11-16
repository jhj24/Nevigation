package com.jhj.navigation.pagechangelistener

import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jhj.navigation.base.BaseCommonNavigationFragment
import com.jhj.navigation.layoutres.CommonNavigationBarLayout


/**
 * 普通的ViewPager变化监听器
 *
 * Created by jhj on 18-11-15.
 */
class CommonPageChangeListener(val viewPager: ViewPager, private val fragmentList: List<BaseCommonNavigationFragment>, val layout: CommonNavigationBarLayout, layoutNavigation: ViewGroup) : ViewPager.OnPageChangeListener {

    private val navigationBarItemList = arrayListOf<NavigationBarItem>()
    private var listener: ViewPager.OnPageChangeListener? = null

    init {
        val layoutRes = layout.getNavigationBarLayoutRes()
        for (i in fragmentList.indices) {
            val fragment = fragmentList[i]
            val view = LayoutInflater.from(layoutNavigation.context).inflate(layoutRes, layoutNavigation, false)
            val textDefault = view.findViewById<TextView>(layout.getNavigationBarTextViewId())
            val imageViewDefault = view.findViewById<ImageView>(layout.getNavigationBarImageViewId())
            val navigationBarItem = NavigationBarItem(textDefault, imageViewDefault)

            view.setOnClickListener {
                viewPager.setCurrentItem(i, false)
            }
            layoutNavigation.addView(view)
            navigationBarItem.textView.text = fragment.title
            navigationBarItem.imageView.setImageResource(fragment.iconDrawableRes)
            navigationBarItemList.add(navigationBarItem)
        }
    }

    fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener?) {
        this.listener = listener
    }


    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        listener?.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        for (i in fragmentList.indices) {
            if (i == position) continue
            val preItem = navigationBarItemList[i]
            setBottomBarItemSelected(preItem, 0f)
            viewPager.tag = position
        }
        val bottomBarItem = navigationBarItemList[position]
        setBottomBarItemSelected(bottomBarItem, 1f)
        listener?.onPageSelected(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        if (ViewPager.SCROLL_STATE_IDLE == state) {
            onPageSelected(viewPager.currentItem)
        }
        listener?.onPageScrollStateChanged(state)
    }


    private fun setBottomBarItemSelected(navigationBarItem: NavigationBarItem, percent: Float) {

        if (percent == 1f) {
            navigationBarItem.textView.isSelected = true
            navigationBarItem.imageView.isSelected = true

        } else {
            navigationBarItem.textView.isSelected = false
            navigationBarItem.imageView.isSelected = false
        }
    }

    data class NavigationBarItem(
            var textView: TextView,
            var imageView: ImageView
    )
}