package ru.nsuhelper.server.data

import ru.nsuhelper.server.data.DataBaseHandler

class WorkerWithData {
    private var NUMBER_SUBJECT = 3
    private var list = ArrayList<String>()

    fun select(subject : String, table : String) : String {
        val dataBase : DataBaseHandler = DataBaseHandler()
        val statementDataBase  = dataBase.getDbConnection().createStatement()
        val result = statementDataBase.executeQuery("select * from $table where subject = '$subject'")
        var url = ""
        while (result.next()) {
            url = result.getString(NUMBER_SUBJECT)
        }
        return url
    }

    fun selectReviews() : ArrayList<String> {
        val dataBase : DataBaseHandler = DataBaseHandler()
        val statementDataBase  = dataBase.getDbConnection().createStatement()
        val result = statementDataBase.executeQuery("select * from nsu.back")
        while (result.next()) {
            //println(result.getString(2))
            list.add(result.getString(2))
        }
        return list
    }
}