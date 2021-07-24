package com.raywenderlich.android.majesticreader.domain.usecase

import com.raywenderlich.android.majesticreader.domain.repository.DocumentRepository
import com.raywenderlich.android.majesticreader.domain.entity.Document

class SetOpenDocument(private val documentRepository: DocumentRepository) {

    operator fun invoke(document: Document) = documentRepository.setOpenDocument(document)
}