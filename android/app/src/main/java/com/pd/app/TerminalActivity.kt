package com.pd.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import kotlin.concurrent.thread

class TerminalActivity : AppCompatActivity() {

    private val TAG = "TerminalBuild"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_terminal)

        // Jalankan distro saat activity dimulai
        thread {
            startLinuxDistro()
        }
    }

    fun startLinuxDistro() {
        try {
            val scriptFile = File(filesDir, "scripts/boot.sh")
            
            if (!scriptFile.exists()) {
                Log.e(TAG, "Script boot.sh tidak ditemukan di: ${scriptFile.absolutePath}")
                return
            }

            // 1. Pastikan izin eksekusi (chmod +x)
            scriptFile.setExecutable(true, false)

            // 2. Siapkan ProcessBuilder
            val processBuilder = ProcessBuilder("/system/bin/sh", scriptFile.absolutePath)
                .directory(filesDir) // Set working directory ke /files aplikasi
                .redirectErrorStream(true)

            // 3. Tambahkan PATH agar command linux dasar bisa ditemukan
            val env = processBuilder.environment()
            env["PATH"] = "${filesDir.absolutePath}/bin:/system/bin:/system/xbin"
            env["HOME"] = "${filesDir.absolutePath}/home"

            val process = processBuilder.start()

            // 4. Stream output ke Logcat/Terminal View
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                Log.d(TAG, "OUTPUT: $line")
                // Di sini Komandan bisa mengirim 'line' ke UI Terminal View via runOnUiThread
            }

            val exitCode = process.waitFor()
            Log.i(TAG, "Process berakhir dengan kode: $exitCode")

        } catch (e: Exception) {
            Log.e(TAG, "Gagal menjalankan distro: ${e.message}")
        }
    }
}
