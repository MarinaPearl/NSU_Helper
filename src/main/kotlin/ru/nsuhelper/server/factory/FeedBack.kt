package ru.nsuhelper.server.factory

import lombok.Getter
import lombok.Setter

class FeedBack(): WorkerTypeCommand() {
    @Getter
    @Setter
    private var feedback : String = ""

    init {
        setCommand(Constants().FEEDBACK)
    }

    override fun runCommand() {
        println("999")
    }
}