package com.omurkumru.mobilab.utils


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
        const val inMemory = "inMemory"
        const val onDisk = "onDisk"
    }
}

class CachePref {
    companion object {
        var cachePrefType: String = CacheTypeConstants.inMemory
    }
}

class SectionPref {
    companion object {
        var sectionPrefType: String = SectionConstants.HOT
    }
}