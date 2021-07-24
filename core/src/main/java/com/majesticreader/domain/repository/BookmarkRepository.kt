package com.majesticreader.domain.repository

import com.majesticreader.data.BookmarkDataSource
import com.majesticreader.domain.entity.Bookmark
import com.majesticreader.domain.entity.Document

class BookmarkRepository(private val dataSource: BookmarkDataSource) {

    suspend fun addBookmark(document: Document, bookmark: Bookmark) =
        dataSource.add(document, bookmark)

    suspend fun getBookmarks(document: Document) = dataSource.read(document)

    suspend fun removeBookmark(document: Document, bookmark: Bookmark) =
        dataSource.remove(document, bookmark)
}