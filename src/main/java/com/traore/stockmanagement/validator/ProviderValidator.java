package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.provider.CreateProviderDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProviderValidator {
    private static final String emailRegex = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$";
    private static final String phoneRegex = "^[0-9]\\d{7}$";

    public static List<String> validate(CreateProviderDTO dto){

        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner le prénom du fournisseur");
            errors.add("Veuillez renseigner le nom du fournisseur");
            errors.add("Veuillez renseigner l'email du fournisseur");
            errors.add("Veuillez renseigner le numéro de téléphone du fournisseur");
            errors.add("Veuillez renseigner la photo du fournisseur");
            errors.add("Veuillez renseigner l'adresse du fournisseur");
            errors.add("Veuillez renseigner l'entreprise");
        }else {
            if (dto.getEnterprise() == null){
                errors.add("Veuillez renseigner l'entrpise");
            }else{
                if (dto.getEnterprise().getId() == null){
                    errors.add("Veuillez renseigner l'identifiant de l'entreprise");
                }
            }

            if(!StringUtils.hasLength(dto.getFirstName())){
                errors.add("Veuillez renseigner le prénom du fournisseur");
            }else {
                if (dto.getFirstName().isBlank()){
                    errors.add("Le prénom ne peut pas être un espace ' '");
                }
            }

            if(!StringUtils.hasLength(dto.getLastName())){
                errors.add("Veuillez renseigner le nom du fournisseur");
            }else {
                if (dto.getLastName().isBlank()){
                    errors.add("Le nom ne peut pas être un espace ' '");
                }
            }

            if (dto.getEmail()==null && dto.getPhone()==null){
                errors.add("Veuillez renseigner l'email ou le numéro de téléphone du fournisseur");
            }else {
                if (dto.getEmail()!=null && !dto.getEmail().isEmpty() && (dto.getEmail().isBlank() || !dto.getEmail().matches(emailRegex))){
                    errors.add("Veuillez renseigner un email correct");
                }
                if (dto.getPhone()!=null && !dto.getPhone().isEmpty() && (dto.getPhone().isBlank() || !dto.getPhone().matches(phoneRegex))){
                    errors.add("Veuillez renseigner un numéro correct");
                }
            }

        }

        return errors;
    }

    public static List<String> updateValidation(CreateProviderDTO dto){
        List<String> errors = new ArrayList<>();

        if (dto == null){
            errors.add("Veuillez renseigner l'identifiant du fournisseur");
            errors.add("Veuillez renseigner les propriétés du fournisseur à mettre à jour");
        }
        else {
            if (dto.getId()==null){
                errors.add("Veuillez renseigner l'identifiant du fournisseur");
            }else{
                if (dto.getId().isEmpty() || dto.getId().isBlank()){
                    errors.add("Identifiant du fournisseur incorrect");
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

        }

        return errors;
    }
}
