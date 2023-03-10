package ru.nsuhelper.server.factory

import lombok.Getter
import lombok.Setter
import ru.nsuhelper.server.data.DataBaseHandler
import ru.nsuhelper.server.data.WorkerWithData

class FeedBack(): WorkerTypeCommand() {
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

    override fun runCommand() {
        var data = DataBaseHandler()
        data.insertReview(feedBack)
        var selectData = WorkerWithData()
        setListReviews(selectData.selectReviews())
    }
}