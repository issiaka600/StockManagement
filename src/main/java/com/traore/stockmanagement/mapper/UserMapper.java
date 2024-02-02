package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.user.CreateUserDTO;
import com.traore.stockmanagement.dto.user.UserDTO;
import com.traore.stockmanagement.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public static UserDTO fromEntityStatic(User user){
        if (user == null){
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .address(user.getAddress())
                .photo(user.getPhoto())
                .phone(user.getPhone())
                .updateEnterpriseDTO(
                        EnterpriseMapper.fromEntityToUpdateDTOStatic(user.getEnterprise())
                )
                .roles(
                        RolesMapper.fromEntityStatic(user.getRoles())
                )
                .build();
    }
    public UserDTO fromEntity(User user){
        if (user == null){
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .address(user.getAddress())
                .photo(user.getPhoto())
                .phone(user.getPhone())
                .updateEnterpriseDTO(
                        EnterpriseMapper.fromEntityToUpdateDTOStatic(user.getEnterprise())
                )
                .roles(
                        RolesMapper.fromEntityStatic(user.getRoles())
                )
                .build();
    }

    public List<UserDTO> fromEntity(List<User> users){
        if (users != null){
            List<UserDTO> dtos = new ArrayList<>();
            for (User user : users){
                dtos.add(
                        fromEntityStatic(user)
                );
            }
            return dtos;
        }
        return null;
    }

    public static List<UserDTO> fromEntityStatic(List<User> users){
        if (users != null){
            List<UserDTO> dtos = new ArrayList<>();
            for (User user : users){
                dtos.add(
                        fromEntityStatic(user)
                );
            }
            return dtos;
        }
        return null;
    }

    public User fromCreateUserDTOtoEntity(CreateUserDTO dto){
        if (dto!=null){
            return User.builder()
                    .firstName(dto.getFirstName().trim())
                    .lastName(dto.getLastName().trim())
                    .email(dto.getEmail().trim())
                    .password(dto.getPassword())
                    .photo(dto.getPhoto())
                    .phone(dto.getPhone().trim())
                    .address(dto.getAddress())
                    .enterprise(
                            EnterpriseMapper.fromUpdateDTOToEntityStatic(
                                    dto.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }
    public static User fromCreateUserDTOtoEntityStatic(CreateUserDTO dto){
        if (dto!=null){
            return User.builder()
                    .id(dto.getId())
                    .firstName(dto.getFirstName().trim())
                    .lastName(dto.getLastName().trim())
                    .email(dto.getEmail().trim())
                    .password(dto.getPassword())
                    .photo(dto.getPhoto().trim())
                    .phone(dto.getPhone().trim())
                    .enterprise(
                            EnterpriseMapper.fromUpdateDTOToEntityStatic(
                                    dto.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }

    public CreateUserDTO FromEntityToCreateUserDTO(User user){
        if (user!=null){
            return CreateUserDTO.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName().trim())
                    .lastName(user.getLastName().trim())
                    .email(user.getEmail().trim())
                    .password(user.getPassword())
                    .photo(user.getPhoto().trim())
                    .phone(user.getPhone().trim())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    user.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }
    public static CreateUserDTO FromEntityToCreateUserDTOStatic(User user){
        if (user!=null){
            return CreateUserDTO.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName().trim())
                    .lastName(user.getLastName().trim())
                    .email(user.getEmail().trim())
                    .password(user.getPassword())
                    .photo(user.getPhoto().trim())
                    .phone(user.getPhone().trim())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    user.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }


}
