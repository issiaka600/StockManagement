package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.CommandClient.CommandClientDTO;
import com.traore.stockmanagement.dto.CommandClient.CommandClientOnlyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface CommandClientAPI {
    final static String COMMAND_CLIENT_API_ROOT = "/command-clients";

    @PostMapping(value = COMMAND_CLIENT_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout de commande client", description = "Cette méthode permet d'ajouter un nouvelle commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Commande client ajoutée avec succès",
                    content = @Content(schema = @Schema(implementation = CommandClientDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format de commande client incorrect"),
            @ApiResponse(responseCode = "500",description = "Erreur serveur, veuillez reéssayer ultérieurement")
    })
    CommandClientDTO save(@RequestBody CommandClientDTO dto);



    @PutMapping(value = COMMAND_CLIENT_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier une commande client", description = "Cette méthode permet de modifier une commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client modifiée avec succès",
                    content = @Content(schema = @Schema(implementation = CommandClientDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour modifier la commande client"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    CommandClientDTO update(@RequestBody CommandClientOnlyDTO dto);



    @DeleteMapping(value = COMMAND_CLIENT_API_ROOT+"/{commandClientId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'une commande client", description = "Cette méthode permet de supprimer une commande client via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Commande client non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur : Veuillez reessayer ultérieurement")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant de la commande client", required = true)
                String commandClientId);
}
