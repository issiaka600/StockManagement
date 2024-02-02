package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.user.CreateUserDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    private static final String emailRegex = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$";
    private static final String phoneRegex = "^[0-9]\\d{7}";
    public static List<String> validate(CreateUserDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner le prénom d'utilisateur");
            errors.add("Veuillez renseigner le nom d'utilisateur");
            errors.add("Veuillez renseigner l'email d'utilisateur");
            errors.add("Veuillez renseigner les mots de passe d'utilisateur");
            errors.add("Veuillez renseigner l'adresse d'utilisateur");
            errors.add("Veuillez renseigner le numéro de téléphone d'utilisateur");
            errors.add("Veuillez renseigner la photo d'utilisateur");
        }
        else{
            if(!StringUtils.hasLength(dto.getFirstName())){
                errors.add("Veuillez renseigner le prénom d'utilisateur");
            }else {
                if (dto.getFirstName().isBlank()){
                    errors.add("Le prénom ne peut pas être un espace ' '");
                }
            }

            if(!StringUtils.hasLength(dto.getLastName())){
                errors.add("Veuillez renseigner le nom d'utilisateur");
            }else {
                if (dto.getLastName().isBlank()){
                    errors.add("Le nom ne peut pas être un espace ' '");
                }
            }

            if(!StringUtils.hasLength(dto.getEmail())){
                errors.add("Veuillez renseigner l'email d'utilisateur");
            }else if (dto.getEmail().isBlank()){
                    errors.add("L'email ne peut pas être un espace ' '");

            }
            else if(!dto.getEmail().matches(emailRegex)){
                errors.add("Veuillez renseigner un email correct");
            }

            if(!StringUtils.hasLength(dto.getPhone())){
                errors.add("Veuillez renseigner le numéro de téléphone de l'utilisateur");
            }else if (dto.getPhone().isBlank()){
                errors.add("Le numéro de téléphone ne peut pas être un espace ' '");

            }
            else if(!dto.getPhone().matches(phoneRegex)){
                errors.add("Veuillez renseigner un numéro de téléphone correct");
            }

            if(!StringUtils.hasLength(dto.getPassword())){
                errors.add("Veuillez renseigner les mots de passe d'utilisateur");
            }

            if(!StringUtils.hasLength(dto.getAddress())){
                errors.add("Veuillez renseigner l'adresse de l'utilisateur");
            }

        }
        return errors;
    }

    public static List<String> updateValidation(CreateUserDTO dto){
        List<String> errors = new ArrayList<>();

        if (dto == null){
            errors.add("Veuillez renseigner l'identifiant de l'utilisateur");
            errors.add("Veuillez renseigner les propriétés d'utilisateur à mettre à jour");
        }
        else {
            if (!Long.TYPE.isInstance(dto.getId())){
                errors.add("Identifiant d'utilisateur incorrect");
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

        }

        return errors;
    }
}
