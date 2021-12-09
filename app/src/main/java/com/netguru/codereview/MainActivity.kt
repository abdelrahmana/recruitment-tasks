package com.netguru.codereview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.netguru.codereview.ui.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
            .commit()
    }
}
