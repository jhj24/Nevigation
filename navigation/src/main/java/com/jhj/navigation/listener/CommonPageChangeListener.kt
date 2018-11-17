package com.jhj.navigation.listener

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.jhj.navigation.model.NavigationBarItem


/**
 * 普通的ViewPager变化监听器
 *
 * Created by jhj on 18-11-15.
 */
class CommonPageChangeListener(
        private val viewPager: ViewPager,
        private val fragmentList: List<Fragment>,
        private val navigationBarItemList: List<NavigationBarItem>) : ViewPager.OnPageChangeListener {

    private var listener: ViewPager.OnPageChangeListener? = null


    fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener?) {
        this.listener = listener
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val currentItem = viewPager.currentItem
        val nextItem: Int

        if (position == currentItem) {//向右
            if (currentItem == fragmentList.size - 1)
                return
            nextItem = currentItem + 1
            val barItemCurrent = navigationBarItemList[currentItem]
            val barItemNext = navigationBarItemList[nextItem]
            setBottomBarItemSelected(barItemCurrent, 1f)
            setBottomBarItemSelected(barItemNext, 0f)
        } else {//向左
            if (currentItem == 0)
                return
            nextItem = currentItem - 1
            val barItemCurrent = navigationBarItemList[currentItem]
            val barItemNext = navigationBarItemList[nextItem]
            setBottomBarItemSelected(barItemCurrent,1f)
            setBottomBarItemSelected(barItemNext, 0f)
        }
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
            navigationBarItem.textViewDefault?.isSelected = true
            navigationBarItem.imageViewDefault?.isSelected = true

        } else {
            navigationBarItem.textViewDefault?.isSelected = false
            navigationBarItem.imageViewDefault?.isSelected = false
        }
    }
}