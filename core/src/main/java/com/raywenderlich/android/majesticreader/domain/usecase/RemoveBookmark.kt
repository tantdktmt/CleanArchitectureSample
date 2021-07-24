package com.raywenderlich.android.majesticreader.domain.usecase

import com.raywenderlich.android.majesticreader.domain.repository.BookmarkRepository
import com.raywenderlich.android.majesticreader.domain.entity.Bookmark
import com.raywenderlich.android.majesticreader.domain.entity.Document

class RemoveBookmark(private val bookmarksRepository: BookmarkRepository) {

    suspend operator fun invoke(document: Document, bookmark: Bookmark) = bookmarksRepository
        .removeBookmark(document, bookmark)
}