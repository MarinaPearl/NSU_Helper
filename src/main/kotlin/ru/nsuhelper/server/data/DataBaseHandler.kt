package ru.nsuhelper.server.data
import ru.nsuhelper.server.data.Configs
import ru.nsuhelper.server.data.Const
import java.sql.Connection;
import java.sql.DriverManager

class DataBaseHandler : Configs() {
   private lateinit var dbConnection : Connection;

   fun getDbConnection() : Connection {
      var connectionString = "jdbc:mysql://" + dbHost + ":" +
      dbPort + "/" + dbName;
      Class.forName("com.mysql.cj.jdbc.Driver");
      dbConnection = DriverManager.getConnection(connectionString,
         dbUser, dbPass);
      return dbConnection;
   }

   fun insertInDb(subject : String, url : String, key : Int) {
      var table = "";
      when (key) {
         0 -> table = Const().COURSE_TABLE_1;
         1 -> table = Const().COURSE_TABLE_2;
         2 -> table = Const().COURSE_TABLE_3;
         3 -> table = Const().COURSE_TABLE_4;
      }
      val insert = "INSERT INTO " + table + "(" + Const().SUBJECT +
              "," + Const().URL + ")" +
              "VALUES(?,?)"
      val prSt = getDbConnection().prepareStatement(insert)
      prSt.setString(1, subject)
      prSt.setString(2, url)
      prSt.executeUpdate();
   }




}