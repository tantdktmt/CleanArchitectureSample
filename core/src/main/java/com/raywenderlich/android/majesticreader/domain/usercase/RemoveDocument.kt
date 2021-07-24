package com.raywenderlich.android.majesticreader.domain.usercase

import com.raywenderlich.android.majesticreader.domain.repository.DocumentRepository
import com.raywenderlich.android.majesticreader.domain.entity.Document

class RemoveDocument(private val documentRepository: DocumentRepository) {
  suspend operator fun invoke(document: Document) = documentRepository.removeDocument(document)
}