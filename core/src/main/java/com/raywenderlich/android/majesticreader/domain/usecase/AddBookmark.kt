package com.raywenderlich.android.majesticreader.domain.usecase

import com.raywenderlich.android.majesticreader.domain.repository.BookmarkRepository
import com.raywenderlich.android.majesticreader.domain.entity.Bookmark
import com.raywenderlich.android.majesticreader.domain.entity.Document

class AddBookmark(private val bookmarkRepository: BookmarkRepository) {

    suspend operator fun invoke(document: Document, bookmark: Bookmark) =
        bookmarkRepository.addBookmark(document, bookmark)
}