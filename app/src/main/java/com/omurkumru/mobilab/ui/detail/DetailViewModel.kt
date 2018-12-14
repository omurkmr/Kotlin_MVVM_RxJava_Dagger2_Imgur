package com.omurkumru.mobilab.ui.detail

import android.arch.lifecycle.ViewModel
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.omurkumru.mobilab.data.source.DetailRepository
import com.omurkumru.mobilab.utils.CachePref
import com.omurkumru.mobilab.utils.CacheTypeConstants
import javax.inject.Inject

class DetailViewModel @Inject constructor(
        private val detailRepository: DetailRepository) : ViewModel() {

    fun getCacheDisk(): DiskCacheStrategy? {
        return when (CachePref.cachePrefType) {
            CacheTypeConstants.IN_MEMORY -> {
                DiskCacheStrategy.NONE
            }
            CacheTypeConstants.ON_DISK -> {
                DiskCacheStrategy.ALL
            }
            else -> {
                DiskCacheStrategy.RESOURCE
            }
        }
    }

    fun getSkipCacheMemory(): Boolean {
        return when (CachePref.cachePrefType) {
            CacheTypeConstants.IN_MEMORY -> false
            CacheTypeConstants.ON_DISK -> true
            else -> false
        }
    }

}
