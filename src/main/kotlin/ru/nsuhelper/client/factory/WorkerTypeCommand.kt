package ru.nsuhelper.client.factory

import lombok.Getter
import lombok.Setter
import ru.nsuhelper.client.controller.ControllerClient

open class WorkerTypeCommand  : TypeCommand {
   private  var command : String = ""

   open fun setCommand(args : String) {
               this.command = args
   }

    open fun getCommand() : String {
        return  command
    }

    override fun outInWindow(controller: ControllerClient) {
        TODO("Not yet implemented")
    }
}