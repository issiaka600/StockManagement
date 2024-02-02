package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.commandProvider.CommandProviderDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderOnlyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface CommandProviderAPI {

    final static String COMMAND_PROVIDER_API_ROOT= "/command-providers";

    @PostMapping(value = COMMAND_PROVIDER_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout de commande fournisseur", description = "Cette méthode permet d'ajouter un nouvelle commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Commande fournisseur ajoutée avec succès",
                    content = @Content(schema = @Schema(implementation = CommandProviderDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format de commande fournisseur incorrect"),
            @ApiResponse(responseCode = "500",description = "Erreur serveur, veuillez reéssayer ultérieurement")
    })
    CommandProviderDTO save(@RequestBody CommandProviderDTO dto);


    @PutMapping(value = COMMAND_PROVIDER_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier une commande fournisseur", description = "Cette méthode permet de modifier une commande founisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande fournisseur modifiée avec succès",
                    content = @Content(schema = @Schema(implementation = CommandProviderDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour modifier la commande fournisseur"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    CommandProviderDTO update(@RequestBody CommandProviderOnlyDTO dto);

    @DeleteMapping(value = COMMAND_PROVIDER_API_ROOT+"/{commandProviderId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'une commande fournisseur", description = "Cette méthode permet de supprimer une commande fournisseur via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande fournisseur supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Commande fournisseur non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur : Veuillez reessayer ultérieurement")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant de la command fournisseur", required = true)
                String commandProviderId);
}
