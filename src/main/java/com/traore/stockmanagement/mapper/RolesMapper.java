package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.roles.RolesDTO;
import com.traore.stockmanagement.model.Roles;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RolesMapper {
    public Roles toEntity(RolesDTO dto){
        return Roles.builder()
                .name(dto.getName())
                .user(
                        UserMapper.fromCreateUserDTOtoEntityStatic(
                                dto.getUser()
                        )
                )
                .build();
    }
    public RolesDTO fromEntity(Roles role){
        return RolesDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static RolesDTO fromEntityStatic(Roles role){
        return RolesDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
    public List<RolesDTO> fromEntity(List<Roles> roles){
        if (roles !=null){
            List<RolesDTO> dtos = new ArrayList<>();
            for (Roles role : roles){
                dtos.add(fromEntity(role));
            }
            return dtos;
        }
        return null;
    }


    public static List<RolesDTO> fromEntityStatic(List<Roles> roles){
        if (roles !=null){
            List<RolesDTO> dtos = new ArrayList<>();
            for (Roles role : roles){
                dtos.add(fromEntityStatic(role));
            }
            return dtos;
        }
        return null;
    }
}
