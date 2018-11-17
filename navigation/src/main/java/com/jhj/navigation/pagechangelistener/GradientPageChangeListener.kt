package com.jhj.navigation.pagechangelistener

import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.jhj.navigation.model.NavigationBarItem


/**
 * 底部导航栏成渐变色的ViewPager监听器
 *
 * Created by jhj on 18-11-15.
 */
class GradientPageChangeListener(
        val viewPager: ViewPager,
        private val fragmentList: List<Fragment>,
        private val navigationBarItemList: List<NavigationBarItem>) : ViewPager.OnPageChangeListener {


    @ColorInt
    private var imageSelectedColor: Int? = null

    private var listener: ViewPager.OnPageChangeListener? = null


    fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener?) {
        this.listener = listener
    }

    fun setGradientResultColor(@ColorInt color: Int?) {
        this.imageSelectedColor = color
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
            setBottomBarItemSelected(barItemCurrent, 1 - positionOffset)
            setBottomBarItemSelected(barItemNext, positionOffset)
        } else {//向左
            if (currentItem == 0)
                return
            nextItem = currentItem - 1
            val barItemCurrent = navigationBarItemList[currentItem]
            val barItemNext = navigationBarItemList[nextItem]
            setBottomBarItemSelected(barItemCurrent, positionOffset)
            setBottomBarItemSelected(barItemNext, 1 - positionOffset)
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
        navigationBarItem.textViewSelected?.alpha = percent
        navigationBarItem.textViewDefault?.alpha = 1 - percent

        navigationBarItem.imageViewDefault?.scaleX = 1f + 0.15f * percent
        navigationBarItem.imageViewDefault?.scaleY = 1f + 0.15f * percent

        val textColor = imageSelectedColor ?: navigationBarItem.textViewSelected?.currentTextColor

        textColor?.let {
            navigationBarItem.imageViewDefault?.setColorFilter(Color.argb(
                    (0xFF * percent).toInt(),
                    Color.red(it),
                    Color.green(it),
                    Color.blue(it)))
        }
    }
}