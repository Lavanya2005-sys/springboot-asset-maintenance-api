package com.example.assetmaintenance.service;

import com.example.assetmaintenance.model.Asset;

import java.util.List;

/**
 * Service interface defining the business operations
 * for Asset Maintenance Management.
 */
public interface AssetService {

    /**
     * Creates and persists a new asset.
     *
     * @param asset the asset to create
     * @return the saved asset with generated ID
     */
    Asset createAsset(Asset asset);

    /**
     * Retrieves all assets in the system.
     *
     * @return list of all assets
     */
    List<Asset> getAllAssets();

    /**
     * Retrieves a single asset by its ID.
     *
     * @param id the asset ID
     * @return the matching asset
     * @throws com.example.assetmaintenance.exception.ResourceNotFoundException if
     *                                                                          not
     *                                                                          found
     */
    Asset getAssetById(Long id);

    /**
     * Updates the status of an existing asset.
     *
     * @param id     the asset ID
     * @param status the new status value
     * @return the updated asset
     * @throws com.example.assetmaintenance.exception.ResourceNotFoundException if
     *                                                                          not
     *                                                                          found
     */
    Asset updateAssetStatus(Long id, String status);

    /**
     * Deletes an asset by its ID.
     *
     * @param id the asset ID
     * @throws com.example.assetmaintenance.exception.ResourceNotFoundException if
     *                                                                          not
     *                                                                          found
     */
    void deleteAsset(Long id);
}
