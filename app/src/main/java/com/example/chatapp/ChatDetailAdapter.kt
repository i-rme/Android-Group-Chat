package com.example.chatapp

import com.example.chatapp.data.Message
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.list_chat_item.view.*
import kotlinx.android.synthetic.main.my_message_item.view.*


class ChatDetailAdapter(private val context: Context, private val  messageList :MutableList<Message>)  : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val message = getItem(position) as Message

        val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.my_message_item, parent, false)

        val tvmessage = rowView.message as TextView
        //val avatarChat = rowView.ivAvatarChat as ImageView

        tvmessage.text = message.text
        //avatarChat.setImageResource(R.mipmap.ic_launcher)

        return rowView
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