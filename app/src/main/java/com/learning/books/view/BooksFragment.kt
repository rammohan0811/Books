package com.learning.books.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.books.R
import com.learning.books.view.adapter.BooksAdapter
import com.learning.books.viewModel.BooksViewModel
import kotlinx.android.synthetic.main.fragment_books.*

interface BookItemListner {
    fun buyOnAmazon(bookUrl:String)
}
/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : Fragment() {

    var viewModel:BooksViewModel? = null
    lateinit var booksAdapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)
        setUpUI()
        setUpListners()
        progressBar.visibility = View.VISIBLE
    }

    private fun setUpUI() {
        val bookItemListner = object :BookItemListner  {
            override fun buyOnAmazon(bookUrl:String) {
                val uri: Uri =
                    Uri.parse(bookUrl)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                ContextCompat.startActivity(requireContext(), intent, null)
            }
        }
        booksAdapter =
            BooksAdapter(requireContext(), bookItemListner)
        booksRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = booksAdapter
        }
    }

    private fun setUpListners() {
        viewModel?.bookResponse?.observe(viewLifecycleOwner, Observer {
            booksAdapter.setBooksData(it.results.books)
            progressBar.visibility = View.GONE

        })
        viewModel?.bookResponseError?.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel?.getBooks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel?.apply {
            bookResponse.removeObserver(Observer {  })
        }
    }
}