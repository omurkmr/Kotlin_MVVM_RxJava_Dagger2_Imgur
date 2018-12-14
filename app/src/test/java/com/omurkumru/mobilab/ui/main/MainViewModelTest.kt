package com.omurkumru.mobilab.ui.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.omurkumru.mobilab.data.model.Datum
import com.omurkumru.mobilab.data.model.Image
import com.omurkumru.mobilab.data.model.MainImage
import com.omurkumru.mobilab.data.model.RawGalleryResponse
import com.omurkumru.mobilab.data.source.MainRepository
import com.omurkumru.mobilab.utils.*
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class MainViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val mainRepository = mock<MainRepository>()
    val viewmodel by lazy { MainViewModel(mainRepository) }

    val observerState = mock<Observer<List<MainImage>>>()

    val memorySelect = CacheTypeConstants.IN_MEMORY
    val diskSelect = CacheTypeConstants.ON_DISK

    val diskNoneStrategy = DiskCacheStrategy.NONE
    val diskAllStrategy = DiskCacheStrategy.ALL
    val diskResourceStrategy = DiskCacheStrategy.RESOURCE

    val successData = provideSuccessTestData()

    val section = SectionConstants.HOT
    val sort = SortTypeConstants.VIRAL
    val window = WindowTypeConstants.DAY
    val showViral = true

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun clear() {

    }

    @Test
    fun setCacheType_isGetString_returnUnitTest() {
        assertThat(viewmodel.setCacheType("test"), instanceOf(Unit.javaClass))
    }

    @Test
    fun setSectionType_isGetString_returnUnitTest() {
        assertThat(viewmodel.setSectionType("test"), instanceOf(Unit.javaClass))
    }

    @Test
    fun setSortType_isGetString_returnUnitTest() {
        assertThat(viewmodel.setSortType("test"), instanceOf(Unit.javaClass))
    }

    @Test
    fun setWindowType_isGetString_returnUnitTest() {
        assertThat(viewmodel.setWindowType("test"), instanceOf(Unit.javaClass))
    }

    @Test
    fun getCacheDisk_isReturnValidTest() {
        CachePref.cachePrefType = memorySelect
        assertEquals(diskNoneStrategy, viewmodel.getCacheDisk())

        CachePref.cachePrefType = diskSelect
        assertEquals(diskAllStrategy, viewmodel.getCacheDisk())

        CachePref.cachePrefType = "wrongTest"
        assertEquals(diskResourceStrategy, viewmodel.getCacheDisk())
    }

    @Test
    fun getCacheMemory_isReturnValidTest() {
        CachePref.cachePrefType = memorySelect
        assertEquals(false, viewmodel.getSkipCacheMemory())

        CachePref.cachePrefType = diskSelect
        assertEquals(true, viewmodel.getSkipCacheMemory())

        CachePref.cachePrefType = "wrongTest"
        assertEquals(false, viewmodel.getSkipCacheMemory())
    }

    @Test
    fun getImageFromApi_SuccessTest() {

        viewmodel.imageResult.observeForever(observerState)
        Mockito.`when`(mainRepository.getGalleryImagesFromApi(section, sort, window, showViral)).thenReturn(Observable.just(successData))
        viewmodel.getGalleryImages(section, sort, window, showViral)
        verify(observerState, times(1)).onChanged(expected())
    }

    private fun expected(): List<MainImage>? {

        val image1 = MainImage("1", "testLink", "title", "testDesc", 0, 0, 0)
        val image2 = MainImage("2", "testLink2", "title2", "testDesc2", 1, 1, 1)
        return listOf(image1, image2)
    }

    private fun provideSuccessTestData(): RawGalleryResponse {
        val datum1 = Datum()
        datum1.id = "1"
        datum1.isAlbum = false
        datum1.link = "testLink"
        datum1.title = "title"
        datum1.description = "testDesc"
        datum1.ups = 0
        datum1.downs = 0
        datum1.score = 0


        val datum2 = Datum()
        datum2.id = "2"
        datum2.isAlbum = false
        datum2.link = "testLink2"
        datum2.title = "title2"
        datum2.description = "testDesc2"
        datum2.ups = 1
        datum2.downs = 1
        datum2.score = 1

        val image1 = Image()
        image1.link = "testLink3"
        image1.description = "testDesc3"
        image1.title = "title3"
        image1.ups = 2
        image1.downs = 2
        image1.score = 2
        datum2.images = listOf(image1)

        val rawResponse = RawGalleryResponse()
        rawResponse.data = listOf(datum1, datum2)
        rawResponse.success = true
        rawResponse.status = 200

        return rawResponse
    }
}