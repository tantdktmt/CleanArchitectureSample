package com.majesticreader.domain.usecase

import com.majesticreader.domain.repository.DocumentRepository
import com.majesticreader.domain.entity.Document

class AddDocument(private val documentRepository: DocumentRepository) {

    suspend operator fun invoke(document: Document) = documentRepository.addDocument(document)
}
