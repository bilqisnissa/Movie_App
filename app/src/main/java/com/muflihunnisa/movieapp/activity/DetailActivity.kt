package com.muflihunnisa.movieapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.muflihunnisa.movieapp.model.Model
import com.muflihunnisa.movieapp.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val KEY_VALUE = "content"
    }

    private var model: Model? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()

        model = intent.getParcelableExtra(KEY_VALUE)
        tv_title_detail.text = model?.name
        tv_synopsis_detail.text = model?.desc
        fb_back_detail.setOnClickListener(this)
        tv_watch_trailer.setOnClickListener(this)

        if (model?.thumb != null) {
            Glide.with(this).load("https://image.tmdb.org/t/p/w92${model?.thumb}")
                    .into(iv_detail_poster)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.fb_back_detail -> startActivity(Intent(MainActivity.getLaunchService(this)))
            R.id.tv_watch_trailer -> startActivity(Intent(TrailerActivity.getLaunchService(this)))
        }
    }
}
