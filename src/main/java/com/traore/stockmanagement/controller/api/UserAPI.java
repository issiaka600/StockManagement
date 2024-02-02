package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.user.CreateUserDTO;
import com.traore.stockmanagement.dto.user.UpdateUserPasswordDTO;
import com.traore.stockmanagement.dto.user.UserDTO;
import com.traore.stockmanagement.enums.RoleName;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


public interface UserAPI {
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout d'utilisateur", description = "Cette méthode permet d'ajouter un nouvel utilisateur à une entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Utiilisateur ajouté avec succès",
                content = @Content(schema = @Schema(implementation = CreateUserDTO.class))),
            @ApiResponse(responseCode = "400",description = "Utilisateur incorrect")
    })
    CreateUserDTO save(@RequestBody CreateUserDTO userDTO);


    @PostMapping(value = "/users", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout d'utilisateur avec image", description = "Cette méthode permet d'ajouter un nouvel utilisateur à une entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Utiilisateur ajouté avec succès",
                content = @Content(schema = @Schema(implementation = CreateUserDTO.class))),
            @ApiResponse(responseCode = "400",description = "Utilisateur incorrect")
    })
    CreateUserDTO save(@RequestParam(required = false) MultipartFile file,
                       @RequestParam String firstName,
                       @RequestParam String lastName,
                       @RequestParam String email,
                       @RequestParam String password,
                       @RequestParam String phone,
                       @RequestParam(required = false) String address,
                       @RequestParam Long enterpriseId,
                       @RequestParam RoleName role);


    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir les informations sur un utilisateur", description = "Cette methode permet de visualiser les informations sur un utilisateur grâce à son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informations de l'utilisateur",
                content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Identifiant d'utilisateur invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur !! \n Veuillez ressayer avec un identifiant correct")
    })
    UserDTO getUser(
            @PathVariable @Parameter(description = "Identifiant de l'utilisateur", required = true)
            Long userId);

    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier les mots de passe utilisateur", description = "Cette méthode permet de changer les mots de passe d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mots de passe modifiés avec succès",
                content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour la modification des mots de passe utlisateur"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    UserDTO updateUserPassword(@RequestBody UpdateUserPasswordDTO dto);

    @DeleteMapping(value = "/users/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'un utilisateur", description = "Cette méthode permet de supprimer un utilisateur via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "utilisateur supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur : Veuillez reessayer ultérieurement")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant d'utilisateur",required = true)
                Long userId);
}
