package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DepartmentStoreValidator {
    private static List<String> errors;

    public static List<String> validate(DepartmentStoreDTO dto){
        errors = new ArrayList<>();
        if (dto == null){
            errors.add("Veuillez renseigner le nom du magasin de stockage");
            errors.add("Veuillez renseigner l' entreprise");
        }else {
            if(!StringUtils.hasLength(dto.getName())){
                errors.add("Veuillez renseigner le nom du magasin");
            }
            if (dto.getName()!= null && dto.getName().isBlank()){
                errors.add("Le nom du magasin ne peut pas être espace ' '");
            }
            if (dto.getEnterprise() == null){
                errors.add("Veuillez renseigner l'entreprise");
            } else if (dto.getEnterprise().getId()== null) {
                errors.add("Veuillez renseigner l'identifiant de l'entreprise");
            }
        }

        return errors;
    }

    public static List<String> updateValidation(DepartmentStoreDTO dto){
        errors = new ArrayList<>();
        if (dto==null){
            errors.add("Veuillez renseigner le nom du magasin de stockage");
            errors.add("Veuillez renseigner l' entreprise");
        }else{
            if (dto.getId() == null){
                errors.add("Veuillez renseigner l'ID du magasin");
            }
            if (dto.getName() == null){
                errors.add("Veuillez renseigner le nouveau nom du magasin");
            }
            if (dto.getName() != null && dto.getName().isBlank()){
                errors.add("Le nom de magasin ne peut pas un espace ' '");
            }
        }
        return errors;

    }
}
