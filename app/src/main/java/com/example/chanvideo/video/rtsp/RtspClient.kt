package com.example.chanvideo.video.rtsp

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.net.URI

class RtspClient {
    companion object {
        const val SAMPLE_RTSP_URL = "rtsp://210.99.70.120:1935/live/cctv001.stream"
        const val CRLF = "\r\n"
    }

    private lateinit var socket: Socket
    private lateinit var rtspUri: URI
    private var cSeq = 0

    fun start() {
        rtspUri = parseRtspUri(SAMPLE_RTSP_URL)
        socket = Socket()
        connectSocket(socket, rtspUri.host, rtspUri.port, 10000)

        val outputStream = socket.getOutputStream()
        requestOptions(outputStream)

        val inputStream = socket.getInputStream()
        val reader = BufferedReader(InputStreamReader(inputStream))
        read(reader)
    }

    /**
     * 소켓 연결
     */
    private fun connectSocket(socket: Socket, hostName: String, port: Int, timeout: Int) {
        if (!socket.isConnected) {
            socket.soTimeout = timeout
            socket.reuseAddress = true
            socket.connect(InetSocketAddress(hostName, port))
        }
    }

    private fun parseRtspUri(url: String): URI {
        return URI(url)
    }

    private fun requestOptions(writer: OutputStream) {
        val request = """OPTIONS ${rtspUri.authority} RTSP/1.0$CRLF"""
        writer.write(request.toByteArray())
        writer.write("""CSeq: ${++cSeq}$CRLF""".toByteArray())
        writer.write(CRLF.toByteArray())
        writer.flush()
    }

    private fun read(reader: BufferedReader) {
        var line = ""
        while (reader.readLine().also { line = it } != null) {
            Log.e("chan", line)
        }
    }
}