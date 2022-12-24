package ru.nsuhelper.client.factory

import lombok.Getter
import lombok.Setter
import ru.nsuhelper.client.controller.ControllerClient

class FeedBack : WorkerTypeCommand() {
    private var feedBack : String = ""
    private var listReviews = ArrayList<String>()

    fun setListReviews(list : ArrayList<String>) {
            listReviews = list
    }

    fun getList() : ArrayList<String> {
        return listReviews
    }

    fun setFeedBack(str : String) {
        this.feedBack = str
    }

    fun getFeedBack() : String {
        return feedBack
    }

    init {
        setCommand(Constants().FEEDBACK)
    }

    override fun outInWindow(controller: ControllerClient) {
            listReviews.forEach { println(it) }
        }
}

