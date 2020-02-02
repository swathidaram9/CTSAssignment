package com.project.assignment.service;

import com.project.assignment.exception.DocumentNotFoundException;
import com.project.assignment.model.Document;
import com.project.assignment.repository.DocumentStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentStorageService {

    private final DocumentStorageRepository documentStorageRepository;

    @Autowired
    public DocumentStorageService(final DocumentStorageRepository documentStorageRepository) {
        this.documentStorageRepository = documentStorageRepository;
    }

    public String saveDocumentDetails(final String documentContent) {
        return documentStorageRepository.save(new Document().withDocumentContents(documentContent)).getDocumentId();
    }

    public String getDocumentDetails(final String docId) {
        final Optional<Document> document = documentStorageRepository.findById(docId);
        if(!document.isPresent()) {
            throw new DocumentNotFoundException("Document not found.");
        }
        return document.get().getDocumentContents();
    }

    public void updateStoredDocument(final String documentId, final String updatedDocumentContent) {
        documentStorageRepository.save(new Document().withDocumentId(documentId).withDocumentContents(updatedDocumentContent));
    }

    public void deleteStoredDocumentDetails(String docId) {
        documentStorageRepository.deleteById(docId);
    }
}
