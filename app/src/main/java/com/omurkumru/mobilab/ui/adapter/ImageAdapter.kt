package com.omurkumru.mobilab.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.omurkumru.mobilab.R
import com.omurkumru.mobilab.data.model.MainImage
import com.omurkumru.mobilab.utils.CachePref
import com.omurkumru.mobilab.utils.CacheTypeConstants
import kotlinx.android.synthetic.main.imgur_item.view.*

class ImageAdapter(context: Context, var imageList: List<MainImage>) : BaseAdapter() {
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

        val diskCacheType = CachePref.cachePrefType

        val cacheDisk: DiskCacheStrategy
        val skipCacheMemory: Boolean

        when (diskCacheType) {
            CacheTypeConstants.IN_MEMORY -> {
                cacheDisk = DiskCacheStrategy.NONE
                skipCacheMemory = false
            }
            CacheTypeConstants.ON_DISK -> {
                cacheDisk = DiskCacheStrategy.ALL
                skipCacheMemory = true
            }
            else -> {
                cacheDisk = DiskCacheStrategy.AUTOMATIC
                skipCacheMemory = false
            }
        }

        Glide.with(context!!)
                .load(image.url)
                .apply(RequestOptions.diskCacheStrategyOf((cacheDisk)))
                .apply(RequestOptions.skipMemoryCacheOf(skipCacheMemory))
                .into(imageView.imgur_IV)

        return imageView
    }
}