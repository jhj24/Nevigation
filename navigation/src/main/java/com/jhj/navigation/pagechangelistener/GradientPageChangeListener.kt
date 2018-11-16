package com.jhj.navigation.pagechangelistener

import android.graphics.Color
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jhj.navigation.base.BaseGradientNavigationFragment
import com.jhj.navigation.layoutres.GradientNavigationBarLayout


/**
 * 底部导航栏成渐变色的ViewPager监听器
 *
 * Created by jhj on 18-11-15.
 */
class GradientPageChangeListener(
        val viewPager: ViewPager,
        private val fragmentList: List<BaseGradientNavigationFragment>,
        val layout: GradientNavigationBarLayout,
        navigationBarView: ViewGroup) : ViewPager.OnPageChangeListener {

    private val navigationBarItemList = arrayListOf<NavigationBarItem>()


    init {
        val barLayoutRes = layout.getNavigationBarLayoutRes()
        for (i in fragmentList.indices) {
            val fragment = fragmentList[i]
            val view = LayoutInflater.from(navigationBarView.context).inflate(barLayoutRes, navigationBarView, false)
            val textDefault = view.findViewById<TextView>(layout.getNavigationBarDefaultTextId())
            val textSelected = view.findViewById<TextView>(layout.getNavigationBarSelectedTextId())
            val imageViewDefault = view.findViewById<ImageView>(layout.getNavigationBarDefaultImageId())
            val navigationBarItem = NavigationBarItem(textDefault, textSelected, imageViewDefault)

            view.setOnClickListener {
                viewPager.setCurrentItem(i, false)
            }
            navigationBarView.addView(view)
            navigationBarItem.titleSelected.text = fragment.title
            navigationBarItem.titleDefault.text = fragment.title
            navigationBarItem.imageViewDefault.setImageResource(fragment.iconDefault)
            navigationBarItemList.add(navigationBarItem)
        }
    }

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
        navigationBarItem.titleSelected.alpha = percent
        navigationBarItem.titleDefault.alpha = 1 - percent

        navigationBarItem.imageViewDefault.scaleX = 1f + 0.15f * percent
        navigationBarItem.imageViewDefault.scaleY = 1f + 0.15f * percent

        val textColor = navigationBarItem.titleSelected.currentTextColor

        navigationBarItem.imageViewDefault.setColorFilter(Color.argb(
                (0xFF * percent).toInt(),
                Color.red(textColor),
                Color.green(textColor),
                Color.blue(textColor))
        )
    }

    data class NavigationBarItem(
            var titleDefault: TextView,
            var titleSelected: TextView,
            var imageViewDefault: ImageView
    )
}