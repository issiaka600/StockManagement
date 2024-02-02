package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.client.ClientDTO;
import com.traore.stockmanagement.dto.client.CreateClientDTO;
import com.traore.stockmanagement.dto.provider.CreateProviderDTO;
import com.traore.stockmanagement.dto.provider.ProviderDTO;
import com.traore.stockmanagement.enums.ClientStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientAPI {

    final static String ClIENT_API_ROOT = "/clients";
    @PostMapping(value = ClIENT_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout de client", description = "Cette méthode permet d'ajouter un nouveau client de l'entrepise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Client ajouté avec succès",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "400",description = "Client format incorrect"),
            @ApiResponse(responseCode = "500",description = "Erreur serveur, veuillez reéssayer ultérieurement")
    })
    ClientDTO save(@RequestBody CreateClientDTO dto);

    @PostMapping(value = ClIENT_API_ROOT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout de client avec image", description = "Cette méthode permet d'ajouter un nouveau client de l'entrepise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Client ajouté avec succès",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "400",description = "Client format incorrect"),
            @ApiResponse(responseCode = "500",description = "Erreur serveur, veuillez reéssayer ultérieurement")
    })
    ClientDTO save(@RequestParam MultipartFile file,
                   @RequestParam String firstName,
                   @RequestParam String lastName,
                   @RequestParam String email,
                   @RequestParam String phone,
                   @RequestParam String address,
                   @RequestParam ClientStatus status,
                   @RequestParam Long enterpriseId);

    @PutMapping(value = ClIENT_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier les informations d'un client", description = "Cette méthode permet de changer les informations d'un client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objet client modifié avec succès",
                    content = @Content(schema = @Schema(implementation = CreateClientDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour modifier les informations d'un client"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    CreateClientDTO update(@RequestBody CreateClientDTO dto);


    @GetMapping(value = ClIENT_API_ROOT +"/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir les informations sur un client", description = "Cette methode permet de visualiser les informations sur un client grâce à son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informations d'un client",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "400", description = "Identifiant du client invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur !! Veuillez ressayer avec un identifiant correct")
    })
    ClientDTO getClient(@PathVariable @Parameter(description = "Identifiant du client", required = true)
                        String clientId);


    @GetMapping(value = ClIENT_API_ROOT+"/enterprises/{enterpriseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir la liste des clients d'une entreprise", description = "Cette méthode permet d'obtenir la liste des clients d'une entreprise")
    @ApiResponse(responseCode = "200", description = "Liste des clients d'une entreprise",
            content = @Content(schema = @Schema(implementation = List.class)))
    List<ClientDTO> getClients(@PathVariable @Parameter(description = "Identifiant de l'entreprise", required = true)
                               Long enterpriseId);


    @DeleteMapping(value = ClIENT_API_ROOT+"/{clientId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'un client", description = "Cette méthode permet de supprimer un client via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Client non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur : Veuillez reessayer ultérieurement")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant du client", required = true)
                String clientId);
}
