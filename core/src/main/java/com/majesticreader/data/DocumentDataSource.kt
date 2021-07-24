package com.majesticreader.data

import com.majesticreader.domain.entity.Document

interface DocumentDataSource {

    suspend fun add(document: Document)

    suspend fun readAll(): List<Document>

    suspend fun remove(document: Document)
}