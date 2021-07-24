package com.majesticreader.framework.db

import com.majesticreader.data.OpenDocumentDataSource
import com.majesticreader.domain.entity.Document

class InMemoryOpenDocumentDataSource : OpenDocumentDataSource {

    private var openDocument: Document = Document.EMPTY

    override fun setOpenDocument(document: Document) {
        openDocument = document
    }

    override fun getOpenDocument() = openDocument
}