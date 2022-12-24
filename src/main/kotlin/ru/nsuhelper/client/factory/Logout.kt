package ru.nsuhelper.client.factory

class Logout : WorkerTypeCommand() {
    init {
        setCommand(Constants().LOGOUT)
    }
}