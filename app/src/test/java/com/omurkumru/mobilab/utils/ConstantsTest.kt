package com.omurkumru.mobilab.utils

import org.junit.Assert
import org.junit.Test

class ConstantsTest {

    @Test
    fun isImgurConstantsRightTest() {
        Assert.assertEquals("https://api.imgur.com/3/", ImgurConstants.BASE_URL)
        Assert.assertEquals("b60cdfc45e67795", ImgurConstants.CLIENT_ID)
        Assert.assertEquals("53482d9f5f44f3bf9a4f69b4d7f1ce04b3fc9312", ImgurConstants.CLIENT_SECRET)
    }

    @Test
    fun isSectionConstantsRightTest() {
        Assert.assertEquals("hot", SectionConstants.HOT)
        Assert.assertEquals("top", SectionConstants.TOP)
        Assert.assertEquals("user", SectionConstants.USER)
    }

    @Test
    fun isCacheTypeConstantsRightTest() {
        Assert.assertEquals("in Memory", CacheTypeConstants.IN_MEMORY)
        Assert.assertEquals("on Disk", CacheTypeConstants.ON_DISK)
    }

    @Test
    fun isSortTypeConstantsRightTest() {
        Assert.assertEquals("viral", SortTypeConstants.VIRAL)
        Assert.assertEquals("top", SortTypeConstants.TOP)
        Assert.assertEquals("time", SortTypeConstants.TIME)
        Assert.assertEquals("rising", SortTypeConstants.RISING)
    }

    @Test
    fun isWindowTypeConstantsRightTest() {
        Assert.assertEquals("day", WindowTypeConstants.DAY)
        Assert.assertEquals("week", WindowTypeConstants.WEEK)
        Assert.assertEquals("month", WindowTypeConstants.MONTH)
        Assert.assertEquals("year", WindowTypeConstants.YEAR)
        Assert.assertEquals("all", WindowTypeConstants.ALL)
    }
}