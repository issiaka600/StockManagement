package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import com.traore.stockmanagement.dto.lineStorage.LineStorageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.math.BigDecimal;
import java.util.List;

public interface LineStorageAPI {
    @PutMapping(value = "/line-storages/{id}/quantity/{quantity}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier une ligne de stockage", description = "Cette méthode permet de changer la quantité d'une ligne de stockage d'un produit dans un magasin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantité de ligne de stockage modifiée avec succès",
                    content = @Content(schema = @Schema(implementation = LineStorageDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour la modification d'une ligne de stockage"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    LineStorageDTO update(@PathVariable @Parameter(description = "Identifiant de la ligne de stockage", required = true)
                          String id,@PathVariable @Parameter(description = "Nouvelle quantité de la ligne de stockage", required = true)
                          BigDecimal quantity);


    @GetMapping(value = "/line-storages/products/{productId}/departmentStores/{departmentStoreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir la ligne de stockage d'un produit dans un magasin", description = "Cette méthode permet d'obtenir la ligne de stockage d'un produit dans un magasin")
    @ApiResponse(responseCode = "200", description = "Obtenir la ligne de stockage d'un produit",
            content = @Content(schema = @Schema(implementation = LineStorageDTO.class)))
    LineStorageDTO getLineStorage(@PathVariable @Parameter(description = "Identifiant du produit stocké",required = true)
                                         String productId, @PathVariable @Parameter(description = "Identifiant du magasin de stockage",required = true)
                                         Long departmentStoreId);

    @DeleteMapping(value = "/line-storages/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'un ligne de stockage", description = "Cette méthode permet de supprimer une ligne de stockage d'un magasin via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ligne de stockage  supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Ligne de stockage non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant de la ligne de stockage", required = true)
                String id);
}
