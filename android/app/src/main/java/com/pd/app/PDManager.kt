package com.pd.app

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PDManager(private val context: Context) {

    private val TAG = "PDManager"

    /**
     * Menyuntikkan aset langsung ke ROOTFS_PATH yang digunakan proot_launcher.sh
     */
    fun injectAssetsToRootfs() {
        // Sesuai dengan ROOTFS_PATH di proot_launcher.sh
        val rootfsPath = File(context.filesDir, "rootfs")
        val targetBin = File(rootfsPath, "bin") // Mengarah ke /bin di dalam rootfs

        if (!targetBin.exists()) {
            targetBin.mkdirs()
        }

        try {
            // Mengambil busybox langsung dari root assets
            val busyboxFile = File(targetBin, "busybox")
            
            context.assets.open("busybox").use { input ->
                FileOutputStream(busyboxFile).use { output ->
                    input.copyTo(output)
                }
            }

            // Memberikan izin eksekusi
            busyboxFile.setExecutable(true, false)
            
            Log.i(TAG, "Aset berhasil disuntikkan ke ROOTFS/bin")
        } catch (e: IOException) {
            Log.e(TAG, "Gagal injeksi aset: ${e.message}")
        }
    }
}
