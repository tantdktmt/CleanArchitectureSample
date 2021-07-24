package com.raywenderlich.android.majesticreader.domain.usecase

import com.raywenderlich.android.majesticreader.domain.repository.DocumentRepository

class GetOpenDocument(private val documentRepository: DocumentRepository) {

    operator fun invoke() = documentRepository.getOpenDocument()
}
