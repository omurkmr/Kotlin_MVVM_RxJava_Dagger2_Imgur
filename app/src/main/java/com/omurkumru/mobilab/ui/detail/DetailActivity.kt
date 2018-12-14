package com.omurkumru.mobilab.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.omurkumru.mobilab.R
import com.omurkumru.mobilab.data.model.MainImage
import com.omurkumru.mobilab.ui.ViewModelFactory
import com.omurkumru.mobilab.utils.IntentConstants
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var detailViewModel: DetailViewModel

    lateinit var imageData: MainImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        AndroidInjection.inject(this)
        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(
                DetailViewModel::class.java)

        imageData = intent.getParcelableExtra(IntentConstants.IMAGE_DATA)

        setData()
    }

    private fun setData() {

        //set image
        Glide.with(this!!)
                .load(imageData.url)
                .apply(RequestOptions.diskCacheStrategyOf((detailViewModel.getCacheDisk()!!)))
                .apply(RequestOptions.skipMemoryCacheOf(detailViewModel.getSkipCacheMemory()))
                .into(bigImage_IV)

        title_TV.text = imageData.title
        description_TV.text = imageData.description
        upVote_TV.text = "UpVote = ${imageData.upVotes.toString()}"
        downVote_TV.text = "DownVote = ${imageData.downVotes.toString()}"
        score_TV.text = "Score = ${imageData.score.toString()}"
    }
}
