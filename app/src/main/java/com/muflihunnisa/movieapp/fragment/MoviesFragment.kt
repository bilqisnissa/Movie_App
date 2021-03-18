package com.muflihunnisa.movieapp.fragment


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.muflihunnisa.movieapp.*
import com.muflihunnisa.movieapp.activity.DetailActivity
import com.muflihunnisa.movieapp.adapter.ContentAdapt
import com.muflihunnisa.movieapp.model.Model
import com.muflihunnisa.movieapp.viewmodel.ContentVM
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {
    private lateinit var contentVM: ContentVM
    private lateinit var contentAdapt: ContentAdapt

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoad(true)
        attachData()
        contentVM.setCatalogueContentMovie()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun attachData() {
        contentAdapt = ContentAdapt {
            showSelected(it)
        }
        contentAdapt.notifyDataSetChanged()
        list_movies.setHasFixedSize(true)
        list_movies.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //panggil RV
        list_movies.addItemDecoration(
                DividerItemDecoration(
                        list_movies.context,
                        DividerItemDecoration.VERTICAL
                )
        )
        list_movies.adapter = contentAdapt
        contentVM = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
        ).get(ContentVM::class.java)
        contentVM.getCatalogueContent().observe(this, Observer {
            contentAdapt.setData(it)
            showLoad(false)
        })
    }

    private fun showSelected(it: Model) {
        val page = Intent(context, DetailActivity::class.java)
        page.putExtra(DetailActivity.KEY_VALUE, it)
        startActivity(page)
    }

    private fun showLoad(b: Boolean) {
        if (b) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }
}
