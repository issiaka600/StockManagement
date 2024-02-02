package com.traore.stockmanagement.validator;

import com.traore.stockmanagement.dto.roles.RolesDTO;
import com.traore.stockmanagement.enums.RoleName;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {
    public static List<String> validate(RolesDTO dto){
        List<String> errors = new ArrayList<>();
        if(dto==null){
            errors.add("Veuillez renseigner un role");
        }
        else {

            if (!StringUtils.hasLength(String.valueOf(dto.getName()))){
                errors.add("Veuillez renseigner un rôle");
            }else{
                RoleName[] roles = RoleName.values();
                boolean roleExists = false;
                for (int i = 0; i < roles.length; i++) {
                    if(roles[i].equals(dto.getName())){
                        roleExists = true;
                    }
                }
                if(!roleExists){
                    errors.add("Veuillez renseigner inexistant");
                }
            }
        }

        return errors;
    }
}
