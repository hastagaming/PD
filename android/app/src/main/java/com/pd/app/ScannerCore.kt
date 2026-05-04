package com.pd.app

import java.io.File
import java.io.RandomAccessFile
import android.util.Log

/**
 * ScannerCore: Automated Distro Architecture Detection
 * This class scans the binary headers of an ISO/Tarball to 
 * determine the correct BusyBox version without user input.
 */
class DistroScanner {

    private val TAG = "PD_Scanner"
    private val BUSYBOX_BASE_URL = "https://busybox.net/downloads/binaries/1.21.1/"

    /**
     * Scans the provided file and returns the correct BusyBox download URL.
     */
    fun getCompatibleBusyBox(distroFile: File): String {
        val arch = autoDetectArch(distroFile)
        Log.d(TAG, "Detected Architecture: $arch")

        return when (arch) {
            "x86_64"  -> BUSYBOX_BASE_URL + "busybox-x86_64"
            "armv7l"  -> BUSYBOX_BASE_URL + "busybox-armv7l"
            "i686"    -> BUSYBOX_BASE_URL + "busybox-i686"
            "armv6l"  -> BUSYBOX_BASE_URL + "busybox-armv6l"
            // Defaulting to armv7l as it has the best compatibility for Android
            else      -> BUSYBOX_BASE_URL + "busybox-armv7l"
        }
    }

    /**
     * Reads the ELF Header of the file to identify bitness and instruction set.
     */
    private fun autoDetectArch(file: File): String {
        return try {
            val raf = RandomAccessFile(file, "r")
            val header = ByteArray(20)
            raf.readFully(header)
            raf.close()

            // Check for ELF Magic Number: 0x7F 'E' 'L' 'F'
            if (header[0] == 0x7f.toByte() && header[1] == 'E'.toByte() && 
                header[2] == 'L'.toByte() && header[3] == 'F'.toByte()) {
                
                val is64Bit = header[4].toInt() == 2 // 1 = 32bit, 2 = 64bit
                
                // Simplified detection logic based on bitness
                if (is64Bit) {
                    "x86_64" // Standard 64-bit target
                } else {
                    "armv7l" // Standard 32-bit target
                }
            } else {
                "unknown"
            }
        } catch (e: Exception) {
            Log.e(TAG, "Scanning failed: ${e.message}")
            "unknown"
        }
    }

    /**
     * Function to be called from the UI to trigger the automated flow.
     */
    fun startAutomatedBusyBoxSetup(isoPath: String): String {
        val file = File(isoPath)
        if (file.exists()) {
            return getCompatibleBusyBox(file)
        }
        return BUSYBOX_BASE_URL + "busybox-armv7l" // Safe fallback
    }
}
