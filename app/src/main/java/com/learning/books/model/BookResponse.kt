package com.learning.books.model

data class BookResponse (val results: Result)


data class Result(val books:List<Book>)


data class Book(val title:String,
                val author:String,
                val book_image:String,
                val amazon_product_url:String
)