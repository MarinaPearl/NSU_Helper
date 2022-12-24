package ru.nsuhelper.client.factory

import ru.nsuhelper.client.controller.ControllerClient

class ListReviews : WorkerTypeCommand() {
    private var listReviews = ArrayList<String>()

    fun setListReviews(list : ArrayList<String>) {
        listReviews = list
    }

    fun getList() : ArrayList<String> {
        return listReviews
    }

    override fun outInWindow(controller: ControllerClient) {
        listReviews.forEach { println(it) }
    }

     init {
         setCommand(Constants().LISTREVIEWS)
     }

}