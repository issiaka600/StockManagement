package com.traore.stockmanagement.validator;
;
import com.traore.stockmanagement.dto.product.CreateProductDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator {

    public static List<String> validate(CreateProductDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner l'identifiant du produit");
            errors.add("Veuillez renseigner le prix unitaire du produit");
            errors.add("Veuillez renseigner la photo du produit");
            errors.add("Veuillez renseigner la quantité minimale du produit");
            errors.add("Veuillez renseigner la catégorie du produit");
        }else{
            if (dto.getName().isEmpty() || dto.getName().isBlank()){
                errors.add("Veuillez renseigner un nom correct pour le produit");
            }
          /*  if( dto.getPhoto().isEmpty() || dto.getPhoto().isBlank()){
                errors.add("Veuillez renseigner la photo du produit");
            }

           */
            if (dto.getQuantityMin()== null ||dto.getQuantityMin().compareTo(BigDecimal.ZERO)<=0){
                errors.add("Veuillez renseigner la quantité minimale du produit");
            }
            if (dto.getUnitPrice() == null || dto.getUnitPrice().compareTo(BigDecimal.ZERO)<=0){
                errors.add("Veuillez renseigner le prix unitaire du produit");
            }
            if (dto.getCategory() == null){
                errors.add("Veuillez renseigner la catégorie du produit");
            }else{
                if (dto.getCategory().getId() == null){
                    errors.add("Veuillez renseigner l'identifiant de catégorie");
                }
            }
            if (dto.getEnterprise() == null){
                errors.add("Veuillez renseigner l'entreprise");
            }else {
                if (dto.getEnterprise().getId() == null){
                    errors.add("Veuillez renseigner l'identifiant de l'entreprise");
                }
            }
        }

        return errors;
    }

    public static List<String> updateValidation(ProductDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner le nom du produit");
            errors.add("Veuillez renseigner l'entreprise");
            errors.add("Veuillez renseigner les propriétés à modifier");
        }else{
            if (dto.getId() == null){
                errors.add("Veuillez renseigner l'identifiant du produit");
            }
            if (!dto.getName().isEmpty() && dto.getName().isBlank()){
                errors.add("Veuillez renseigner un nom correct pour le produit");
            }
            if(!dto.getPhoto().isEmpty() && dto.getPhoto().isBlank()){
                errors.add("Veuillez renseigner la photo du produit");
            }
            if (dto.getQuantityMin()!= null && dto.getQuantityMin().compareTo(BigDecimal.ZERO)<0){
                errors.add("Veuillez renseigner la quantité minimale du produit");
            }
            if (dto.getUnitPrice() != null && dto.getUnitPrice().compareTo(BigDecimal.ZERO)<0){
                errors.add("Veuillez renseigner le prix unitaire du produit");
            }
            if (dto.getCategory() != null){
                if (dto.getCategory().getId() == null){
                    errors.add("Veuillez renseigner l'identifiant de catégorie");
                }
            }
        }

        return errors;
    }
}
