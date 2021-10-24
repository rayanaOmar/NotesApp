package com.example.notesapp.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    //The constructor -> database name and database version
    companion object{
        private const val DATABASE_NAME = "MYDatabase"
        private const val DATABASE_VERSION = 1
        private const val NOTE_TABLE = "NotesTable"

        private const val KEYID = "id"
        private const val KEYNOTE = "note"
    }
    //Query for creating the tables
    override fun onCreate(database: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $NOTE_TABLE($KEYID INTEGER Primary Key, $KEYNOTE Text)")
        database?.execSQL(createTable)
    }
    //when the version number is increased this will be executed
    override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int) {
        database!!.execSQL("DROP TABLE IF EXISTS $NOTE_TABLE")
        onCreate(database)
    }
    //function to add the note
    //the note will be form NoteDetails Class (Type)
    fun addMoreNote(note: NoteDetails): Long{
        val database = this.writableDatabase
        //add the note to contentValues
        val contentValues = ContentValues()
        contentValues.put(KEYNOTE, note.noteText)

        //add the note into SQLite database
        val successful = database.insert(NOTE_TABLE, null , contentValues)
        database.close()

        //return the note form the cursor after save it
        return successful
    }
}