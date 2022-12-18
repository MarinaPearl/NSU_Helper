package ru.nsuhelper.server

import java.net.ServerSocket
import java.net.SocketException

class ServerWork {
    private var PORT = 8080;

    fun startServer() {
        val server = ServerSocket();
        println("Server started");
        try {
            while (true) {
                val socket = server.accept();
            }
        }catch (error : SocketException) {
            error.printStackTrace();
        }


    }
}