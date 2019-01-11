package com.omurkumru.imgurImages.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.omurkumru.imgurImages.R
import com.omurkumru.imgurImages.data.model.MainImage
import kotlinx.android.synthetic.main.imgur_item.view.*

class MainImageAdapter(context: Context, var imageList: List<MainImage>, var mainViewModel: MainViewModel) : BaseAdapter() {
    var context: Context? = context

    override fun getCount(): Int {
        return imageList.size
    }

    override fun getItem(position: Int): Any {
        return imageList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val image = this.imageList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var imageView = inflator.inflate(R.layout.imgur_item, null)

        imageView.imgurDesc_TV.text = image.description

        Glide.with(context!!)
                .load(image.url)
                .apply(RequestOptions.diskCacheStrategyOf((mainViewModel.getCacheDisk())))
                .apply(RequestOptions.skipMemoryCacheOf(mainViewModel.getSkipCacheMemory()))
                .into(imageView.imgur_IV)

        return imageView
    }
}