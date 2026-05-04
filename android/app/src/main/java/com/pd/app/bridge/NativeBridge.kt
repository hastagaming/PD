package com.pd.app.bridge

class NativeBridge {
    companion object {
        init {
            System.loadLibrary("pd_core")
        }
    }

    external fun startDaemon(socketPath: String): Int
    external fun stopDaemon(): Int
}
