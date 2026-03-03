package com.example.assetmaintenance.service;

import com.example.assetmaintenance.exception.ResourceNotFoundException;
import com.example.assetmaintenance.model.Asset;
import com.example.assetmaintenance.repository.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link AssetServiceImpl} using Mockito.
 * The repository is mocked so tests run without a database.
 */
@ExtendWith(MockitoExtension.class)
class AssetServiceImplTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetServiceImpl assetService;

    private Asset sampleAsset;

    @BeforeEach
    void setUp() {
        sampleAsset = Asset.builder()
                .id(1L)
                .name("Industrial Pump")
                .type("Mechanical")
                .status("ACTIVE")
                .lastMaintenanceDate(LocalDate.of(2025, 1, 15))
                .nextMaintenanceDate(LocalDate.of(2025, 7, 15))
                .build();
    }

    // -----------------------------------------------------------------------
    // createAsset
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("createAsset - should save and return the new asset")
    void createAsset_ShouldReturnSavedAsset() {
        when(assetRepository.save(any(Asset.class))).thenReturn(sampleAsset);

        Asset result = assetService.createAsset(sampleAsset);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Industrial Pump");
        verify(assetRepository, times(1)).save(any(Asset.class));
    }

    // -----------------------------------------------------------------------
    // getAllAssets
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("getAllAssets - should return all assets as a list")
    void getAllAssets_ShouldReturnList() {
        when(assetRepository.findAll()).thenReturn(List.of(sampleAsset));

        List<Asset> assets = assetService.getAllAssets();

        assertThat(assets).hasSize(1);
        assertThat(assets.get(0).getName()).isEqualTo("Industrial Pump");
        verify(assetRepository, times(1)).findAll();
    }

    // -----------------------------------------------------------------------
    // getAssetById
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("getAssetById - should return asset when found")
    void getAssetById_WhenFound_ShouldReturnAsset() {
        when(assetRepository.findById(1L)).thenReturn(Optional.of(sampleAsset));

        Asset result = assetService.getAssetById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(assetRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("getAssetById - should throw ResourceNotFoundException when not found")
    void getAssetById_WhenNotFound_ShouldThrowException() {
        when(assetRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> assetService.getAssetById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Asset")
                .hasMessageContaining("99");

        verify(assetRepository, times(1)).findById(99L);
    }

    // -----------------------------------------------------------------------
    // updateAssetStatus
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("updateAssetStatus - should update status and return updated asset")
    void updateAssetStatus_ShouldReturnUpdatedAsset() {
        Asset updatedAsset = Asset.builder()
                .id(1L)
                .name("Industrial Pump")
                .type("Mechanical")
                .status("UNDER_MAINTENANCE")
                .lastMaintenanceDate(LocalDate.of(2025, 1, 15))
                .nextMaintenanceDate(LocalDate.of(2025, 7, 15))
                .build();

        when(assetRepository.findById(1L)).thenReturn(Optional.of(sampleAsset));
        when(assetRepository.save(any(Asset.class))).thenReturn(updatedAsset);

        Asset result = assetService.updateAssetStatus(1L, "UNDER_MAINTENANCE");

        assertThat(result.getStatus()).isEqualTo("UNDER_MAINTENANCE");
        verify(assetRepository, times(1)).findById(1L);
        verify(assetRepository, times(1)).save(any(Asset.class));
    }

    @Test
    @DisplayName("updateAssetStatus - should throw ResourceNotFoundException when asset not found")
    void updateAssetStatus_WhenNotFound_ShouldThrowException() {
        when(assetRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> assetService.updateAssetStatus(99L, "RETIRED"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");

        verify(assetRepository, never()).save(any(Asset.class));
    }

    // -----------------------------------------------------------------------
    // deleteAsset
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("deleteAsset - should delete asset when found")
    void deleteAsset_WhenFound_ShouldDeleteSuccessfully() {
        when(assetRepository.findById(1L)).thenReturn(Optional.of(sampleAsset));
        doNothing().when(assetRepository).delete(sampleAsset);

        assetService.deleteAsset(1L);

        verify(assetRepository, times(1)).findById(1L);
        verify(assetRepository, times(1)).delete(sampleAsset);
    }

    @Test
    @DisplayName("deleteAsset - should throw ResourceNotFoundException when asset not found")
    void deleteAsset_WhenNotFound_ShouldThrowException() {
        when(assetRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> assetService.deleteAsset(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");

        verify(assetRepository, never()).delete(any(Asset.class));
    }
}
