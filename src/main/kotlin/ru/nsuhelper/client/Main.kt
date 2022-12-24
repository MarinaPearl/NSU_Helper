package ru.nsuhelper.client

import ru.nsuhelper.client.factory.FeedBack
import ru.nsuhelper.client.factory.Subject

fun main() {
    println("Client connect")
    val client = ClientWork();
    client.startClient();
}