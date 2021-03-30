package com.kapirti.googlesolutionchallenge_2021

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class ChatAdapter(val loadedList:ArrayList<ModelForChat>):RecyclerView.Adapter<ChatAdapter.PaylasimHolder>() {
    class PaylasimHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaylasimHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_row,parent,false)
        return PaylasimHolder(view)
    }

    override fun onBindViewHolder(holder: PaylasimHolder, position: Int) {
        holder.itemView.recycler_row_textText.text=loadedList[position].text
        holder.itemView.recycler_row_textTime.text=loadedList[position].time
    }

    override fun getItemCount(): Int {
        return loadedList.size
    }
}