package com.pd.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.EditText
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var terminalOutput: TextView
    private lateinit var terminalInput: EditText
    private var bridge: SocketBridge? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        terminalOutput = findViewById(R.id.terminal_output)
        terminalInput = findViewById(R.id.terminal_input)

        startTerminalBridge()
    }

    private fun startTerminalBridge() {
        thread {
            bridge = SocketBridge("/data/local/tmp/pd.sock")
            if (bridge!!.connect()) {
                val input = bridge!!.getInputStream()
                val buffer = ByteArray(4096)
                while (true) {
                    val read = input?.read(buffer) ?: -1
                    if (read > 0) {
                        val text = String(buffer, 0, read)
                        runOnUiThread { terminalOutput.append(text) }
                    }
                }
            } else {
                runOnUiThread { 
                    terminalOutput.text = "Error: Daemon not running.\nPlease run 'sh launch_daemon.sh' via ADB." 
                }
            }
        }
    }
}
