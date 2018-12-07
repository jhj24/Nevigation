package com.jhj.nevigation

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.jhj.navigation.GradientPageChangeListener
import com.jhj.navigation.NavigationBarItem
import kotlinx.android.synthetic.main.activity_navigation.*

class GradientActivity : AppCompatActivity() {

    private val fragmentList = arrayListOf<Fragment>()
    private val navigationBarItemList = arrayListOf<NavigationBarItem>()

    private val bottomBarList = listOf(
            "首页" to R.drawable.bg_home_selected,
            "通讯录" to R.drawable.bg_contacts_selected,
            "功能" to R.drawable.bg_function_selected,
            "我的" to R.drawable.bg_me_selected)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        fragmentList.add(getFragment(0))
        fragmentList.add(getFragment(1))
        fragmentList.add(getFragment(2))
        fragmentList.add(getFragment(3))


        fragmentList.forEachIndexed { index, fragment ->
            val view = LayoutInflater.from(this).inflate(R.layout.layout_buttom_item, layout_navigation, false)
            val navigationBarItem = NavigationBarItem(
                    textViewDefault = view.findViewById(R.id.itemTitleDefault),
                    textViewSelected = view.findViewById(R.id.itemTitleSelected),
                    imageViewDefault = view.findViewById(R.id.imageViewDefault))

            view.setOnClickListener {
                viewPager.setCurrentItem(index, false)
            }
            layout_navigation.addView(view)

            navigationBarItem.textViewDefault?.text = bottomBarList[index].first
            navigationBarItem.textViewSelected?.text = bottomBarList[index].first
            navigationBarItem.imageViewDefault?.setImageResource(bottomBarList[index].second)
            navigationBarItemList.add(navigationBarItem)
        }


        viewPager.adapter = MyPageAdapter(supportFragmentManager, fragmentList)

        val listener = GradientPageChangeListener(viewPager, fragmentList, navigationBarItemList)
        //listener.setGradientResultColor(R.color.colorAccent)
        //listener.setOnPageChangeListener(pageChangeListener)
        viewPager.addOnPageChangeListener(listener)

    }

    private fun getFragment(status: Int): TestFragment {
        val fragment = TestFragment()
        val bundle = Bundle()
        bundle.putInt("status", status)
        fragment.arguments = bundle
        return fragment
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