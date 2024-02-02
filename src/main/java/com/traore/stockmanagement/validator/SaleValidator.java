package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.sale.SaleDTO;
import com.traore.stockmanagement.dto.sale.SaleOnlyDTO;
import com.traore.stockmanagement.enums.CommandStatus;

import java.util.ArrayList;
import java.util.List;

public class SaleValidator {
    public static List<String> validate(SaleDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner la date de la vente");
            errors.add("Veuillez renseigner le status de la vente");
            errors.add("Veuillez renseigner la vente");
            errors.add("Veuillez renseigner les lignes de la vente");
        }else {
            if (dto.getUser() == null){
                errors.add("Veuillez renseigner l'utilisateur");
            }else {
                if (dto.getUser().getId()==null){
                    errors.add("Veuillez renseigner l'identifiant de l'utilisateur qui ajoute la vente");
                }
            }

            if (dto.getStatus() == null){
                errors.add("Veuillez renseigner le status de la vente");
            }else{
                boolean isStatusExists= false;
                for (CommandStatus status : CommandStatus.values()){
                    if (status.equals(dto.getStatus())){
                        isStatusExists = true;
                        break;
                    }
                }
                if (!isStatusExists){
                    errors.add("Veuillez renseigner un status correct pour la vente");
                }
            }

            if (dto.getDate() == null){
                errors.add("Veuillez renseigner la date de la vente");
            }

            if (dto.getSaleLines() == null){
                errors.add("Une vente doit contenir au moins une ligne de vente");
            }



        }

        return errors;
    }
    public static List<String> updateValidation(SaleOnlyDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner l'identifiant de la vente");
            errors.add("Veuillez renseigner les propriétés à mettre à jour");
        }else {
            if (dto.getId() == null){
                errors.add("Veuillez renseigner l'identifiant de la vente");
            }else {
                if (dto.getId().isBlank()){
                    errors.add("Identifiant de la vente est incorrect");
                }
            }
            if (dto.getDate() == null && dto.getStatus() ==null){
                errors.add("Veuillez la ou les propriétés à modifier");
            }else {
                if (dto.getStatus()!=null){
                    boolean isStatusExists = false;
                    for (CommandStatus status : CommandStatus.values()){
                        if (status.equals(dto.getStatus())) {
                            isStatusExists = true;
                            break;
                        }
                    }
                    if (!isStatusExists){
                        errors.add("Veuillez renseigner un status valide pour la vente");
                    }
                }
            }

        }

        return errors;
    }
}
