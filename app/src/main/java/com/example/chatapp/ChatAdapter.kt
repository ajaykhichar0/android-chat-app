package com.example.chatapp

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class ChatAdapter(
    private val messages: MutableList<Message>,
    private val onDeleteMessage: (Message) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_SENDER = 1
        private const val VIEW_TYPE_RECEIVER = 2
        private const val SWIPE_THRESHOLD = 100f
    }

    override fun getItemViewType(position: Int): Int {
        return when (messages[position].sender) {
            MessageSender.SENDER -> VIEW_TYPE_SENDER
            MessageSender.RECEIVER -> VIEW_TYPE_RECEIVER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SENDER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_sender, parent, false)
                SenderViewHolder(view)
            }
            VIEW_TYPE_RECEIVER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_receiver, parent, false)
                ReceiverViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder) {
            is SenderViewHolder -> holder.bind(message)
            is ReceiverViewHolder -> holder.bind(message)
        }
    }

    override fun getItemCount(): Int = messages.size

    inner class SenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText: TextView = itemView.findViewById(R.id.messageText)
        private val messageContainer: LinearLayout = itemView.findViewById(R.id.messageContainer)
        private val deleteContainer: LinearLayout = itemView.findViewById(R.id.deleteContainer)
        private val deleteButton: TextView = itemView.findViewById(R.id.deleteButton)
        private val deleteIcon: TextView = itemView.findViewById(R.id.deleteIcon)

        private var startX = 0f
        private var startY = 0f

        fun bind(message: Message) {
            messageText.text = message.text
            
            // Show/hide delete container based on swipe state
            deleteContainer.visibility = if (message.isSwipedForDelete) View.VISIBLE else View.GONE

            // Set up touch listener for swipe detection
            messageContainer.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startX = event.x
                        startY = event.y
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        val endX = event.x
                        val endY = event.y
                        val deltaX = startX - endX
                        val deltaY = abs(startY - endY)

                        // Check if it's a left swipe (deltaX > 0) and not vertical
                        if (deltaX > SWIPE_THRESHOLD && deltaY < SWIPE_THRESHOLD) {
                            // Left swipe detected - show delete option
                            message.isSwipedForDelete = true
                            notifyItemChanged(adapterPosition)
                        } else if (deltaX < -SWIPE_THRESHOLD && deltaY < SWIPE_THRESHOLD) {
                            // Right swipe detected - hide delete option
                            message.isSwipedForDelete = false
                            notifyItemChanged(adapterPosition)
                        }
                        true
                    }
                    else -> false
                }
            }

            // Set up delete button click listener
            deleteButton.setOnClickListener {
                onDeleteMessage(message)
            }

            deleteIcon.setOnClickListener {
                onDeleteMessage(message)
            }
        }
    }

    inner class ReceiverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText: TextView = itemView.findViewById(R.id.messageText)
        private val messageContainer: LinearLayout = itemView.findViewById(R.id.messageContainer)
        private val deleteContainer: LinearLayout = itemView.findViewById(R.id.deleteContainer)
        private val deleteButton: TextView = itemView.findViewById(R.id.deleteButton)
        private val deleteIcon: TextView = itemView.findViewById(R.id.deleteIcon)

        private var startX = 0f
        private var startY = 0f

        fun bind(message: Message) {
            messageText.text = message.text
            
            // Show/hide delete container based on swipe state
            deleteContainer.visibility = if (message.isSwipedForDelete) View.VISIBLE else View.GONE

            // Set up touch listener for swipe detection
            messageContainer.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startX = event.x
                        startY = event.y
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        val endX = event.x
                        val endY = event.y
                        val deltaX = startX - endX
                        val deltaY = abs(startY - endY)

                        // Check if it's a left swipe (deltaX > 0) and not vertical
                        if (deltaX > SWIPE_THRESHOLD && deltaY < SWIPE_THRESHOLD) {
                            // Left swipe detected - show delete option
                            message.isSwipedForDelete = true
                            notifyItemChanged(adapterPosition)
                        } else if (deltaX < -SWIPE_THRESHOLD && deltaY < SWIPE_THRESHOLD) {
                            // Right swipe detected - hide delete option
                            message.isSwipedForDelete = false
                            notifyItemChanged(adapterPosition)
                        }
                        true
                    }
                    else -> false
                }
            }

            // Set up delete button click listener
            deleteButton.setOnClickListener {
                onDeleteMessage(message)
            }

            deleteIcon.setOnClickListener {
                onDeleteMessage(message)
            }
        }
    }
}
