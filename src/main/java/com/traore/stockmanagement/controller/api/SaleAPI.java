package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.sale.SaleDTO;
import com.traore.stockmanagement.dto.sale.SaleOnlyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface SaleAPI {
    final static String SALE_API_ROOT = "/sales";

    @PostMapping(value = SALE_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une vente", description = "Cette méthode permet d'enregistrer une vente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vente créée avec succès",
                    content = @Content(schema = @Schema(implementation = SaleDTO.class))),
            @ApiResponse(responseCode = "400", description = "L'objet vente n'est pas valide"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur, veuillez ressayer ultérieurement")
    })
    SaleDTO save(@RequestBody SaleDTO dto);


    @PutMapping(value = SALE_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour une vente", description = "Cette méthode permet de mettre à jour une vente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet vente mis à jour",
                    content = @Content(schema = @Schema(implementation = SaleDTO.class))),
            @ApiResponse(responseCode = "400", description = "L'objet vente n'est pas valide"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur, veuillez ressayer ultérieurement")
    })
    SaleDTO update(@RequestBody SaleOnlyDTO dto);

    @GetMapping(value = SALE_API_ROOT+"/{saleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir les informations sur une vente", description = "Cette methode permet de visualiser les informations sur une vente grâce à son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informations de la vente",
                    content = @Content(schema = @Schema(implementation = SaleDTO.class))),
            @ApiResponse(responseCode = "400", description = "Identifiant de la vente invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur !! \n Veuillez ressayer avec un identifiant correct")
    })
    SaleDTO getSale(@PathVariable @Parameter(description = "Identifiant de la vente", required = true)
                    String saleId);

    @GetMapping(value = SALE_API_ROOT+"/{enterpriseId}/enterprises", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir la liste des ventes d'une entreprise", description = "Cette méthode permet d'obtenir la liste des ventes d'une entreprise")
    @ApiResponse(responseCode = "200", description = "Liste des ventes",
            content = @Content(schema = @Schema(implementation = List.class)))

    List<SaleDTO> getSales(@PathVariable @Parameter(description = "Identifiant de l'entreprise", required = true)
                           Long enterpriseId);

    @DeleteMapping(value = SALE_API_ROOT+"/{saleId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'une vente", description = "Cette méthode permet de supprimer un vente via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vente supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Vente non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant de la vente", required = true)
                String saleId);
}
