package pe.flavia.storage.data

import android.content.ContentValues
import pe.flavia.storage.entities.Book

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/24/21 - 5:09 PM
    Solera Mobile
*/

class BooksDataSourceRepository(private val booksDatabase: BooksDatabase) {

    fun getAllBooks(): List<Book>{
        val sqliteDatabase = booksDatabase.readableDatabase
        val books: MutableList<Book> = mutableListOf()
        val sql = "SELECT  * FROM ${BooksDBKeys.BD_TABLE_NAME}"
        val cursor = sqliteDatabase.rawQuery(sql,null)

        if(cursor.moveToFirst())
            do{
                val book = Book(cursor.getString(0).toInt(), cursor.getString(1), cursor.getString(2))
                books.add(book)
            }while(cursor.moveToNext())

        sqliteDatabase.close()
        return books.toList()
    }

    fun addBook(book: Book){
        val sqliteDatabase= booksDatabase.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(BooksDBKeys.BD_KEY_NAME,book.name)
        contentValues.put(BooksDBKeys.BD_KEY_AUTHOR,book.author)

        sqliteDatabase.insert(BooksDBKeys.BD_TABLE_NAME,null, contentValues)
        sqliteDatabase.close()
    }

    fun deleteBook(book: Book): Int{
        val sqliteDatabase= booksDatabase.writableDatabase
        val row = sqliteDatabase.delete(BooksDBKeys.BD_TABLE_NAME, "${BooksDBKeys.BD_KEY_ID}=?", arrayOf(book.id.toString()))
        sqliteDatabase.close()
        return row
    }

    fun updateBook(book: Book): Int{
        val sqliteDatabase= booksDatabase.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(BooksDBKeys.BD_KEY_NAME,book.name)
        contentValues.put(BooksDBKeys.BD_KEY_AUTHOR,book.author)

        val row = sqliteDatabase.update(BooksDBKeys.BD_TABLE_NAME, contentValues,
            "${BooksDBKeys.BD_KEY_ID}=?", arrayOf(book.id.toString()))
        sqliteDatabase.close()
        return row
    }
}