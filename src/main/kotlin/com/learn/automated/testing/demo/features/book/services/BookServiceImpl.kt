package com.learn.automated.testing.demo.features.book.services

import com.learn.automated.testing.demo.features.book.BookRepository
import com.learn.automated.testing.demo.features.book.exceptions.BookException
import com.learn.automated.testing.demo.features.book.models.Book
import com.learn.automated.testing.demo.features.book.request.BookRequest
import com.learn.automated.testing.demo.features.book.response.BookDetailResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookServiceImpl constructor(
        val repository: BookRepository
) : BookService {

    override fun create(bookRequest: BookRequest): BookDetailResponse {
        val book = Book(
                title = bookRequest.title!!,
                totalPage = bookRequest.totalPage!!,
                price = bookRequest.price!!
        )
        return BookDetailResponse(
                true,
                "success",
                repository.save(book)
        )
    }

    @Throws(BookException::class)
    override fun update(id: Long, bookRequest: BookRequest): BookDetailResponse {
        val bookOptional: Optional<Book> = repository.findById(id)
        if (!bookOptional.isPresent) {
            throw BookException.notFound()
        }
        val book = bookOptional.get()
        book.title = bookRequest.title!!
        book.totalPage = bookRequest.totalPage!!
        return BookDetailResponse(true, "success", repository.save(book))
    }

}