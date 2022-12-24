package ru.nsuhelper.client

import com.google.gson.Gson
import ru.nsuhelper.client.controller.ControllerClient
import ru.nsuhelper.client.factory.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Thread.sleep
import java.net.Socket
import java.util.*

class WorkerWithServer(socket: Socket) {
    private lateinit var writer: BufferedWriter
    private lateinit var reader: BufferedReader
    private lateinit var socket: Socket
    private var active: Boolean = true
    private var prop = Properties()
    private val pathProperty = "client.properties"
    private var controller = ControllerClient()
    private lateinit var thread: Thread

    init {
        try {
            this.socket = socket
            this.reader = createReader()
            this.writer = createWriter()
            this.prop = javaClass.classLoader.getResourceAsStream(pathProperty).use {
                Properties().apply { load(it) }
            }
            thread = Thread {
                readMessageServer()
            }.apply { start() }

        } catch (error: Exception) {
            //error.printStackTrace()
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
        socket.close()
        reader.close();
        writer.close();

    }

    fun writeLine(message: String) {
        try {
            writer.write(message)
            writer.newLine()
            writer.flush()
        } catch (error: Exception) {
            if (!socket.isClosed) {
                close()
            }
           // error.printStackTrace()
        }
    }

    fun readLine(): String {
        var str: String = ""
        try {
            str = reader.readLine()
        } catch (error: Exception) {
            if (!socket.isClosed) {
                close()
            }
            //error.printStackTrace()
        }
        return str
    }

    fun sendMessageSelect(subject: String, table: String) {
        var sub = Subject()
        sub.setSubject(subject)
        sub.setCourse(table)
        var objectWrapper = ObjectWrapper()
        var gson: String = objectWrapper.getGson(sub)
        writeLine(gson)
    }

    fun insertReview(str: String) {
        var review = FeedBack()
        review.setFeedBack(str)
        var objectWrapper = ObjectWrapper()
        var gson: String = objectWrapper.getGson(review)
        writeLine(gson)
    }

    fun logout() {
        var logout = Logout()
        var objectWrapper = ObjectWrapper()
        var gson: String = objectWrapper.getGson(logout)
        writeLine(gson)
        sleep(2000)
        active = false
        thread.interrupt()
        if (!socket.isClosed) {
            close()
        }
        System.exit(0)
    }

    fun selectReviews() {
        var reviews = ListReviews()
        var objectWrapper = ObjectWrapper()
        var gson : String = objectWrapper.getGson(reviews)
        writeLine(gson)
    }

    private fun readMessageServer() {
        while (active) {
            var str: String = ""
            str = readLine()
            var gson = Gson()
            var command: TypeCommand =
                Class.forName(prop.getProperty(gson.fromJson(str, ReaderClass::class.java).getCommand()))
                    .newInstance() as TypeCommand
            command = gson.fromJson(str, command.javaClass)
            command.outInWindow(controller)
        }
    }
}