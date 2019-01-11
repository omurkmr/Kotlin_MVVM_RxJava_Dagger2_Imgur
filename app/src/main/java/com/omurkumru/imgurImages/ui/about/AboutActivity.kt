package com.omurkumru.imgurImages.ui.about

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.omurkumru.imgurImages.BuildConfig
import com.omurkumru.imgurImages.R
import kotlinx.android.synthetic.main.activity_about.*
import java.util.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val version = packageManager.getPackageInfo(packageName, 0).versionName
        val buildDate = Date(BuildConfig.TIMESTAMP)
        val author = getString(R.string.author_mail)

        version_TV.text = version
        buildTime_TV.text = buildDate.toString()
        author_TV.text = author


    }
}
