package com.majesticreader.domain.usecase

import com.majesticreader.domain.repository.BookmarkRepository
import com.majesticreader.domain.entity.Bookmark
import com.majesticreader.domain.entity.Document

class RemoveBookmark(private val bookmarksRepository: BookmarkRepository) {

    suspend operator fun invoke(document: Document, bookmark: Bookmark) = bookmarksRepository
        .removeBookmark(document, bookmark)
}