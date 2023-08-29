package ru.nsuhelper.server

import java.net.ServerSocket
import java.net.SocketException
import java.sql.Statement

class ServerWork {
    private var PORT = 8080;
    fun startServer() {
        val server = ServerSocket(PORT);
        println("Server started");
        while (true) {
            try {
                val socket = server.accept()
                println("client connection")
                val worker = WorkerWithClient(socket)
                worker.start()
            } catch (error: SocketException) {
                error.stackTrace;
            }
        }
    }
}