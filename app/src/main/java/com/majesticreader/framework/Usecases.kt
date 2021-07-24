package com.majesticreader.framework

import com.majesticreader.domain.usecase.*

data class Usecases(
    val addBookmark: AddBookmark,
    val getBookmarks: GetBookmarks,
    val deleteBookmark: RemoveBookmark,
    val addDocument: AddDocument,
    val getDocuments: GetDocuments,
    val removeDocument: RemoveDocument,
    val getOpenDocument: GetOpenDocument,
    val setOpenDocument: SetOpenDocument
)
