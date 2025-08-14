package com.example.chatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupRecyclerView()
        loadInitialMessages()
    }

    private fun initializeViews() {
        recyclerView = findViewById(R.id.recyclerViewChat)
    }

    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(messages) { message ->
            deleteMessage(message)
        }
        
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatAdapter
        }
    }

    private fun loadInitialMessages() {
        // Add initial messages as per requirement:
        // 1. First sender sends message
        // 2. Then receiver sends message
        
        val initialMessages = listOf(
            Message(
                id = "1",
                text = "Hello! How are you doing today?",
                sender = MessageSender.SENDER
            ),
            Message(
                id = "2",
                text = "Hi there! I'm doing great, thanks for asking. How about you?",
                sender = MessageSender.RECEIVER
            ),
            Message(
                id = "3",
                text = "I'm doing well too! Just working on some exciting projects.",
                sender = MessageSender.SENDER
            ),
            Message(
                id = "4",
                text = "That sounds awesome! I'd love to hear more about them.",
                sender = MessageSender.RECEIVER
            ),
            Message(
                id = "5",
                text = "Sure! I'm building a chat application with swipe-to-delete functionality.",
                sender = MessageSender.SENDER
            ),
            Message(
                id = "6",
                text = "Wow, that's really cool! Swipe left on any message to try the delete feature.",
                sender = MessageSender.RECEIVER
            )
        )

        messages.addAll(initialMessages)
        chatAdapter.notifyDataSetChanged()
        
        // Scroll to bottom to show latest messages
        recyclerView.scrollToPosition(messages.size - 1)
    }

    private fun deleteMessage(message: Message) {
        val position = messages.indexOf(message)
        if (position != -1) {
            messages.removeAt(position)
            chatAdapter.notifyItemRemoved(position)
        }
    }
}
