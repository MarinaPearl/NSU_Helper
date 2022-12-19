package ru.nsuhelper.client

import ru.nsuhelper.client.factory.ObjectWrapper
import ru.nsuhelper.client.factory.Subject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket
import java.util.*

class WorkerWithServer(socket: Socket) {
    private lateinit var writer: BufferedWriter
    private lateinit var reader: BufferedReader
    private lateinit var socket: Socket
    private var active: Boolean = true
    private var prop = Properties()
    private val pathProperty = "client.properties"

    init {
        try {
            this.socket = socket
            this.reader = createReader()
            this.writer = createWriter()
            this.prop = javaClass.classLoader.getResourceAsStream(pathProperty).use {
                Properties().apply { load(it) }
            }

        } catch (error: Exception) {
            error.printStackTrace()
        }

    }

    fun getSocket(): Socket {
        return socket;
    }

    private fun createReader(): BufferedReader {
        return BufferedReader(InputStreamReader(this.socket.getInputStream()));
    }

    private fun createWriter(): BufferedWriter {
        return BufferedWriter(OutputStreamWriter(this.socket.getOutputStream()));
    }

    private fun close() {
        reader.close();
        writer.close();
    }

    fun writeLine(message: String) {
        try {
            writer.write(message)
            writer.newLine()
            writer.flush()
        } catch (error: Exception) {
            close()
            error.printStackTrace()
        }
    }

    fun readLine(): String {
        var str: String = ""
        try {
            str = reader.readLine()
        } catch (error: Exception) {
            close()
            error.printStackTrace()
        }
        return str
    }

    fun startWork() {
        var sub = Subject()
        sub.setSubject("Математический анализ")
        sub.setCourse("nsu.1course")
        var objectWrapper = ObjectWrapper()
        var gson : String =  objectWrapper.getGson(sub)
        writeLine(gson)
    }
}