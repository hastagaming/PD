package com.pd.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Jika ada layout, gunakan: setContentView(R.layout.activity_setup)
    }

    // Fungsi baru untuk memproses ISO yang dipilih Komandan
    private fun processSelectedIso(file: File) {
        // Pastikan class DistroScanner sudah Komandan buat di file terpisah
        val scanner = ScannerCore() 
        val busyboxUrl = scanner.getCompatibleBusyBox(file)

        // Sistem otomatis memilihkan BusyBox untuk user dan berpindah Activity
        val intent = Intent(this, BusyBoxActivity::class.java).apply {
            putExtra("BUSYBOX_URL", busyboxUrl)
            putExtra("ISO_PATH", file.absolutePath)
        }
        startActivity(intent)
    }

    // Fungsi lama tetap saya pertahankan untuk berjaga-jaga
    fun startBusyBox(path: String, name: String) {
        val intent = Intent(this, BusyBoxActivity::class.java).apply {
            putExtra("path", path)
            putExtra("name", name)
        }
        startActivity(intent)
    }
}
