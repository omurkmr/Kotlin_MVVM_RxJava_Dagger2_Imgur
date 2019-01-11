package com.omurkumru.imgurImages.utils


class ImgurConstants {
    companion object {
        const val BASE_URL = "https://api.imgur.com/3/"
        const val CLIENT_ID = "b60cdfc45e67795"
        const val CLIENT_SECRET = "53482d9f5f44f3bf9a4f69b4d7f1ce04b3fc9312"
    }
}

class SectionConstants {
    companion object {
        const val HOT = "hot"
        const val TOP = "top"
        const val USER = "user"
    }
}

class CacheTypeConstants {
    companion object {
        const val IN_MEMORY = "in Memory"
        const val ON_DISK = "on Disk"
    }
}

class SortTypeConstants {
    companion object {
        const val VIRAL = "viral"
        const val TOP = "top"
        const val TIME = "time"
        const val RISING = "rising"
    }
}

class WindowTypeConstants {
    companion object {
        const val DAY = "day"
        const val WEEK = "week"
        const val MONTH = "month"
        const val YEAR = "year"
        const val ALL = "all"
    }
}

class IntentConstants {
    companion object {
        const val IMAGE_DATA = "imageData"
    }
}


class CachePref {
    companion object {
        var cachePrefType: String = CacheTypeConstants.IN_MEMORY
    }
}

class SectionPref {
    companion object {
        var sectionPrefType: String = SectionConstants.HOT
    }
}

class SortPref {
    companion object {
        var sortPrefType: String = SortTypeConstants.VIRAL
    }
}

class WindowPref {
    companion object {
        var windowPrefType: String = WindowTypeConstants.DAY
    }
}