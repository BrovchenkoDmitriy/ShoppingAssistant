package com.example.shoppingassistant.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppingassistant.R
import com.example.shoppingassistant.presentation.noteItem.NoteItemFragment
import com.example.shoppingassistant.presentation.positionItem.PositionItemFragment

class MainActivity : AppCompatActivity(), PositionItemFragment.OnEditingIsFinishedListener, NoteItemFragment.OnEditingIsFinishedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onEditingIsFinishedListener() {

                supportFragmentManager.popBackStack()
    }
}