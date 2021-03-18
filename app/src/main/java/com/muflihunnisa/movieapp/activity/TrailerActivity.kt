package com.muflihunnisa.movieapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.muflihunnisa.movieapp.R
import com.muflihunnisa.movieapp.model.Model
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_trailer.*

class TrailerActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        fun getLaunchService(from: Context) = Intent(from, TrailerActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)
        supportActionBar?.hide()

        ib_back_trailer.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.ib_back_trailer -> startActivity(Intent(MainActivity.getLaunchService(this)))
        }
    }
}