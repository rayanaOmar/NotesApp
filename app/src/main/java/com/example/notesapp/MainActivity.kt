package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Database.DatabaseHandler
import com.example.notesapp.Database.NoteDetails

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var submitBtn: Button
    lateinit var recyclerView: RecyclerView

    lateinit var database: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        submitBtn = findViewById(R.id.button)
        recyclerView = findViewById(R.id.recyclerView)

        database = DatabaseHandler(this)

        submitBtn.setOnClickListener {
            val userEnter = editText.text.toString()
            database.addMoreNote(NoteDetails(0,userEnter))
            editText.text.clear()
            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
        }
    }
}