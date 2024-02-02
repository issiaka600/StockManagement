package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.commandLineProvider.CommandLineProviderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
public interface CommandLineProviderAPI {
    final static String COMMAND_LINE_PROVIDER_API_ROOT = "/command-line-providers";

    @PostMapping(value = COMMAND_LINE_PROVIDER_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout d'une ligne de commande fournisseur", description = "Cette méthode permet d'ajouter un nouvelle ligne de commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Ligne de commande fournisseur ajoutée avec succès",
                    content = @Content(schema = @Schema(implementation = CommandLineProviderDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format de ligne de commande fournisseur incorrect"),
            @ApiResponse(responseCode = "500",description = "Erreur serveur, veuillez reéssayer ultérieurement")
    })
    CommandLineProviderDTO save(@RequestBody CommandLineProviderDTO dto);

    @PutMapping(value = COMMAND_LINE_PROVIDER_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier une ligne commande fournisseur", description = "Cette méthode permet de modifier une ligne de commande founisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ligne de commande fournisseur modifiée avec succès",
                    content = @Content(schema = @Schema(implementation = CommandLineProviderDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour modifier la commande fournisseur"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    CommandLineProviderDTO update(@RequestBody CommandLineProviderDTO dto);




    @DeleteMapping(value = COMMAND_LINE_PROVIDER_API_ROOT+"/{commandLineProviderId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'une ligne de commande fournisseur", description = "Cette méthode permet de supprimer une ligne de commande fournisseur via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ligne de commande fournisseur supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Ligne de commande fournisseur non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur : Veuillez reessayer ultérieurement")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant de la commande fournisseur", required = true)
                String commandLineProviderId);
}
