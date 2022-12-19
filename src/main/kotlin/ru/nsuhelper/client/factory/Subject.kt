package ru.nsuhelper.client.factory

import lombok.Getter
import lombok.Setter


class Subject : WorkerTypeCommand() {
    //@Getter @Setter
    private var subject: String = ""
    private var course: String = ""

    init {
        setCommand(Constants().SUBJECT)
    }

    fun setSubject(sub: String) {
        this.subject = sub
    }

    fun setCourse(cr : String) {
        this.course = cr
    }
}