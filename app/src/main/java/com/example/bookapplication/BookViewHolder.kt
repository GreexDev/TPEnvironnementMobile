package com.example.bookapplication

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*

class BookViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    fun updateWithBook(book: Book, clickListener: (Book) -> Unit){
        // Set background color
        if (book.read){
            itemView.titleAndAuthor.setBackgroundColor(Color.GREEN)
        } else {
            itemView.titleAndAuthor.setBackgroundColor(Color.RED)
        }
        itemView.title.text = book.title
        itemView.author.text = book.author
        Picasso.get().load(book.img_url).into(itemView.image)

        itemView.setOnClickListener {
            clickListener(book)
        }
    }
}