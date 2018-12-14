package com.omurkumru.mobilab.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.res.Configuration
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
import com.omurkumru.mobilab.utils.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var sectionArrayAdapter: ArrayAdapter<String>
    lateinit var cacheArrayAdapter: ArrayAdapter<String>
    lateinit var sortArrayAdapter: ArrayAdapter<String>
    lateinit var windowArrayAdapter: ArrayAdapter<String>
    lateinit var mainImageAdapter: MainImageAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var mainViewModel: MainViewModel


    val nameObserver = Observer<List<MainImage>> { imageList ->
        mainImageAdapter = MainImageAdapter(this, imageList!!, mainViewModel)
        imgurImage_GridView.adapter = mainImageAdapter
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

        //get list when app opened
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

    override fun onConfigurationChanged(newConfig: Configuration) {

        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> topGroup.visibility = View.GONE
            Configuration.ORIENTATION_PORTRAIT -> topGroup.visibility = View.VISIBLE
            else -> topGroup.visibility = View.VISIBLE
        }

        super.onConfigurationChanged(newConfig)
    }

    private fun setAdapter() {
        cacheArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mainViewModel.cacheOption)
        sectionArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mainViewModel.sectionOption)
        sortArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mainViewModel.sortOption)
        windowArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mainViewModel.windowOption)

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
                        0 -> mainViewModel.setCacheType(CacheTypeConstants.IN_MEMORY)
                        1 -> mainViewModel.setCacheType(CacheTypeConstants.ON_DISK)
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
                        0 -> mainViewModel.setSectionType(SectionConstants.HOT)
                        1 -> mainViewModel.setSectionType(SectionConstants.TOP)
                        2 -> mainViewModel.setSectionType(SectionConstants.USER)
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
                        0 -> mainViewModel.setSortType(SortTypeConstants.VIRAL)
                        1 -> mainViewModel.setSortType(SortTypeConstants.TOP)
                        2 -> mainViewModel.setSortType(SortTypeConstants.TIME)
                        3 -> mainViewModel.setSortType(SortTypeConstants.RISING)
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
                        0 -> mainViewModel.setWindowType(WindowTypeConstants.DAY)
                        1 -> mainViewModel.setWindowType(WindowTypeConstants.WEEK)
                        2 -> mainViewModel.setWindowType(WindowTypeConstants.MONTH)
                        3 -> mainViewModel.setWindowType(WindowTypeConstants.ALL)
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