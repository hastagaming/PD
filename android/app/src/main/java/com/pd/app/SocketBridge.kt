package com.pd.app

import android.net.LocalSocket
import android.net.LocalSocketAddress
import java.io.InputStream
import java.io.OutputStream

class SocketBridge(private val path: String) {
    private var socket: LocalSocket? = null

    fun connect(): Boolean {
        return try {
            socket = LocalSocket()
            socket?.connect(LocalSocketAddress(path, LocalSocketAddress.Namespace.FILESYSTEM))
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getInputStream(): InputStream? = socket?.inputStream
    fun getOutputStream(): OutputStream? = socket?.outputStream
}
