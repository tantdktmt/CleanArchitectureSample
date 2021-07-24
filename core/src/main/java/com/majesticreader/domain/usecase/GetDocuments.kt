package com.majesticreader.domain.usecase

import com.majesticreader.domain.repository.DocumentRepository

class GetDocuments(private val documentRepository: DocumentRepository) {

    suspend operator fun invoke() = documentRepository.getDocuments()
}
