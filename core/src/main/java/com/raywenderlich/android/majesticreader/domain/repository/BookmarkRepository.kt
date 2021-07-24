package com.raywenderlich.android.majesticreader.domain.repository

import com.raywenderlich.android.majesticreader.data.BookmarkDataSource
import com.raywenderlich.android.majesticreader.domain.entity.Bookmark
import com.raywenderlich.android.majesticreader.domain.entity.Document

class BookmarkRepository(private val dataSource: BookmarkDataSource) {
  suspend fun addBookmark(document: Document, bookmark: Bookmark) =
      dataSource.add(document, bookmark)

  suspend fun getBookmarks(document: Document) = dataSource.read(document)

  suspend fun removeBookmark(document: Document, bookmark: Bookmark) =
      dataSource.remove(document, bookmark)
}