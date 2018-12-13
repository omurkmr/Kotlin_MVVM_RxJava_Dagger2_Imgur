package com.omurkumru.mobilab.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.omurkumru.mobilab.R
import com.omurkumru.mobilab.data.model.MainImage
import com.omurkumru.mobilab.ui.about.AboutActivity
import com.omurkumru.mobilab.ui.adapter.ImageAdapter
import com.omurkumru.mobilab.utils.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    var cacheOption = arrayOf(CacheTypeConstants.IN_MEMORY, CacheTypeConstants.ON_DISK)
    var sectionOption = arrayOf(SectionConstants.HOT, SectionConstants.TOP, SectionConstants.USER)
    var sortOption = arrayOf(SortTypeConstants.VIRAL, SortTypeConstants.TOP, SortTypeConstants.TIME, SortTypeConstants.RISING)
    var windowOption = arrayOf(WindowTypeConstants.DAY, WindowTypeConstants.WEEK, WindowTypeConstants.MONTH, WindowTypeConstants.ALL)

    lateinit var sectionArrayAdapter: ArrayAdapter<String>
    lateinit var cacheArrayAdapter: ArrayAdapter<String>
    lateinit var sortArrayAdapter: ArrayAdapter<String>
    lateinit var windowArrayAdapter: ArrayAdapter<String>
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
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(
                MainViewModel::class.java)

        mainViewModel.imageResult.observe(this, nameObserver)

        setAdapter()
        setListener()

        getImages()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_info -> startActivity(Intent(this, AboutActivity::class.java))

        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAdapter() {
        cacheArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cacheOption)
        sectionArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sectionOption)
        sortArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sortOption)
        windowArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, windowOption)

        cacheArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sectionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sortArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        windowArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
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
                        0 -> CachePref.cachePrefType = CacheTypeConstants.IN_MEMORY
                        1 -> CachePref.cachePrefType = CacheTypeConstants.ON_DISK
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
                    getImages()
                }
            }
            prompt = "Preferred Section"
        }

        with(sort_SP)
        {
            adapter = sortArrayAdapter
            setSelection(0, false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> SortPref.sortPrefType = SortTypeConstants.VIRAL
                        1 -> SortPref.sortPrefType = SortTypeConstants.TOP
                        2 -> SortPref.sortPrefType = SortTypeConstants.TIME
                        3 -> SortPref.sortPrefType = SortTypeConstants.RISING
                    }
                    getImages()
                }
            }
            prompt = "Preferred Sort"
        }

        with(window_SP)
        {
            adapter = windowArrayAdapter
            setSelection(0, false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> WindowPref.windowPrefType = WindowTypeConstants.DAY
                        1 -> WindowPref.windowPrefType = WindowTypeConstants.WEEK
                        2 -> WindowPref.windowPrefType = WindowTypeConstants.MONTH
                        3 -> WindowPref.windowPrefType = WindowTypeConstants.ALL
                    }
                    getImages()
                }
            }
            prompt = "Preferred Window"
        }

        with(viral_SW)
        {
            setOnClickListener {
                getImages()
            }
        }
    }

    fun getImages() {
        val section = SectionPref.sectionPrefType
        val sort = SortPref.sortPrefType
        val window = WindowPref.windowPrefType
        val showViral = viral_SW.isChecked

        mainViewModel.getGalleryImages(section, sort, window, showViral)
    }
}