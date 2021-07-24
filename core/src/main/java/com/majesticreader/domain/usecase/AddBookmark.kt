package com.majesticreader.domain.usecase

import com.majesticreader.domain.repository.BookmarkRepository
import com.majesticreader.domain.entity.Bookmark
import com.majesticreader.domain.entity.Document

class AddBookmark(private val bookmarkRepository: BookmarkRepository) {

    suspend operator fun invoke(document: Document, bookmark: Bookmark) =
        bookmarkRepository.addBookmark(document, bookmark)
}