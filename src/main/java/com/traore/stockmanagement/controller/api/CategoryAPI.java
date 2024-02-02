package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import com.traore.stockmanagement.dto.category.CategoryDetailsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface CategoryAPI {
    @PostMapping(value = "/categories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout de catégories", description = "Cette méthode permet d'ajouter une catégorie pour une entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie ajoutée avec succès",
                content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "400", description = "Catégorie invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    CategoryDTO save(@RequestBody CategoryDTO categoryDTO);

    @PutMapping(value = "/categories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier une catégorie", description = "Cette méthode permet de changer le nom d'une catégorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie modifiée avec succès",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour la modification d'une catégorie"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    CategoryDTO update(@RequestBody CategoryDTO categoryDTO);

    @GetMapping(value = "/categories/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir une catégorie avec ces produits", description = "Cette méthode permet d'obtenir une catégorie avec ses produits")
    @ApiResponse(responseCode = "200", description = "Catégory avec produits",
            content = @Content(schema = @Schema(implementation = CategoryDetailsDTO.class)))
    CategoryDetailsDTO getCategoryDetail(@PathVariable @Parameter(description = "Identifiant de la catégorie")Long categoryId);


    @DeleteMapping(value = "/categories/{categoryId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'une catégorie", description = "Cette méthode permet de supprimer une catégorie via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant de la catégorie") Long categoryId);
}
