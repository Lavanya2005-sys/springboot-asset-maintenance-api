package com.example.assetmaintenance.repository;

import com.example.assetmaintenance.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Asset entity.
 * Extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
}
