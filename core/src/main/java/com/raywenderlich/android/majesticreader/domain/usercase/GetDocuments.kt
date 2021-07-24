package com.raywenderlich.android.majesticreader.domain.usercase

import com.raywenderlich.android.majesticreader.domain.repository.DocumentRepository

class GetDocuments(private val documentRepository: DocumentRepository) {
  suspend operator fun invoke() = documentRepository.getDocuments()
}
