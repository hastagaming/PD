package com.pd.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class BusyBoxActivity : AppCompatActivity() {

    private lateinit var statusTextView: TextView
    private val TAG = "PD_ScannerCore"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        statusTextView = TextView(this).apply {
            setPadding(50, 50, 50, 50)
            text = "System: ScannerCore engine detected..."
            textSize = 16f
            setBackgroundColor(0xFF1E1E2E.toInt())
            setTextColor(0xFFCDD6F4.toInt())
        }
        setContentView(statusTextView)

        thread {
            startCoreEngine()
        }
    }

    private fun startCoreEngine() {
        try {
            updateStatus("Initializing ScannerCore...")
            
            val core = ScannerCore()
            val isoPath = intent.getStringExtra("ISO_PATH") ?: ""
            val busyBoxUrl = core.startAutomatedBusyBoxSetup(isoPath)
            
            updateStatus("Detected architecture target: $busyBoxUrl")
            updateStatus("Binaries verified.")

            Thread.sleep(800)

            updateStatus("Handing over to TerminalActivity...")
            val intent = Intent(this, TerminalActivity::class.java)
            startActivity(intent)
            finish()

        } catch (e: Exception) {
            Log.e(TAG, "ScannerCore failed to start", e)
            updateStatus("Critical Error: ${e.message}")
        }
    }

    private fun updateStatus(msg: String) {
        runOnUiThread {
            statusTextView.append("\n> $msg")
            Log.d(TAG, msg)
        }
    }
}
