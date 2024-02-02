package com.traore.stockmanagement.controller.api;

import com.traore.stockmanagement.dto.product.CreateProductDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;
import com.traore.stockmanagement.dto.product.ProductWithLineStorageDTO;
import com.traore.stockmanagement.dto.product.ProductWithStockMovementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ProductAPI {
    final static String PRODUCT_API_ROOT = "/products";
    @PostMapping(value = PRODUCT_API_ROOT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un produit avec image", description = "Cette méthode permet d'enregistrer un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet produit créé",
                    content = @Content(schema = @Schema(implementation = CreateProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "L'objet produit n'est pas valide"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur, veuillez réessayer ultérieurement")
    })
    CreateProductDTO save(@RequestParam MultipartFile file,
                          @RequestParam  String name,
                          @RequestParam  BigDecimal unitPrice,
                          @RequestParam  BigDecimal quantityMin,
                          @RequestParam  Long categoryId,
                          @RequestParam  Long enterpriseId);
    @PostMapping(value = PRODUCT_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un produit", description = "Cette méthode permet d'enregistrer un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet produit créé",
                    content = @Content(schema = @Schema(implementation = CreateProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "L'objet produit n'est pas valide"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur, veuillez réessayer ultérieurement")
    })
    CreateProductDTO save(@RequestBody CreateProductDTO dto);

    @PutMapping(value = PRODUCT_API_ROOT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour un produit", description = "Cette méthode permet de mettre à jour les propriétés d'un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet produit mis à jour",
                    content = @Content(schema = @Schema(implementation = CreateProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "L'objet produit n'est pas valide"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur, veuillez réessayer ultérieurement")
    })
    CreateProductDTO update(@RequestBody ProductDTO dto);

    @GetMapping(value = PRODUCT_API_ROOT + "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir un produit avec sa catégorie et son entreprise", description = "Cette méthode permet d'obtenir un produit avec sa catégorie et son entreprise")
    @ApiResponse(responseCode = "200", description = "Produit avec catégorie et entreprise",
            content = @Content(schema = @Schema(implementation = CreateProductDTO.class)))

    CreateProductDTO getProduct(@PathVariable @Parameter(description = "Identifiant du produit", required = true)
                                String productId);

    @GetMapping(value = PRODUCT_API_ROOT + "/{productId}/line-storages", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir un produit avec sa catégorie et son entreprise", description = "Cette méthode permet d'obtenir un produit avec sa catégorie et son entreprise")
    @ApiResponse(responseCode = "200", description = "Produit avec catégorie et entreprise",
            content = @Content(schema = @Schema(implementation = ProductWithLineStorageDTO.class)))
    ProductWithLineStorageDTO getProductWithLineStorages(@PathVariable @Parameter(description = "Identifiant du produit", required = true)
                                                         String productId);


    @GetMapping(value = PRODUCT_API_ROOT + "/{productId}/stock-movements", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir un produit avec sa catégorie et son entreprise", description = "Cette méthode permet d'obtenir un produit avec sa catégorie et son entreprise")
    @ApiResponse(responseCode = "200", description = "Produit avec catégorie et entreprise",
            content = @Content(schema = @Schema(implementation = ProductWithStockMovementDTO.class)))
    ProductWithStockMovementDTO getProductWithStockMovements(@PathVariable @Parameter(description = "Identifiant du produit", required = true)
                                                             String productId);

    @GetMapping(value = PRODUCT_API_ROOT +"/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir la liste des produits par catégorie", description = "Cette méthode permet d'obtenir la liste des produits par catégorie")
    @ApiResponse(responseCode = "200", description = "Liste des produits par catégorie",
            content = @Content(schema = @Schema(implementation = List.class)))

    List<ProductDTO> getProducts(@PathVariable @Parameter(description = "Identifiant de la catégorie", required = true)
                                 Long categoryId);


    @DeleteMapping(value = PRODUCT_API_ROOT +"/{productId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'un produit", description = "Cette méthode permet de supprimer un produit via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produit supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé"),
            @ApiResponse(responseCode = "500", description = "Produit interne du serveur")
    })
    void delete(@PathVariable @Parameter(description = "Identifiant de la catégorie", required = true)
                String productId);
}
