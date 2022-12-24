package ru.nsuhelper.server.factory

import ru.nsuhelper.server.data.WorkerWithData


class ListReviews : WorkerTypeCommand() {
    private var listReviews = ArrayList<String>()

    fun setListReviews(list : ArrayList<String>) {
        listReviews = list
    }

    fun getList() : ArrayList<String> {
        return listReviews
    }

    init {
        setCommand(Constants().LISTREVIEWS)
    }

    override fun runCommand() {
        var selectData = WorkerWithData()
        setListReviews(selectData.selectReviews())
    }
}