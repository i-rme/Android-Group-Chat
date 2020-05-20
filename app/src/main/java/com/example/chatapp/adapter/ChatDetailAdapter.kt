package com.example.chatapp.adapter

import com.example.chatapp.data.Message
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.chatapp.provider.ChatListProvider
import com.example.chatapp.R
import kotlinx.android.synthetic.main.message_item_left.view.*

/**
 * Chat adapter too set the views from the activity
 *
 */
class ChatDetailAdapter(
    private val context: Context,
    private val messageList: MutableList<Message>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val message = getItem(position) as Message
        //Check if we are the user and the show the bubble
        if (ChatListProvider.username.equals(message.user)) {
            val rowView = convertView ?: LayoutInflater.from(context).inflate(
                R.layout.message_item_right,
                parent,
                false
            )
            val tvmessage = rowView.message as? TextView
            tvmessage?.text = message.text
            return rowView

        } else {
            val rowView = convertView ?: LayoutInflater.from(context).inflate(
                R.layout.message_item_left,
                parent,
                false
            )
            val tvmessage = rowView.message  as? TextView
            val tvUsername = rowView.tvUsername  as? TextView
            tvUsername?.text = message.user
            tvmessage?.text = message.text
            return rowView

        }
    }

    override fun getItem(position: Int): Any {
        return messageList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return messageList.size
    }

}