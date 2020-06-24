package com.learn.automated.testing.demo.features.book

import com.learn.automated.testing.demo.features.book.models.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long>