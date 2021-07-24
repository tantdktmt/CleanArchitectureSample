package com.majesticreader.domain.usecase

import com.majesticreader.domain.repository.DocumentRepository
import com.majesticreader.domain.entity.Document

class SetOpenDocument(private val documentRepository: DocumentRepository) {

    operator fun invoke(document: Document) = documentRepository.setOpenDocument(document)
}