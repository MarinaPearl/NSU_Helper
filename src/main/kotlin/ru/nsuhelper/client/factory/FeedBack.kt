package ru.nsuhelper.client.factory

import lombok.Getter
import lombok.Setter

class FeedBack : WorkerTypeCommand() {
    @Getter @Setter
    private var feedback : String = ""

    init {
        println(javaClass.name)
        setCommand(Constants().FEEDBACK)
    }
}