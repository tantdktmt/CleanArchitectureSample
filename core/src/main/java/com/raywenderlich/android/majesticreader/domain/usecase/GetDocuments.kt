package com.raywenderlich.android.majesticreader.domain.usecase

import com.raywenderlich.android.majesticreader.domain.repository.DocumentRepository

class GetDocuments(private val documentRepository: DocumentRepository) {

    suspend operator fun invoke() = documentRepository.getDocuments()
}
