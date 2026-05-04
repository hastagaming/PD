package com.pd.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class TerminalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val bootScript = File(filesDir, "scripts/boot.sh")
        if (bootScript.exists()) {
            // Eksekusi terminal logic di sini
        }
    }
}
// Inside SetupActivity.kt
private fun processSelectedIso(file: File) {
    val scanner = DistroScanner()
    val busyboxUrl = scanner.getCompatibleBusyBox(file)
    
    // System automatically chooses for the user
    val intent = Intent(this, BusyBoxActivity::class.java).apply {
        putExtra("BUSYBOX_URL", busyboxUrl)
        putExtra("ISO_PATH", file.absolutePath)
    }
    startActivity(intent)
}
