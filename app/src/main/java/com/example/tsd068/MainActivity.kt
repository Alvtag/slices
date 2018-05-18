package com.example.tsd068

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tsd068.newsslice.R

class MainActivity : AppCompatActivity() {
    lateinit var usualContent:TextView
    lateinit var webView:WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usualContent = findViewById(R.id.textView)
        webView = findViewById(R.id.webView)
        //usualContent

        usualContent.visibility= View.VISIBLE
        webView.visibility= View.GONE

        Logg.instance.d("ALVTAG", "point A")
        val url:String = intent.getStringExtra("url") ?: return
        Logg.instance.d("ALVTAG", "point B:url:"+url)
        usualContent.visibility= View.GONE
        webView.visibility= View.VISIBLE
        webView.loadUrl(url)
    }
}
