package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.stockMovement.StockMovementDTO;
import com.traore.stockmanagement.enums.MovementType;
import com.traore.stockmanagement.enums.RoleName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StockMovementValidator {

    public static List<String> validate(StockMovementDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner la date du mouvement de stock");
            errors.add("Veuillez renseigner la quantité du mouvement de stock");
            errors.add("Veuillez renseigner le type du mouvement de stock");
            errors.add("Veuillez renseigner le magasin où a lieu le mouvement de stock");
            errors.add("Veuillez renseigner le produit concerné par le mouvement de stock");
        }
        else {
            if (dto.getDate()==null){
                errors.add("Veuillez renseigner la date du mouvement de stock");
            }

            if (dto.getType()==null){
                errors.add("Veuillez renseigner le type du mouvement");
            }else {
                MovementType[] movementTypes = MovementType.values();
                boolean movementTypeExists = false;
                for (MovementType movementType : movementTypes) {
                    if (movementType.equals(dto.getType())) {
                        movementTypeExists = true;
                        break;
                    }
                }
                if(!movementTypeExists){
                    errors.add("Veuillez renseigner inexistant");
                }

                if(dto.getType().equals(MovementType.PROVIDER) && dto.getUnitPrice()==null){
                    errors.add("Veuillez renseigner le prix unitaire du produit à stocker");
                }
            }

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
}
