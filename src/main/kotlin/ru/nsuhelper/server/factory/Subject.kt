package ru.nsuhelper.server.factory

import lombok.Getter
import lombok.Setter


class Subject : WorkerTypeCommand(){
    @Getter
    @Setter
    private val subject : String = ""

    init {
        setCommand(Constants().SUBJECT)
    }
}