package com.learning.books.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.books.model.BookResponse
import com.learning.headspace.repository.networking.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BooksViewModel : ViewModel() {

    private var _bookResponse = MutableLiveData<BookResponse>()

    val bookResponse:LiveData<BookResponse> get() = _bookResponse

    fun getBooks() {
        Repository.create().getBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _bookResponse.value = it
            },{
                print(it.toString())
            })
    }


}