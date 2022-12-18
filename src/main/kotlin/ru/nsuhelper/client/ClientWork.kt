package ru.nsuhelper.client

import java.net.Socket
import java.net.SocketException

class ClientWork {
    private val ip  = "127.0.0.1";
    private val port = 8080;
    fun startClient() {
        try {
            val client = Socket(ip, port);
        }catch (error : SocketException) {
            error.printStackTrace();
        }
    }
}