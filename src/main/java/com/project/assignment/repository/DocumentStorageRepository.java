package com.project.assignment.repository;

import com.project.assignment.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentStorageRepository extends JpaRepository<Document, String> {

}
