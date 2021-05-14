package com.example.bookapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter(var bookListItems: List<Book>, val clickListener: (Book) -> Unit): RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bookListItems.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookListItems[position]
        holder.updateWithBook(book, clickListener)
    }
}