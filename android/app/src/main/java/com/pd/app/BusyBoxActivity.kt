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
        
        // Dark theme UI to match your terminal style
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
            val core = ScannerCore(this)
            
            // We invoke the core logic here. 
            // This will handle the binary extraction and setup.
            core.initializeBinaries()

            updateStatus("Core binaries ready.")
            updateStatus("Handing over to TerminalActivity...")
            
            Thread.sleep(600)

            // Switch to TerminalActivity to start the interactive session
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
