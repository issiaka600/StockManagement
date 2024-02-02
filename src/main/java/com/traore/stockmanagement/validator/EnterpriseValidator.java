package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.enterprise.CreateEnterpriseDTO;
import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseValidator {

    public static List<String> validate(CreateEnterpriseDTO dto){
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez renseigner le nom de l'entreprise");
            errors.add("Veuillez renseigner le logo de l'entreprise");
        }
        else {
            if(!StringUtils.hasLength(dto.getName())){
                errors.add("Veuillez le nom de l'entreprise");
            }

            if(dto.getName().isBlank()){
                errors.add("Le nom ne peut pas être espace ' '");
            }
            if(dto.getLogo()==null){
                errors.add("Veuillez renseigner le logo de l'entreprise");
            }
        }

        return errors;
    }
    public static List<String> validate(UpdateEnterpriseDTO dto){
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez renseigner l'identifiant de l'entreprise");
            errors.add("Veuillez renseigner la propriété à mettre à jour");
        }
        else{
            if(StringUtils.hasLength(dto.getName()) && dto.getName().isBlank()){
                errors.add("Le nom de l'entreprise ne peut pas être espace ' '");
            }
            if(StringUtils.hasLength(dto.getLogoId()) && dto.getLogoId().isBlank()){
                errors.add("Le logo de l'entreprise ne peut pas être espace ' '");
            }
            if(!StringUtils.hasLength(dto.getName()) && !StringUtils.hasLength(dto.getLogoId())){
                errors.add("Veuillez renseigner la propriété à mettre à jour");
            }
        }

        return errors;
    }
}
