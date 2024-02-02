package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.saleLine.SaleLineDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleLineValidator {

    public static List<String> validate(SaleLineDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto==null){
            errors.add("Veuillez renseigner le prix untitaire");
            errors.add("Veuillez renseigner la quantité");
            errors.add("Veuillez renseigner le produit concerné");
            errors.add("Veuillez renseigner la command concernée");
        }else {
            if (dto.getSale()==null){
                errors.add("Veuillez renseigner la vente");
            }else {
                if (dto.getSale().getId()==null){
                    errors.add("Veuillez renseigner l'identifiant de la vente");
                }else {
                    if (dto.getSale().getId().isBlank()){
                        errors.add("Veuillez un identifiant valide pour la vente");
                    }
                }
            }

            if (dto.getProduct() == null){
                errors.add("Veuillez renseigner le produit");
            }else {
                if (dto.getProduct().getId() == null){
                    errors.add("Veuillez renseigner l'identifiant du produit");
                }else {
                    if (dto.getProduct().getId().isBlank()){
                        errors.add("Veuillez renseigner un identifiant valide pour la vente");
                    }
                }
            }

            if (dto.getQuantity() == null || dto.getQuantity().compareTo(BigDecimal.ZERO)<=0){
                errors.add("Veuillez renseigner la quantité du produit");
            }
            if (dto.getUnitPrice()==null || dto.getUnitPrice().compareTo(BigDecimal.ZERO)<=0){
                errors.add("Veuillez renseigner le prix unitaire");
            }


        }

        return errors;
    }

    public static List<String> updateValidation(SaleLineDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto==null){
            errors.add("Veuillez renseigner l'identifiant de la ligne de la vente");
            errors.add("Veuillez renseigner les propriétés à modifier dans ligne de la vente");
        }else {
            if (dto.getId() == null){
                errors.add("Veuillez renseigner l'identifiant de la ligne de la vente");
            }else {
                if (dto.getId().isBlank()){
                    errors.add("Veuillez renseigner un identifiant correct pour la ligne de la vente");
                }
            }

            if (dto.getQuantity()!=null && dto.getQuantity().compareTo(BigDecimal.ZERO)<=0){
                errors.add("La quantité doit être supérieure à zéro");
            }

            if (dto.getUnitPrice()!=null && dto.getUnitPrice().compareTo(BigDecimal.ZERO)<=0){
                errors.add("Le prix unitaire doit être supérieur à zéro");
            }
        }

        return errors;
    }
}
