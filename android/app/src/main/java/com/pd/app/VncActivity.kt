package com.pd.app

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class VncActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vnc)

        webView = findViewById(R.id.vnc_webview)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.displayZoomControls = false
        settings.builtInZoomControls = true

        webView.webViewClient = WebViewClient()
        
        // Connect to local NoVNC server started by the daemon
        webView.loadUrl("http://127.0.0.1:6080/vnc.html?autoconnect=true")
    }
}
