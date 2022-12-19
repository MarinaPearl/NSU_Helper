package ru.nsuhelper.server.factory

import lombok.Getter
import lombok.Setter
import ru.nsuhelper.server.data.WorkerWithData


class Subject : WorkerTypeCommand(){

    private var subject : String = ""
    private var course: String = ""
    init {
        setCommand(Constants().SUBJECT)
    }

    override fun runCommand() {
        var data = WorkerWithData()
        data.select(subject, course)
    }
}