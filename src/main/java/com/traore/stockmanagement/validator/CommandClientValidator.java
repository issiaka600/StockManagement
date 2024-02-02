package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.CommandClient.CommandClientDTO;
import com.traore.stockmanagement.dto.CommandClient.CommandClientOnlyDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderOnlyDTO;
import com.traore.stockmanagement.enums.CommandStatus;

import java.util.ArrayList;
import java.util.List;

public class CommandClientValidator {
    public static List<String> validate(CommandClientDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner la date de la commande");
            errors.add("Veuillez renseigner le status de la commande");
            errors.add("Veuillez renseigner le client");
            errors.add("Veuillez renseigner l'utilisateur qui instrue la commande client");
            errors.add("Veuillez renseigner les lignes de la commande");
        }else {
            if (dto.getClient() == null){
                errors.add("Veuillez renseigner le client");
            }else {
                if (dto.getClient().getId()==null){
                    errors.add("Veuillez renseigner l'identifiant du client");
                }else{
                    if (dto.getClient().getId().isBlank()){
                        errors.add("Identifiant du client est invalide");
                    }
                }
            }
            if (dto.getUser() == null){
                errors.add("Veuillez renseigner l'utilisateur");
            }else {
                if (dto.getUser().getId()==null){
                    errors.add("Veuillez renseigner l'identifiant de l'utilisateur");
                }
            }

            if (dto.getStatus() == null){
                errors.add("Veuillez renseigner le status de commande client");
            }else{
                boolean isStatusExists= false;
                for (CommandStatus status : CommandStatus.values()){
                    if (status.equals(dto.getStatus())){
                        isStatusExists = true;
                        break;
                    }
                }
                if (!isStatusExists){
                    errors.add("Veuillez renseigner un status correct pour la commande client");
                }
            }

            if (dto.getDate() == null){
                errors.add("Veuillez renseigner la date de la commande client");
            }

            if (dto.getCommandLineClients() == null){
                errors.add("Une commande doit contenir au moins une ligne de commande");
            }



        }

        return errors;
    }
    public static List<String> updateValidation(CommandClientOnlyDTO dto){
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
