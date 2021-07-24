package com.majesticreader.data

import com.majesticreader.domain.entity.Bookmark
import com.majesticreader.domain.entity.Document

interface BookmarkDataSource {

    suspend fun add(document: Document, bookmark: Bookmark)

    suspend fun read(document: Document): List<Bookmark>

    suspend fun remove(document: Document, bookmark: Bookmark)
}