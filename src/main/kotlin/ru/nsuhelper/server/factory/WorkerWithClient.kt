package ru.nsuhelper.server.factory

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

class WorkerWithClient(socket: Socket) {
    private var writer: BufferedWriter
    private var reader: BufferedReader
    private var socket: Socket

    init {
        this.socket = socket
        this.reader = createReader()
        this.writer = createWriter()
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

    fun readLine() : String {
        var str : String = ""
        try {
            str = reader.readLine()

        }catch (error : Exception) {
            close()
            error.printStackTrace()
        }
        return str
    }



}