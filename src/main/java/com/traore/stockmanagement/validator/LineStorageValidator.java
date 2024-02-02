package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.lineStorage.LineStorageDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LineStorageValidator {

    public static List<String> validate(LineStorageDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner la quantité");
            errors.add("Veuillez renseigner le magasin de stockage");
            errors.add("Veuillez renseigner le produit");
        }else {
            if (dto.getQuantity()!=null && dto.getQuantity().compareTo(BigDecimal.ZERO)<=0){
                errors.add("Veuillez renseigner une quantité correcte");
            }
            if (dto.getProduct()==null){
                errors.add("Veuillez renseigner le produit");
            }else{
                if (dto.getProduct().getId()==null){
                    errors.add("Veuillez renseigner l'identifiant du produit");
                }
            }

            if (dto.getDepartmentStore()==null){
                errors.add("Veuillez renseigner le magasin");
            }else{
                if (dto.getDepartmentStore().getId()==null){
                    errors.add("Veuillez renseigner l'identifiant du magasin");
                }
            }
        }

        return errors;
    }

    public static List<String> updateValidation(String id, BigDecimal quantity){
        List<String> errors = new ArrayList<>();
        if (id==null){
            errors.add("Veuillez renseigner l'identifiant de la ligne de stockage");
        }else {
            if (id.isBlank()){
                errors.add("Veuillez renseigner un identifiant de ligne de stockage correct");
            }
        }
        if (quantity == null){
            errors.add("Veuillez renseigner la nouvelle quantité");
        }else {
            if (quantity.compareTo(BigDecimal.ZERO)<=0){
                errors.add("Veuillez renseigner une quantité correcte");
            }
        }

        return errors;
    }

}
