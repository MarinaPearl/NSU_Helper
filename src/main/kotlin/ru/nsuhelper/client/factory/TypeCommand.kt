package ru.nsuhelper.client.factory

import ru.nsuhelper.client.controller.ControllerClient

interface TypeCommand {
    fun outInWindow(controller : ControllerClient)
}