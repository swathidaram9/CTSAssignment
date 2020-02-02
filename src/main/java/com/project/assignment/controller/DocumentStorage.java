package com.project.assignment.controller;

import com.project.assignment.exception.DocumentNotFoundException;
import com.project.assignment.service.DocumentStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/storage")
public class DocumentStorage {

    private DocumentStorageService documentStorageService;

    @Autowired
    public DocumentStorage(final DocumentStorageService documentStorageService) {
        this.documentStorageService = documentStorageService;
    }

    @PostMapping(value = "/documents", produces = "text/plain;charset=us-ascii")
    @ResponseStatus(HttpStatus.CREATED)
    public String createDocumentForStorage(@RequestBody String documentContent) {
        return documentStorageService.saveDocumentDetails(documentContent);
    }

    @GetMapping(value = "/documents/{docId}", produces = "text/plain;charset=us-ascii")
    @ResponseStatus(HttpStatus.OK)
    public String getDocument(@PathVariable String docId) {
        return documentStorageService.getDocumentDetails(docId);
    }

    @PutMapping("/documents/{docId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStoredDocument(@PathVariable String docId, @RequestBody String updatedDocumentContent) {
        // Check for document exists or not.
        documentStorageService.getDocumentDetails(docId);
        // Update the document content.
        documentStorageService.updateStoredDocument(docId, updatedDocumentContent);
    }

    @DeleteMapping("/documents/{docId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStoredDocument(@PathVariable String docId) {
        // Check for document exists or not.
        documentStorageService.getDocumentDetails(docId);
        // Delete the document from the DB.
        documentStorageService.deleteStoredDocumentDetails(docId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DocumentNotFoundException.class)
    public void handleException (DocumentNotFoundException exception) {
        // TODO: This needs to be implemented.
    }
}