package com.example.assetmaintenance.controller;

import com.example.assetmaintenance.model.Asset;
import com.example.assetmaintenance.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller exposing endpoints for Asset Maintenance Management.
 * Base URL: /api/assets
 */
@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    /**
     * POST /api/assets
     * Creates a new asset.
     *
     * @param asset the request body containing asset details
     * @return the created asset with HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset created = assetService.createAsset(asset);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/assets
     * Retrieves all assets.
     *
     * @return list of all assets with HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    /**
     * GET /api/assets/{id}
     * Retrieves a single asset by ID.
     *
     * @param id path variable representing the asset ID
     * @return the matching asset with HTTP 200 OK, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAssetById(id));
    }

    /**
     * PUT /api/assets/{id}/status
     * Updates the status of an existing asset.
     *
     * @param id      path variable representing the asset ID
     * @param payload request body with a "status" field
     * @return the updated asset with HTTP 200 OK, or 404 if not found
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Asset> updateAssetStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {

        String newStatus = payload.get("status");
        Asset updated = assetService.updateAssetStatus(id, newStatus);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/assets/{id}
     * Deletes an asset by ID.
     *
     * @param id path variable representing the asset ID
     * @return HTTP 204 No Content on success, or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}
