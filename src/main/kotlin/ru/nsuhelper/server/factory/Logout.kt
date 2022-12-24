package ru.nsuhelper.server.factory


class Logout : WorkerTypeCommand() {
    init {
        setCommand(Constants().LOGOUT)
    }

    override fun runCommand() {
        println("Client bye")
    }
}