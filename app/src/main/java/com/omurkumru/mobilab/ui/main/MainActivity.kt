package com.omurkumru.mobilab.ui.main

import android.R
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.omurkumru.mobilab.R.layout
import com.omurkumru.mobilab.data.model.MainImage
import com.omurkumru.mobilab.ui.adapter.ImageAdapter
import com.omurkumru.mobilab.utils.CachePref
import com.omurkumru.mobilab.utils.CacheTypeConstants
import com.omurkumru.mobilab.utils.SectionConstants
import com.omurkumru.mobilab.utils.SectionPref
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    var cacheOption = arrayOf(CacheTypeConstants.inMemory, CacheTypeConstants.onDisk)
    var sectionOption = arrayOf(SectionConstants.HOT, SectionConstants.TOP, SectionConstants.USER)

    lateinit var sectionArrayAdapter: ArrayAdapter<String>
    lateinit var cacheArrayAdapter: ArrayAdapter<String>
    lateinit var imageAdapter: ImageAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var mainViewModel: MainViewModel


    val nameObserver = Observer<List<MainImage>> { imageList ->
        imageAdapter = ImageAdapter(this, imageList!!)
        imgurImage_GridView.adapter = imageAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        AndroidInjection.inject(this)

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(
                MainViewModel::class.java)

        mainViewModel.imageResult.observe(this, nameObserver)

        setAdapter()
        setListener()
    }

    private fun setAdapter() {
        cacheArrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, cacheOption)
        sectionArrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, sectionOption)

        cacheArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sectionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        imgurImage_GridView.numColumns = 2
    }


    private fun setListener() {
        with(cacheOptions_SP)
        {
            adapter = cacheArrayAdapter
            setSelection(0, false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> CachePref.cachePrefType = CacheTypeConstants.inMemory
                        1 -> CachePref.cachePrefType = CacheTypeConstants.onDisk
                    }
                }
            }
            prompt = "Preferred Cache Type"
        }

        with(section_SP)
        {
            adapter = sectionArrayAdapter
            setSelection(0, false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> SectionPref.sectionPrefType = SectionConstants.HOT
                        1 -> SectionPref.sectionPrefType = SectionConstants.TOP
                        2 -> SectionPref.sectionPrefType = SectionConstants.USER
                    }
                    getImages(SectionPref.sectionPrefType, viral_SW.isChecked)
                }
            }
            prompt = "Preferred Section"
        }

        with(viral_SW)
        {
            setOnClickListener {
                getImages(SectionPref.sectionPrefType, viral_SW.isChecked)
            }
        }
    }

    fun getImages(section: String, showViral: Boolean) {
        mainViewModel.getGalleryImages(section, showViral)
    }
}