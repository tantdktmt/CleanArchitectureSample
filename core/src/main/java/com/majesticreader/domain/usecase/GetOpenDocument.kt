package com.majesticreader.domain.usecase

import com.majesticreader.domain.repository.DocumentRepository

class GetOpenDocument(private val documentRepository: DocumentRepository) {

    operator fun invoke() = documentRepository.getOpenDocument()
}
