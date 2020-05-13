package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.data.Chat
import kotlinx.android.synthetic.main.list_chat_item.view.*


class ChatListAdapter(private val context: Context, private val chatList :MutableList<Chat>)  : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val chat = getItem(position) as Chat

        val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_chat_item, parent, false)

        val tvChatName = rowView.tvChatNameList as TextView
        val avatarChat = rowView.ivAvatarChat as ImageView

        tvChatName.text = chat.chatName
        avatarChat.setImageResource(R.mipmap.ic_launcher)

        return rowView
    }

    override fun getItem(position: Int): Any {
        return chatList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return chatList.size
    }


}