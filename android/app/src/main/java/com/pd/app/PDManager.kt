package com.pd.app

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class PDManager(private val context: Context) {
    private val TAG = "PD_Bootstrap"
    private val internalDir = context.filesDir // /data/data/com.pd.app/files

    /**
     * Fungsi utama untuk menyiapkan lingkungan isolasi
     */
    fun prepareEnvironment() {
        Log.d(TAG, "Memulai inisialisasi lingkungan PD...")
        
        // 1. Buat struktur folder internal
        val binDir = File(internalDir, "bin")
        val scriptsDir = File(internalDir, "scripts")
        val rootfsDir = File(internalDir, "rootfs")
        val homeDir = File(internalDir, "home")

        listOf(binDir, scriptsDir, rootfsDir, homeDir).forEach {
            if (!it.exists()) it.mkdirs()
        }

        // 2. Ekstrak file dari Assets
        copyAssetFolder("bin", binDir)
        copyAssetFolder("scripts", scriptsDir)

        // 3. Berikan izin eksekusi pada binary
        setExecutablePermissions(binDir)
        setExecutablePermissions(scriptsDir)
