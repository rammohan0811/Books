package com.learning.books.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learning.books.R
import com.learning.books.model.Book
import com.learning.books.view.BookItemListner
import kotlinx.android.synthetic.main.book_item_view.view.*


class BooksAdapter(val context:Context, val bookItemListner: BookItemListner) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

     var books: List<Book> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
       ViewHolder(LayoutInflater.from(context).inflate(R.layout.book_item_view, parent,false))


    override fun getItemCount(): Int = books.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
          holder.configureData(books.get(position))


    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
       fun configureData(book: Book) {
           itemView.apply {
               bookTitle.text = book.title
               author.text = book.author
               Glide.with(context)
                   .load(book.book_image)
                   .placeholder(R.drawable.ic_launcher_foreground)
                   .error(R.drawable.ic_launcher_background)
                   .fallback(R.drawable.ic_launcher_background)
                   .into(bookSnap)
               buy.setOnClickListener {
                   bookItemListner.buyOnAmazon(book.amazon_product_url)
               }
           }
       }
    }

    fun setBooksData(books:List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

}