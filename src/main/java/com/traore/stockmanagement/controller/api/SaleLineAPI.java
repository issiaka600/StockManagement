package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.saleLine.SaleLineDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface SaleLineAPI {
    final static String SALE_LINE_API_ROOT = "/sale-lines";

    @PostMapping(value = SALE_LINE_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout d'une ligne de vente", description = "Cette méthode permet d'ajouter un nouvelle ligne de vente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Ligne de vente ajoutée avec succès",
                    content = @Content(schema = @Schema(implementation = SaleLineDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format de ligne de vente incorrect"),
            @ApiResponse(responseCode = "500",description = "Erreur serveur, veuillez reéssayer ultérieurement")
    })
    SaleLineDTO save(@RequestBody SaleLineDTO dto);

    @PutMapping(value = SALE_LINE_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier une ligne vente", description = "Cette méthode permet de modifier une ligne de vente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ligne de vente modifiée avec succès",
                    content = @Content(schema = @Schema(implementation = SaleLineDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour modifier une ligne d'une vente"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    SaleLineDTO update(@RequestBody SaleLineDTO dto);




    @DeleteMapping(value = SALE_LINE_API_ROOT +"/{saleLineId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'une ligne de vente", description = "Cette méthode permet de supprimer une ligne d'une vente via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ligne de vente supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Ligne de vente non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur : Veuillez reessayer ultérieurement")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant de la ligne de vente", required = true)
                String saleLineId);
}
