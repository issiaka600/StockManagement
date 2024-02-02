package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.CommandLineClient.CommandLineClientDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface CommandLineClientAPI {
    final static String COMMAND_LINE_CLIENT_API_ROOT = "/command-line-clients";

    @PostMapping(value = COMMAND_LINE_CLIENT_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout d'une ligne de commande client", description = "Cette méthode permet d'ajouter un nouvelle ligne de commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Ligne de commande client ajoutée avec succès",
                    content = @Content(schema = @Schema(implementation = CommandLineClientDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format de ligne de commande client incorrect"),
            @ApiResponse(responseCode = "500",description = "Erreur serveur, veuillez reéssayer ultérieurement")
    })
    CommandLineClientDTO save(@RequestBody CommandLineClientDTO dto);

    @PutMapping(value = COMMAND_LINE_CLIENT_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier une ligne commande client", description = "Cette méthode permet de modifier une ligne de commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ligne de commande client modifiée avec succès",
                    content = @Content(schema = @Schema(implementation = CommandLineClientDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour modifier une ligne de la commande client"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    CommandLineClientDTO update(@RequestBody CommandLineClientDTO dto);




    @DeleteMapping(value = COMMAND_LINE_CLIENT_API_ROOT +"/{commandLineClientId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'une ligne de commande client", description = "Cette méthode permet de supprimer une ligne de commande client via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ligne de commande client supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Ligne de commande client non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur : Veuillez reessayer ultérieurement")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant de la ligne de commande client", required = true)
                String commandLineClientId);
}
