package com.majesticreader.domain.usecase

import com.majesticreader.domain.repository.BookmarkRepository
import com.majesticreader.domain.entity.Document

class GetBookmarks(private val bookmarkRepository: BookmarkRepository) {

    suspend operator fun invoke(document: Document) = bookmarkRepository.getBookmarks(document)
}