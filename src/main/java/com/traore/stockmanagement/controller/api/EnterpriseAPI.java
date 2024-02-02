package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.enterprise.*;
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

public interface EnterpriseAPI {
   /* @PostMapping(value = "/enterprises", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une entreprise", description = "Cette méthode permet d'enregistrer une entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet entreprise créé/modifié",
                    content = @Content(schema = @Schema(implementation = EnterpriseWithAttachmentDTO.class))),
            @ApiResponse(responseCode = "400", description = "L'objet entreprise n'est pas valide")
    })
    EnterpriseWithAttachmentDTO save(@RequestBody CreateEnterpriseDTO dto);

    */
   @PostMapping(value = "/enterprises", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   @Operation(summary = "Enregistrer une entreprise", description = "Cette méthode permet d'enregistrer une entreprise")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "L'objet entreprise créé/modifié",
                   content = @Content(schema = @Schema(implementation = EnterpriseWithAttachmentDTO.class))),
           @ApiResponse(responseCode = "400", description = "L'objet entreprise n'est pas valide")
   })
   EnterpriseWithAttachmentDTO save(
           @RequestParam("file") MultipartFile file,
           @RequestParam("name") String name
   );


    @PutMapping(value = "/enterprises", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour une entreprise", description = "Cette méthode permet de mettre à jour une entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet entreprise mis à jour",
                    content = @Content(schema = @Schema(implementation = UpdateEnterpriseDTO.class))),
            @ApiResponse(responseCode = "400", description = "L'objet entreprise n'est pas valide")
    })
    UpdateEnterpriseDTO update(@RequestBody UpdateEnterpriseDTO dto);

    @GetMapping(value = "/enterprises", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir la liste des entreprises", description = "Cette méthode permet d'obtenir la liste des entreprises")
    @ApiResponse(responseCode = "200", description = "Liste des entreprises",
            content = @Content(schema = @Schema(implementation = List.class, example = "[enterprise1, enterprise2]")))
    List<EnterpriseWithAttachmentDTO> getEnterprisesOnly();

    @GetMapping(value = "/enterprises/{enterpriseId}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir une entreprise avec ses utilisateurs", description = "Cette méthode permet d'obtenir une entreprise avec ses utilisateurs")
    @ApiResponse(responseCode = "200", description = "Entreprise avec utilisateurs",
            content = @Content(schema = @Schema(implementation = EnterpriseWithUsersDTO.class)))
    EnterpriseWithUsersDTO getEnterpriseAndUsers(
            @PathVariable @Parameter(description = "ID de l'entreprise", required = true) Long enterpriseId);

    @GetMapping(value = "/enterprises/{enterpriseId}/department-stores", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir une entreprise avec ses magasins", description = "Cette méthode permet d'obtenir une entreprise avec ses magasins")
    @ApiResponse(responseCode = "200", description = "Entreprise avec magasins",
            content = @Content(schema = @Schema(implementation = EnterpriseWithDepartmentStoreDTO.class)))
    EnterpriseWithDepartmentStoreDTO getEnterpriseAndDepartmentStores(
            @PathVariable @Parameter(description = "ID de l'entreprise", required = true) Long enterpriseId);

    @GetMapping(value = "/enterprises/{enterpriseId}/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir une entreprise avec ses catégories", description = "Cette méthode permet d'obtenir une entreprise avec ses catégories")
    @ApiResponse(responseCode = "200", description = "Entreprise avec catégories",
            content = @Content(schema = @Schema(implementation = EnterpriseWithCategories.class)))
    EnterpriseWithCategories getEnterpriseWithCategories(
            @PathVariable @Parameter(description = "ID de l'entreprise", required = true) Long enterpriseId);

    @GetMapping(value = "/enterprises/{enterpriseId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir une entreprise avec ses produits", description = "Cette méthode permet d'obtenir une entreprise avec ses produits")
    @ApiResponse(responseCode = "200", description = "Entreprise avec produits",
            content = @Content(schema = @Schema(implementation = EnterpriseWithProductsDTO.class)))
    EnterpriseWithProductsDTO getEnterpriseWithProducts(
            @PathVariable @Parameter(description = "ID de l'entreprise", required = true) Long enterpriseId);
}
