package com.example.bookapplication

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailed.readBook;


class DetailedActivity : AppCompatActivity() {
    private val firebaseRepo: FirebaseRepo = FirebaseRepo()
    lateinit var textTitle: TextView
    lateinit var imgBook: ImageView
    lateinit var textYear: TextView
    lateinit var textResume: TextView

    lateinit var book_id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        // Get Widgets
        textTitle = findViewById<TextView>(R.id.titleBook)
        imgBook = findViewById<ImageView>(R.id.img)
        textYear = findViewById<TextView>(R.id.year)
        textResume = findViewById<TextView>(R.id.resume)

        // Get val from intent
        val bookName = intent.getStringExtra("bookName")
        val bookRead = intent.getBooleanExtra("bookRead", false)
        val bookUrl = intent.getStringExtra("bookImg")
        val bookYear = intent.getStringExtra("bookYear")
        val bookResume = intent.getStringExtra("bookResume")
        book_id = intent.getStringExtra("bookId")!!

        // Set val from view
        textTitle.setText(bookName)
        textYear.setText(bookYear)
        textResume.setText(bookResume)
        Picasso.get().load(bookUrl).into(imgBook)
        readBook.isChecked = bookRead

        // set checkbox checked change listener
        readBook.setOnCheckedChangeListener{ buttonView, isChecked ->
            firebaseRepo.updateReadBook(book_id,isChecked)
        }
    }
}
