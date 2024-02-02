package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.roles.RolesDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface RolesAPI {
    final static String ROLES_API_ROOT = "/roles";
    @PostMapping(value = ROLES_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un rôle", description = "Cette méthode permet d'enrégistrer un rôle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet rôle créé",
                    content = @Content(schema = @Schema(implementation = RolesDTO.class))),
            @ApiResponse(responseCode = "400", description = "L'objet rôle n'est pas valide")
    })
    RolesDTO save(@RequestBody RolesDTO dto);

    @PutMapping(value = ROLES_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mise à jour d'un rôle", description = "Cette méthode permet de mettre à jour un rôle d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle modifié",
                content = @Content(schema =@Schema(implementation = RolesDTO.class) )),
            @ApiResponse(responseCode = "400", description = "Mise à jour invalide")
    })
    RolesDTO update(@RequestBody RolesDTO rolesDTO);

    @DeleteMapping(value = ROLES_API_ROOT+"/{roleId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'un rôle d'utilisateur", description = "Cette méthode permet de supprimer un rôle via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    void delete(
            @PathVariable
            @Parameter(description = "ID du rôle à supprimer", required = true)
            Integer roleId);
}
