
package com.muflihunnisa.movieapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.muflihunnisa.movieapp.R
import kotlinx.android.synthetic.main.activity_onboarding3.*

class Onboarding3 : AppCompatActivity(), View.OnClickListener {


    companion object{
        fun getLaunchService (from: Context) = Intent(from, Onboarding3::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding3)
        supportActionBar?.hide()



        tv_get_started.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.tv_get_started -> startActivity(Intent(LoginActivity.getLaunchService(this)))
        }
    }
}