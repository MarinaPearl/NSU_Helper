package ru.nsuhelper.client


import java.net.Socket
import java.net.SocketException

class ClientWork {
    private val ip  = "127.0.0.1"
    private val port = 8080
    fun startClient() {
        try {
            val client = Socket(ip, port)
            val worker = WorkerWithServer(client)
            worker.sendMessageSelect("Математический анализ", "nsu.1course")
            worker.sendMessageSelect("Математическая логика", "nsu.1course")
            worker.selectReviews()
            //worker.insertReview("Как красиво")
            worker.logout()
        }catch (error : SocketException) {
            println("logout")
            //error.printStackTrace();
        }
    }
}