package com.jhj.navigation.pagechangelistener

import android.graphics.Color
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jhj.navigation.base.BaseNavigationFragment
import com.jhj.navigation.layoutres.NavigationBarLayout

class GradientPageChangeListener(val viewPager: ViewPager, private val fragmentList: List<BaseNavigationFragment>, val layout: NavigationBarLayout, layoutNavigation: LinearLayout) : ViewPager.OnPageChangeListener {

    private val bottomBarItemList = arrayListOf<BottomBarItem>()

    init {
        val layoutRes = layout.getNavigationBarLayoutRes()
        for (i in fragmentList.indices) {
            val fragment = fragmentList[i]
            val view = LayoutInflater.from(layoutNavigation.context).inflate(layoutRes, layoutNavigation, false)
            val defaultText = view.findViewById<TextView>(layout.getNavigationBarDefaultTextId())
            val selectedText = view.findViewById<TextView>(layout.getNavigationBarSelectedTextId())
            val imageViewDefault = view.findViewById<ImageView>(layout.getNavigationBarDefaultImageId())
            val bottomBarItem = BottomBarItem(defaultText, selectedText, imageViewDefault)

            view.setOnClickListener {
                viewPager.setCurrentItem(i, false)
            }
            layoutNavigation.addView(view)
            bottomBarItem.titleSelected.text = fragment.title
            bottomBarItem.titleDefault.text = fragment.title
            bottomBarItem.imageViewDefault.setImageResource(fragment.iconDefault)
            bottomBarItemList.add(bottomBarItem)
        }
    }


    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val currentItem = viewPager.currentItem
        val nextItem: Int

        if (position == currentItem) {//向右
            if (currentItem == fragmentList.size - 1) return
            nextItem = currentItem + 1
            val barItemCurrent = bottomBarItemList[currentItem]
            val barItemNext = bottomBarItemList[nextItem]
            setBottomBarItemSelected(barItemCurrent, 1 - positionOffset)
            setBottomBarItemSelected(barItemNext, positionOffset)
        } else {//向左
            if (currentItem == 0) return
            nextItem = currentItem - 1
            val barItemCurrent = bottomBarItemList[currentItem]
            val barItemNext = bottomBarItemList[nextItem]
            setBottomBarItemSelected(barItemCurrent, positionOffset)
            setBottomBarItemSelected(barItemNext, 1 - positionOffset)
        }
    }

    override fun onPageSelected(position: Int) {
        for (i in fragmentList.indices) {
            if (i == position) continue
            val preItem = bottomBarItemList[i]
            setBottomBarItemSelected(preItem, 0f)
            viewPager.tag = position
        }
        val bottomBarItem = bottomBarItemList[position]
        setBottomBarItemSelected(bottomBarItem, 1f)
    }

    override fun onPageScrollStateChanged(state: Int) {
        if (ViewPager.SCROLL_STATE_IDLE == state) {
            onPageSelected(viewPager.currentItem)
        }
    }

    private fun setBottomBarItemSelected(bottomBarItem: BottomBarItem, percent: Float) {
        bottomBarItem.titleSelected.alpha = percent
        bottomBarItem.titleDefault.alpha = 1 - percent

        bottomBarItem.imageViewDefault.scaleX = 1f + 0.15f * percent
        bottomBarItem.imageViewDefault.scaleY = 1f + 0.15f * percent

        val textColor = bottomBarItem.titleSelected.currentTextColor

        bottomBarItem.imageViewDefault.setColorFilter(Color.argb(
                (0xFF * percent).toInt(),
                Color.red(textColor),
                Color.green(textColor),
                Color.blue(textColor))
        )
    }

    data class BottomBarItem(
            var titleDefault: TextView,
            var titleSelected: TextView,
            var imageViewDefault: ImageView
    )
}