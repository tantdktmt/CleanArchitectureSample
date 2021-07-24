package com.raywenderlich.android.majesticreader.data

import com.raywenderlich.android.majesticreader.domain.entity.Document

interface OpenDocumentDataSource {

  fun setOpenDocument(document: Document)

  fun getOpenDocument(): Document
}