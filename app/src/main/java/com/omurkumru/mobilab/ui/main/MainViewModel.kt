package com.omurkumru.mobilab.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.omurkumru.mobilab.data.model.MainImage
import com.omurkumru.mobilab.data.model.RawGalleryResponse
import com.omurkumru.mobilab.data.source.MainRepository
import com.omurkumru.mobilab.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel @Inject constructor(
        private val mainRepository: MainRepository) : ViewModel() {

    var imageResult: MutableLiveData<List<MainImage>> = MutableLiveData()

    lateinit var disposableObserver: DisposableObserver<RawGalleryResponse>

    var cacheOption = arrayOf(CacheTypeConstants.IN_MEMORY, CacheTypeConstants.ON_DISK)
    var sectionOption = arrayOf(SectionConstants.HOT, SectionConstants.TOP, SectionConstants.USER)
    var sortOption = arrayOf(SortTypeConstants.VIRAL, SortTypeConstants.TOP, SortTypeConstants.TIME, SortTypeConstants.RISING)
    var windowOption = arrayOf(WindowTypeConstants.DAY, WindowTypeConstants.WEEK, WindowTypeConstants.MONTH, WindowTypeConstants.ALL)

    fun getGalleryImages(section: String, sort: String, window: String, showViral: Boolean) {

        disposableObserver = object : DisposableObserver<RawGalleryResponse>() {
            var imageList: ArrayList<MainImage> = ArrayList()
            override fun onComplete() {}
            override fun onNext(images: RawGalleryResponse) {
                val list = images.data
                //we cannot get one type data list so we have create our own list
                //onNext method fits perfectly on this situation
                list?.forEach {
                    if (it.isAlbum!!) {
                        it.images?.forEach {
                            imageList.add(MainImage(it.id, it.link, it.title, it.description, it.ups, it.downs, it.score))
                        }
                    } else {
                        imageList.add(MainImage(it.id, it.link, it.title, it.description, it.ups, it.downs, it.score))
                    }
                }
                imageResult.postValue(imageList)
            }

            override fun onError(e: Throwable) {}
        }

        mainRepository.getGalleryImagesFromApi(section, sort, window, showViral)
                //making rest requests so using io fits
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver)
    }

    fun setCacheType(type: String) {
        CachePref.cachePrefType = type
    }

    fun setSectionType(type: String) {
        SectionPref.sectionPrefType = type
    }

    fun setSortType(type: String) {
        SortPref.sortPrefType = type
    }

    fun setWindowType(type: String) {
        WindowPref.windowPrefType = type
    }

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

    override fun onCleared() {
        disposableObserver.dispose()
    }

    fun getImageResult(): List<MainImage>? {
        return imageResult.value
    }
}