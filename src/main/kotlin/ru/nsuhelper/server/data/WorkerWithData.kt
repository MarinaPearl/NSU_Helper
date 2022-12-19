package ru.nsuhelper.server.data

import ru.nsuhelper.server.data.DataBaseHandler

class WorkerWithData {
    fun select(subject : String, table : String) {
        val dataBase : DataBaseHandler = DataBaseHandler()
        val statementDataBase  = dataBase.getDbConnection().createStatement()
        val result = statementDataBase.executeQuery("select * from $table where subject = '$subject'")
        while (result.next()) {
            println(result.getString(2))
            println(result.getString(3))
            println()
        }
    }
}