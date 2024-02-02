package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.commandProvider.CommandProviderDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderOnlyDTO;
import com.traore.stockmanagement.enums.CommandStatus;
import java.util.ArrayList;
import java.util.List;

public class CommandProviderValidator {

    public static List<String> validate(CommandProviderDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner la date de la commande");
            errors.add("Veuillez renseigner le status de la commande");
            errors.add("Veuillez renseigner la commande");
            errors.add("Veuillez renseigner les lignes de la commande");
        }else {
            if (dto.getProvider() == null){
                errors.add("Veuillez renseigner le fournisseur");
            }else {
                if (dto.getProvider().getId()==null){
                    errors.add("Veuillez renseigner l'identifiant du fournisseur");
                }else{
                    if (dto.getProvider().getId().isBlank()){
                        errors.add("Identifiant du fournisseur est invalide");
                    }
                }
            }

            if (dto.getUser() == null){
                errors.add("Veuillez renseigner l'utilisateur qui passe commande");
            }else {
                if (dto.getUser().getId()==null){
                    errors.add("Veuillez renseigner l'identifiant de l'utilisateur");
                }
            }


            if (dto.getStatus() == null){
                errors.add("Veuillez renseigner le status de commande fournisseur");
            }else{
                boolean isStatusExists= false;
                for (CommandStatus status : CommandStatus.values()){
                    if (status.equals(dto.getStatus())){
                        isStatusExists = true;
                        break;
                    }
                }
                if (!isStatusExists){
                    errors.add("Veuillez renseigner un status correct pour la commande fournisseur");
                }
            }

            if (dto.getDate() == null){
                errors.add("Veuillez renseigner la date de la commande fournisseur");
            }

            if (dto.getCommandLineProviders() == null){
                errors.add("Une commande doit contenir au moins une ligne de commande");
            }



        }

        return errors;
    }
    public static List<String> updateValidation(CommandProviderOnlyDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner l'identifiant de la commande");
            errors.add("Veuillez renseigner les propriétés à mettre à jour");
        }else {
            if (dto.getId() == null){
                errors.add("Veuillez renseigner l'identifiant de la commande");
            }else {
                if (dto.getId().isBlank()){
                    errors.add("Identifiant de la commande est incorrect");
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
                        errors.add("Veuillez renseigner un status valide pour la commande");
                    }
                }
            }

        }

        return errors;
    }
}
