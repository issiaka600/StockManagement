package com.traore.stockmanagement.controller.api;

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

public interface ProviderAPI {
    final static String PROVIDER_API_ROOT = "/providers";
    @PostMapping(value = PROVIDER_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout de fournisseur", description = "Cette méthode permet d'ajouter un nouveau fournisseur de l'entrepise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Fournisseur ajouté avec succès",
                    content = @Content(schema = @Schema(implementation = ProviderDTO.class))),
            @ApiResponse(responseCode = "400",description = "Fournisseur de stock incorrect"),
            @ApiResponse(responseCode = "500",description = "Erreur serveur, veuillez reéssayer ultérieurement")
    })
    ProviderDTO save(@RequestBody CreateProviderDTO dto);

    @PostMapping(value = PROVIDER_API_ROOT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajout de fournisseur avec image", description = "Cette méthode permet d'ajouter un nouveau fournisseur de l'entrepise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Fournisseur ajouté avec succès",
                    content = @Content(schema = @Schema(implementation = ProviderDTO.class))),
            @ApiResponse(responseCode = "400",description = "Fournisseur de stock incorrect"),
            @ApiResponse(responseCode = "500",description = "Erreur serveur, veuillez reéssayer ultérieurement")
    })
    ProviderDTO save(@RequestParam MultipartFile file,
                     @RequestParam String firstName,
                     @RequestParam String lastName,
                     @RequestParam String email,
                     @RequestParam String phone,
                     @RequestParam String address,
                     @RequestParam ClientStatus status,
                     @RequestParam Long enterpriseId);


    @PutMapping(value = PROVIDER_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier les informations d'un fournisseur", description = "Cette méthode permet de changer les informations d'un founisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objet fournisseur modifié avec succès",
                    content = @Content(schema = @Schema(implementation = CreateProviderDTO.class))),
            @ApiResponse(responseCode = "400",description = "Format invalid pour modifier les informations d'un fournisseur"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur ! Veuillez réessayer plutard.")
    })
    CreateProviderDTO update(@RequestBody CreateProviderDTO dto);


    @GetMapping(value = PROVIDER_API_ROOT +"/{providerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir les informations sur un fournisseur", description = "Cette methode permet de visualiser les informations sur un fournisseur grâce à son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informations d'un fournisseur",
                    content = @Content(schema = @Schema(implementation = ProviderDTO.class))),
            @ApiResponse(responseCode = "400", description = "Identifiant du fournisseur invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur du serveur !! Veuillez ressayer avec un identifiant correct")
    })
    ProviderDTO getProvider(@PathVariable @Parameter(description = "Idenfiant du fournisseur", required = true) String providerId);

    @GetMapping(value = PROVIDER_API_ROOT+"/enterprises/{enterpriseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir la liste des fournisseurs d'une entreprise", description = "Cette méthode permet d'obtenir la liste des fournisseurs d'une entreprise")
    @ApiResponse(responseCode = "200", description = "Liste des fournisseurs d'une entreprise",
            content = @Content(schema = @Schema(implementation = List.class)))
    List<ProviderDTO> getProviders(@PathVariable @Parameter(description = "Idenfiant de l'entrprise", required = true)
                                   Long enterpriseId);


    @DeleteMapping(value = PROVIDER_API_ROOT+"/{providerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'un fournisseur", description = "Cette méthode permet de supprimer un fournisseur via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fournisseur supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Fournisseur non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur : Veuillez reessayer ultérieurement")
    })
    void delete(@PathVariable @Parameter(description = "Idenfiant du fournisseur", required = true)
                String providerId);
}
