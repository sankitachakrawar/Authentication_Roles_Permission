package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.FileUploadEntity;

public interface FileStorageRepository extends JpaRepository<FileUploadEntity, Long>{

}
