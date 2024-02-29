package com.example.chanvideo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chanvideo.video.rtsp.RtspClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private var rtspClient: RtspClient? = null

    fun start() {
        viewModelScope.launch(Dispatchers.IO) {
            rtspClient = RtspClient()
            rtspClient?.start()
        }
    }
}