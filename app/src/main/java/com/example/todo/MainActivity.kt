package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())
        todoItems.adapter = todoAdapter
        todoItems.layoutManager = LinearLayoutManager(this)

        addBtn.setOnClickListener {
            val title = todoTitle.text.toString()

            if(title.isNotEmpty()){
                val todo = Todo(title)
                todoAdapter.add(todo)
                todoTitle.text.clear()
            }
        }

        delBtn.setOnClickListener {
            todoAdapter.del()
        }
    }
}