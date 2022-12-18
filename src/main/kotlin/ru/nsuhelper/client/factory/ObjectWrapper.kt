package ru.nsuhelper.client.factory

import com.google.gson.Gson

class ObjectWrapper {
    fun getGson( obj : TypeCommand) : String {
        var str : String = " "
        var gson : Gson = Gson()
        str = gson.toJson(obj)
        return str
    }
}