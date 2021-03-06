package com.jhj.nevigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_common_gradient.setOnClickListener {
            startActivity<GradientActivity>()
        }
        btn_common.setOnClickListener {
            startActivity<CommonActivity>()
        }
    }


}
