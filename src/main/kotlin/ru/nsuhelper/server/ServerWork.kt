package ru.nsuhelper.server

import java.net.ServerSocket
import java.net.SocketException
import java.sql.Statement

class ServerWork {
    private var PORT = 8080;

    fun startServer() {
        val dataBase : DataBaseHandler = DataBaseHandler();
        val statementDataBase  = dataBase.getDbConnection().createStatement();
        val str : String = "Алгебра и геометрия"
        val result = statementDataBase.executeQuery("select * from nsu.1course where subject = '$str'")
        while (result.next()) {
            println(result.getString(2))
            println(result.getString(3))
            println()
        }
        //val server = ServerSocket(PORT);
//        println("Server started");
//        while (true) {
//            try {
//                val socket = server.accept();
//            } catch (error: SocketException) {
//                error.stackTrace;
//            }
//        }
    }
}