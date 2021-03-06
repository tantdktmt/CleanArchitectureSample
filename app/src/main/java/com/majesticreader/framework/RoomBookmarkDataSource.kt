package com.majesticreader.framework

import android.content.Context
import com.majesticreader.data.BookmarkDataSource
import com.majesticreader.domain.entity.Bookmark
import com.majesticreader.domain.entity.Document
import com.majesticreader.framework.db.BookmarkEntity
import com.majesticreader.framework.db.MajesticReaderDatabase

class RoomBookmarkDataSource(context: Context) : BookmarkDataSource {

    private val bookmarkDao = MajesticReaderDatabase.getInstance(context).bookmarkDao()

    override suspend fun add(document: Document, bookmark: Bookmark) =
        bookmarkDao.addBookmark(
            BookmarkEntity(
                documentUri = document.url,
                page = bookmark.page
            )
        )

    override suspend fun read(document: Document): List<Bookmark> = bookmarkDao
        .getBookmarks(document.url).map { Bookmark(it.id, it.page) }

    override suspend fun remove(document: Document, bookmark: Bookmark) =
        bookmarkDao.removeBookmark(
            BookmarkEntity(id = bookmark.id, documentUri = document.url, page = bookmark.page)
        )
}