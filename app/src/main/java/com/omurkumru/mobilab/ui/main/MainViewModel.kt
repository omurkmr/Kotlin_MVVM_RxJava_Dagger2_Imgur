package com.omurkumru.mobilab.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.omurkumru.mobilab.data.model.MainImage
import com.omurkumru.mobilab.data.model.RawGalleryResponse
import com.omurkumru.mobilab.data.source.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel @Inject constructor(
        private val mainRepository: MainRepository) : ViewModel() {

    var imageResult: MutableLiveData<List<MainImage>> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<RawGalleryResponse>

    fun getGalleryImages(section: String, showViral: Boolean) {

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
                            imageList.add(MainImage(it.id, it.link, it.description))
                        }
                    } else {
                        imageList.add(MainImage(it.id, it.link, it.description))
                    }
                }

                imageResult.postValue(imageList)
            }

            override fun onError(e: Throwable) {}
        }

        mainRepository.getGalleryImagesFromApi(section, showViral)
                //making rest requests so using io fits
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver)
    }
}