package com.example.shoppingassistant.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppingassistant.R

class MainActivity : AppCompatActivity(), PositionItemFragment.OnEditingIsFinishedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onEditingIsFinishedListener() {

                supportFragmentManager.popBackStack()
    }
}