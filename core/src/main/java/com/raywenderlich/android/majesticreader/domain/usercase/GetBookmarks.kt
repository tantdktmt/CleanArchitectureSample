package com.raywenderlich.android.majesticreader.domain.usercase

import com.raywenderlich.android.majesticreader.domain.repository.BookmarkRepository
import com.raywenderlich.android.majesticreader.domain.entity.Document

class GetBookmarks(private val bookmarkRepository: BookmarkRepository) {

  suspend operator fun invoke(document: Document) = bookmarkRepository.getBookmarks(document)
}