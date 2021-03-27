package pe.flavia.storage.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import pe.flavia.storage.entities.Book

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/24/21 - 4:58 PM
    Solera Mobile
*/

class BooksDatabase(context: Context): SQLiteOpenHelper(context, BooksDBKeys.DATABASE_NAME, null, BooksDBKeys.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE ${BooksDBKeys.BD_TABLE_NAME} (${BooksDBKeys.BD_KEY_ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ${BooksDBKeys.BD_KEY_NAME}  TEXT, ${BooksDBKeys.BD_KEY_AUTHOR} TEXT )"

        Log.v("CONSOLE","sql $sql")
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS ${BooksDBKeys.BD_TABLE_NAME}"
        db?.execSQL(sql)
    }
}