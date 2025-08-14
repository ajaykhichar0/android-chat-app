package com.example.chatapp

data class Message(
    val id: String,
    val text: String,
    val sender: MessageSender,
    var isSwipedForDelete: Boolean = false
)

enum class MessageSender {
    SENDER,
    RECEIVER
}
