package ru.nsuhelper.server.factory

abstract class WorkerTypeCommand : TypeCommand {
    private  var command : String = ""

    open fun setCommand(args : String) {
        this.command = args
    }

    open fun getCommand() : String {
        return  command
    }
}