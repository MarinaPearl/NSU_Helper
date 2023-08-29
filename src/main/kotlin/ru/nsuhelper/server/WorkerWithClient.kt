package ru.nsuhelper.server

import com.google.gson.Gson
import ru.nsuhelper.server.factory.Constants
import ru.nsuhelper.server.factory.ObjectWrapper
import ru.nsuhelper.server.factory.ReaderClass
import ru.nsuhelper.server.factory.TypeCommand
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket
import java.util.Properties

class WorkerWithClient(socket: Socket) : Thread() {
    private lateinit var writer: BufferedWriter
    private lateinit var reader: BufferedReader
    private lateinit var socket: Socket
    private var active: Boolean = true
    private var prop = Properties()
    private val pathProperty = "factory.properties"

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
            //error.printStackTrace()
            println("client bye")
        }
        return str
    }


    override fun run() {
        try {
            while (active) {
                workWithGson()
            }
        }catch (error : Exception) {
            println("Client bye")
        }
    }

    private fun workWithGson() {
        var message: String = ""
        message = readLine()
        var gson = Gson()
        var command : TypeCommand = Class.forName(prop.getProperty(gson.fromJson(message, ReaderClass::class.java).getCommand())).newInstance() as TypeCommand
        if (gson.fromJson(message, ReaderClass::class.java).getCommand() == Constants().LOGOUT) {
            active = false
        }
        command = gson.fromJson(message, command.javaClass)
        command.runCommand()
        var objectWrapper = ObjectWrapper()
        var retMessage: String = objectWrapper.getGson(command)
        writeLine(retMessage)
    }



}