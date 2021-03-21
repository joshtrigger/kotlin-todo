package com.example.todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter (private val todos: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,parent,false
            )
        )
    }

    fun add(todo:Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun del() {
        todos.removeAll { todo: Todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    private fun toggle(title: TextView, status: Boolean){
        if(status){
            title.paintFlags = title.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            title.paintFlags = title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            itemTitle.text = currentTodo.title
            cbDone.isChecked = currentTodo.isChecked
            toggle(itemTitle, currentTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggle(itemTitle, isChecked)
                currentTodo.isChecked=!currentTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}