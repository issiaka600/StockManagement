package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {
    public static List<String> validate(CategoryDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner le nom de la catégorie");
            errors.add("Veuillez renseigner l' entreprise");
        }else {
            if(!StringUtils.hasLength(dto.getName())){
                errors.add("Veuillez renseigner le nom de la catégorie");
            }
            if (dto.getName()!= null && dto.getName().isBlank()){
                errors.add("Le nom de catégorie ne peut pas être espace ' '");
            }
            if (dto.getEnterprise() == null){
                errors.add("Veuillez renseigner l'entreprise");
            } else if (dto.getEnterprise().getId()== null) {
                errors.add("Veuillez renseigner l'identifiant de l'entreprise");
            }
        }

        return errors;
    }
    public static List<String> updateValidation(CategoryDTO dto){
        List<String> errors = new ArrayList<>();
        if (dto==null){
            errors.add("Veuillez renseigner l'identifiant de la catégorie");
            errors.add("Veuillez renseigner le nouveau nom de la catégorie");
        }else{
            if (dto.getId() == null){
                errors.add("Veuillez renseigner l'ID de la catégorie");
            }
            if (dto.getName() == null){
                errors.add("Veuillez renseigner le nouveau nom de la catégorie");
            }
            if (dto.getName() != null && dto.getName().isBlank()){
                errors.add("Le nom de catégorie ne peut pas un espace ' '");
            }
        }
        return errors;

    }
}
