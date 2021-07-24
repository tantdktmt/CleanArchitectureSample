package com.raywenderlich.android.majesticreader.data

import com.raywenderlich.android.majesticreader.domain.entity.Bookmark
import com.raywenderlich.android.majesticreader.domain.entity.Document

interface BookmarkDataSource {

  suspend fun add(document: Document, bookmark: Bookmark)

  suspend fun read(document: Document): List<Bookmark>

  suspend fun remove(document: Document, bookmark: Bookmark)
}