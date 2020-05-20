package com.example.chatapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.chatapp.R
import com.example.chatapp.data.Chat
import kotlinx.android.synthetic.main.list_chat_item.view.*
import java.util.*
import kotlin.collections.ArrayList

private val randomGenerator = Random()

/**
 * Chat list adapter too set the views from the activity
 */
class ChatListAdapter(private val context: Context, private val chatList: MutableList<Chat>) :
    BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val chat = getItem(position) as Chat

        val rowView = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.list_chat_item,
            parent,
            false
        )
        // icon randomizer for the chat
        var imageList = ArrayList<Int>()
        imageList = initImageList(imageList)
        val index = randomGenerator.nextInt(imageList.size)
        Log.v("IMAGE SELECTION: ", "$index")
        val tvChatName = rowView.tvChatNameList as TextView
        val avatarChat = rowView.ivAvatarChat as ImageView

        tvChatName.text = chat.chatName
        avatarChat.setImageResource(imageList[index])

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

    private fun initImageList(imageList: ArrayList<Int>): ArrayList<Int> {
        imageList.add(R.mipmap.ic_launcher_green_round)
        imageList.add(R.mipmap.ic_launcher_red_round)
        return imageList
    }

}