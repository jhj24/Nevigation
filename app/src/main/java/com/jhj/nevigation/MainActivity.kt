package com.jhj.nevigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jhj.nevigation.common.CommonNavigationActivity
import com.jhj.nevigation.common.CommonTextNavigationActivity
import com.jhj.nevigation.gradient.NavigationActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_default.setOnClickListener {
            startActivity<CommonNavigationActivity>()
        }
        btn_gradient.setOnClickListener {
            startActivity<NavigationActivity>()
        }

        btn_top_text.setOnClickListener {
            startActivity<CommonTextNavigationActivity>()
        }
    }


}
