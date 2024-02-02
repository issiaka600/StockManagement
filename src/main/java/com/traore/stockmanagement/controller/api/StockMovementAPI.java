package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.stockMovement.StockMovementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface StockMovementAPI {
    @PostMapping(value = "/stock-movements", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout de mouvement de stock", description = "Cette méthode permet d'ajouter un mouvement de stok tout en mettant à jour la ligne de stockage du produit dans ce magasin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Mouvement de stock ajouté avec succès",
                    content = @Content(schema = @Schema(implementation = StockMovementDTO.class))),
            @ApiResponse(responseCode = "400",description = "Mouvement de stock incorrect"),
            @ApiResponse(responseCode = "500",description = "Erreur serveur, veuillez reéssayer ultérieurement")
    })
    StockMovementDTO save(@RequestBody StockMovementDTO dto);

    @DeleteMapping(value = "/stock-movements/{stockMovementId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'un mouvement de stock", description = "Cette méthode permet de supprimer un mouvement de stock via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mouvement de stock supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Mouvement de stock non trouvé"),
            @ApiResponse(responseCode = "500", description = "Utilisateur interne du serveur")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant du mouvement de stock", required = true)
                String stockMovementId);
}
