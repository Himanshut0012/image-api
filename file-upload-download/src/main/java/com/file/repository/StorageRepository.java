package com.file.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.file.entity.ImageData;

@Repository
public interface StorageRepository extends JpaRepository<ImageData, Integer>{

	Optional<ImageData> findByName(String name);
}
