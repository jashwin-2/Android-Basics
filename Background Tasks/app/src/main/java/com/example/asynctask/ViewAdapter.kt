package com.example.asynctask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewAdapter(private val context : Context, private val list : List<String>) :
    RecyclerView.Adapter<ViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskName.text = list[position]
        holder.parentLayout.setOnClickListener{
            runTask(list[position],holder.status,holder.progressBar)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
   inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val taskName: TextView = view.findViewById(R.id.task_name)
        val progressBar:ProgressBar = view.findViewById(R.id.progressBar3)
         val status:TextView = view.findViewById(R.id.status)
        val parentLayout : LinearLayout =view.findViewById(R.id.parent_layout)

    }
    fun runTask(taskName : String,status : TextView,progressBar: ProgressBar){
        val task = Task(context,taskName,status,progressBar)
        Manager().runTask(task)
    }

}