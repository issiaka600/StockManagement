package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.client.CreateClientDTO;
import com.traore.stockmanagement.enums.ClientStatus;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    private static final String emailRegex = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$";
    private static final String phoneRegex = "^[0-9]\\d{7}$";

    public static List<String> validate(CreateClientDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner le prénom du client");
            errors.add("Veuillez renseigner le nom du client");
            errors.add("Veuillez renseigner l'email du client");
            errors.add("Veuillez renseigner le numéro de téléphone du client");
            errors.add("Veuillez renseigner la photo du client");
            errors.add("Veuillez renseigner l'adresse du client");
            errors.add("Veuillez renseigner l'entreprise");
        }else {
            if (dto.getEnterprise() == null){
                errors.add("Veuillez renseigner l'entrpise");
            }else{
                if (dto.getEnterprise().getId() == null){
                    errors.add("Veuillez renseigner l'identifiant de l'entreprise");
                }
            }

            if(dto.getFirstName()==null){
                errors.add("Veuillez renseigner le prénom du client");
            }else {
                if (dto.getFirstName().isBlank()){
                    errors.add("Le prénom ne peut pas être un espace ' '");
                }
            }

            if(dto.getLastName()==null){
                errors.add("Veuillez renseigner le nom du client");
            }else {
                if (dto.getLastName().isBlank()){
                    errors.add("Le nom ne peut pas être un espace ' '");
                }
            }

            if (dto.getEmail()==null && dto.getPhone()==null){
                errors.add("Veuillez renseigner l'email ou le numéro de téléphone du client");
            }else {
                if (dto.getEmail()!=null && !dto.getEmail().matches(emailRegex)){
                    errors.add("Veuillez renseigner un email correct");
                }
                if (dto.getPhone()!=null &&  !dto.getPhone().matches(phoneRegex)){
                    errors.add("Veuillez renseigner un numéro correct");
                }
            }
            if (dto.getStatus()!=null){
                boolean isStatusExists = false;
                for (ClientStatus clientStatus : ClientStatus.values()){
                    if (clientStatus.equals(dto.getStatus())) {
                        isStatusExists = true;
                        break;
                    }
                }
                if (!isStatusExists){
                    errors.add("Veuillez un status client correct");
                }
            }

        }

        return errors;
    }

    public static List<String> updateValidation(CreateClientDTO dto){
        List<String> errors = new ArrayList<>();

        if (dto == null){
            errors.add("Veuillez renseigner l'identifiant du client");
            errors.add("Veuillez renseigner les propriétés du client à mettre à jour");
        }
        else {
            if (dto.getId()==null){
                errors.add("Veuillez renseigner l'identifiant du client");
            }else{
                if (dto.getId().isEmpty() || dto.getId().isBlank()){
                    errors.add("Identifiant du client incorrect");
                }
            }


            if (StringUtils.hasLength(dto.getFirstName()) && dto.getFirstName().isBlank()){
                errors.add("Le prénom ne peut pas être un espace ' '");
            }
            if (StringUtils.hasLength(dto.getLastName()) && dto.getLastName().isBlank()){
                errors.add("Le nom ne peut pas être un espace ' '");
            }
            if (StringUtils.hasLength(dto.getAddress()) && dto.getAddress().isBlank()){
                errors.add("L'adresse ne peut pas être un espace ' '");
            }
            if (StringUtils.hasLength(dto.getPhoto()) && dto.getPhoto().isBlank()){
                errors.add("La photo ne peut pas être un espace ' '");
            }
            if (StringUtils.hasLength(dto.getEmail()) && !dto.getEmail().matches(emailRegex)){
                errors.add("L'email est incorrect");
            }
            if (StringUtils.hasLength(dto.getPhone()) && !dto.getPhone().matches(phoneRegex)){
                errors.add("Le numéro de téléphone est incorrect");
            }

            if (dto.getStatus()!=null){
                boolean isStatusExists = false;
                for (ClientStatus clientStatus : ClientStatus.values()){
                    if (clientStatus.equals(dto.getStatus())) {
                        isStatusExists = true;
                        break;
                    }
                }
                if (!isStatusExists){
                    errors.add("Veuillez un status client correct");
                }
            }

        }

        return errors;
    }

}
