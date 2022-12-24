package ru.nsuhelper.server.factory

import lombok.Getter
import lombok.Setter
import ru.nsuhelper.server.data.WorkerWithData


class Subject : WorkerTypeCommand(){

    private var subject : String = ""
    private var course: String = ""
    private var url: String = ""

    init {
        setCommand(Constants().SUBJECT)
    }

    fun setUrl(url : String) {
        this.url = url
    }

    fun getUrl() : String {
        return url
    }

    override fun runCommand() {
        var data = WorkerWithData()
        var url = data.select(subject, course)
        setUrl(url)
    }
}