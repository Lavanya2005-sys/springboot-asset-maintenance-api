package com.example.assetmaintenance.service;

import com.example.assetmaintenance.exception.ResourceNotFoundException;
import com.example.assetmaintenance.model.Asset;
import com.example.assetmaintenance.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link AssetService} containing all business logic
 * for asset maintenance operations.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Asset createAsset(Asset asset) {
        log.debug("Creating new asset: {}", asset.getName());
        Asset saved = assetRepository.save(asset);
        log.info("Asset created with ID: {}", saved.getId());
        return saved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Asset> getAllAssets() {
        log.debug("Fetching all assets");
        return assetRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Asset getAssetById(Long id) {
        log.debug("Fetching asset with ID: {}", id);
        return assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Asset updateAssetStatus(Long id, String status) {
        log.debug("Updating status for asset ID: {} to '{}'", id, status);
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", id));
        asset.setStatus(status);
        Asset updated = assetRepository.save(asset);
        log.info("Asset ID: {} status updated to '{}'", id, status);
        return updated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteAsset(Long id) {
        log.debug("Deleting asset with ID: {}", id);
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", id));
        assetRepository.delete(asset);
        log.info("Asset ID: {} deleted successfully", id);
    }
}
