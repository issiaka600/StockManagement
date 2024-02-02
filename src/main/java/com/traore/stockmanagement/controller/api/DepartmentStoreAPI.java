package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreWithLineStoragesDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreWithStockMovementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface DepartmentStoreAPI {
    @PostMapping(value = "/department-stores", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout de magasin de stocks", description = "Cette méthode permet d'ajouter un magasin de stocks pour une entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Magasin de stocks ajouté avec succès",
                    content = @Content(schema = @Schema(implementation = DepartmentStoreDTO.class))),
            @ApiResponse(responseCode = "400", description = "DepartmentStoreDTO invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    DepartmentStoreDTO save(@RequestBody DepartmentStoreDTO dto);

    @PutMapping(value = "/department-stores", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier un magasin de stocks", description = "Cette méthode permet de changer le nom d'un magasin de stocks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nom de magasin modifié avec succès",
                    content = @Content(schema = @Schema(implementation = DepartmentStoreDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour la modification du nom de magasin"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    DepartmentStoreDTO update(@RequestBody DepartmentStoreDTO dto);

    @GetMapping(value = "/department-stores/{departmentStoreId}/line-storages", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Magasin avec ses stocks", description = "Cette méthode permet d'obtenir un magasin avec ses stocks")
    @ApiResponse(responseCode = "200", description = "Magasin avec ses stocks",
            content = @Content(schema = @Schema(implementation = DepartmentStoreWithLineStoragesDTO.class)))
    DepartmentStoreWithLineStoragesDTO getDepartmentStoreWithLineStorages(
            @PathVariable @Parameter(description = "Identifiant du magasin", required = true)
            Long departmentStoreId);

    @GetMapping(value = "/department-stores/{departmentStoreId}/stock-movements", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Magasin avec ses mouvements de stocks", description = "Cette méthode permet d'obtenir un magasin avec ses mouvements de stocks")
    @ApiResponse(responseCode = "200", description = "Magasin avec ses mouvements de stockss",
            content = @Content(schema = @Schema(implementation = DepartmentStoreWithStockMovementDTO.class)))
    DepartmentStoreWithStockMovementDTO getDepartmentStoreWithStockMovements(
            @PathVariable @Parameter(description = "Identifiant du magasin", required = true)
            Long departmentStoreId);

    @DeleteMapping(value = "/department-stores/{departmentStoreId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression de magasin de stocks", description = "Cette méthode permet de supprimer un magasin de stocks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Magasin supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Magasin non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant du magasin", required = true)
                Long departmentStoreId);
}
