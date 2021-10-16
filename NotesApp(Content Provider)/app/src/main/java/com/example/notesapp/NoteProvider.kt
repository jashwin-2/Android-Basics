package com.example.notesapp

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class NoteProvider : ContentProvider() {

    companion object {
        const val PROVIDER_NAME = "com.example.notesapp/NoteProvider"
        const val URL = "content://$PROVIDER_NAME/NOTE_TABLE"
        val CONTENT_URI = Uri.parse(URL)
        const val _ID = "_id"
        const val TITLE = "TITLE"
        const val DESCRIPTION = "DESCRIPTION"
        const val TIME = "TIME"
    }

    lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        val helper = MyHelper(context!!)
        db = helper.writableDatabase
        return db != null
    }

    override fun query(
        uri: Uri,
        col: Array<out String>?,
        condion: String?,
        values: Array<out String>?,
        p4: String?
    ): Cursor? {
    return db.query("NOTE_TABLE",col,condion,values,null,null,p4)
    }

    override fun getType(uri: Uri): String? {
    return "vnd.android.cursor.dir/vnd.example.note_table"
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri{
        db.insert("NOTE_TABLE", null, cv)
        context!!.contentResolver.notifyChange(uri, null)
        return uri
    }

    override fun delete(uri: Uri, condion: String?, p2: Array<out String>?): Int {
        val count = db.delete("NOTE_TABLE", condion, p2)
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun update(
        uri: Uri,
        cv: ContentValues?,
        condion: String?,
        p3: Array<out String>?
    ): Int {
        val count = db.update("NOTE_TABLE", cv, condion, p3)
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }
}