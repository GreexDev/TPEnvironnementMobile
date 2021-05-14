package com.example.bookapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_book.*
import java.util.ArrayList

class ListBookActivity : AppCompatActivity(), (Book) -> Unit {
    private val firebaseRepo: FirebaseRepo = FirebaseRepo()

    private var bookList: List<Book> = ArrayList()
    private var bookListAdapter: BookListAdapter = BookListAdapter(bookList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book)

        // get data
        loadData()

        // Fill view with adapter
        book_list.layoutManager = LinearLayoutManager(this)
        book_list.adapter = bookListAdapter
    }

    private fun loadData(){
        firebaseRepo.getBookList().addOnCompleteListener {
            if (it.isSuccessful){
                bookList = it.result!!.toObjects(Book::class.java)
                bookListAdapter.bookListItems = bookList
                bookListAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this,"Impossible de charger les données", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun invoke(book: Book) {
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra("bookName",book.title)
        intent.putExtra("bookRead",book.read)
        intent.putExtra("bookImg",book.img_url)
        intent.putExtra("bookYear",book.year)
        intent.putExtra("bookResume",book.resume)
        intent.putExtra("bookId",book.id)
        startActivity(intent)
    }
}