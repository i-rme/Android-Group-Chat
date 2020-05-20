package com.example.chatapp

import com.example.chatapp.data.Message
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.message_item_left.view.*

class ChatDetailAdapter(private val context: Context, private val  messageList :MutableList<Message>)  : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val message = getItem(position) as Message

        if (ChatListProvider.username.equals(message.user)){
            val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.message_item_right, parent, false)
            val tvmessage = rowView.message as TextView
            tvmessage.text = message.text
            return rowView

        }else{
            val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.message_item_left, parent, false)
            val tvmessage = rowView.message as TextView
            val tvUsername = rowView.tvUsername as TextView
            tvUsername.text = message.user
            tvmessage.text = message.text
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