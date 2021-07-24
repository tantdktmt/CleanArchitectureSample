package com.majesticreader.data

import com.majesticreader.domain.entity.Document

interface OpenDocumentDataSource {

    fun setOpenDocument(document: Document)

    fun getOpenDocument(): Document
}