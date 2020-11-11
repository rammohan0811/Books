package com.learning.headspace.repository.networking

import com.learning.books.model.BookResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    companion object {
        const val API_KEY = "DCGyptRLyPoL1Lqco7wz4x6opGLhM9Yr"
    }
    @GET("svc/books/v3/lists/current/hardcover-fiction.json")
    fun getBooks(@Query("api-key")apiKey:String = API_KEY): Observable<BookResponse>
}