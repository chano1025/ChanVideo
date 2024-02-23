package com.example.chanvideo.video.rtsp

import java.net.InetSocketAddress
import java.net.Socket
import java.net.URL

class RtspClient {
    companion object {
        const val SAMPLE_RTSP_URL = "rtsp://210.99.70.120:1935/live/cctv001.stream"
    }

    private var socket: Socket? = null

    /**
     * 소켓 연결
     */
    private fun connectSocket(socket: Socket, hostName: String, port: Int, timeout: Int) {
        if (!socket.isConnected) {
            socket.connect(InetSocketAddress(hostName, port))
            socket.soTimeout = timeout
            socket.reuseAddress = true
        }
    }

    private fun read() {

    }

    fun start() {
        val rtspUrl = parseRtspUrl(SAMPLE_RTSP_URL)
        socket = Socket()
        connectSocket(socket, "")
    }

    private fun parseRtspUrl(url: String): URL {
        return URL(url)
    }
}