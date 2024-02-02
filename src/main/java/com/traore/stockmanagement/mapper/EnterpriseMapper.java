package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.enterprise.*;
import com.traore.stockmanagement.model.Enterprise;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EnterpriseMapper {

    public Enterprise fromCreateDTOToEntity(CreateEnterpriseDTO dto) {
        return Enterprise.builder()
                .name(dto.getName().trim())
                .logoId(dto.getLogoId())
                .build();
    }
    public EnterpriseWithAttachmentDTO fromEntityToEnterpriseWithAttachment(Enterprise enterprise){
        return  EnterpriseWithAttachmentDTO.builder()
                .id(enterprise.getId())
                .name(enterprise.getName())
                .logoId(enterprise.getLogoId()!=null ? enterprise.getLogoId() : null )
                .build();
    }
    public UpdateEnterpriseDTO fromEntityToUpdateDTO(Enterprise enterprise){
        return UpdateEnterpriseDTO.builder()
                .id(enterprise.getId())
                .name(enterprise.getName())
                .logoId(enterprise.getLogoId())
                .build();
    }
    public Enterprise fromUpdateDTOToEntity(UpdateEnterpriseDTO dto){
        return Enterprise.builder()
                .id(dto.getId())
                .name(dto.getName())
                .logoId(dto.getLogoId())
                .build();
    }
    public List<UpdateEnterpriseDTO> fromEntityToUpdateDTO(List<Enterprise> enterprises){
        if(enterprises != null){
            List<UpdateEnterpriseDTO> dtos = new ArrayList<>();
            for (Enterprise enterprise : enterprises){
                dtos.add(
                        fromEntityToUpdateDTO(enterprise)
                );
            }
            return dtos;
        }
        return null;
    }
    public EnterpriseWithUsersDTO fromEntityToDTOWithUsers(Enterprise enterprise){
        if (enterprise != null){
            return EnterpriseWithUsersDTO.builder()
                    .id(enterprise.getId())
                    .name(enterprise.getName())
                    .logoId(enterprise.getLogoId())
                    .userDTOS(
                            UserMapper.fromEntityStatic(
                                    enterprise.getUsers()
                            )
                    )
                    .build();
        }
        return null;
    }

    public EnterpriseWithDepartmentStoreDTO fromEntityToDTOWithDepartmentStores(Enterprise enterprise){
        if (enterprise != null){
            return EnterpriseWithDepartmentStoreDTO.builder()
                    .id(enterprise.getId())
                    .name(enterprise.getName())
                    .logoId(enterprise.getLogoId())
                    .departmentStores(
                          DepartmentStoreMapper.fromEntityToDepartmentStoreOnlyDTO(
                                  enterprise.getDepartmentStores()
                          )
                    )
                    .build();
        }
        return null;
    }

    public EnterpriseWithCategories fromEntityToEnterpriseWithCategories(Enterprise enterprise){
        if(enterprise !=null){
            return EnterpriseWithCategories.builder()
                    .id(enterprise.getId())
                    .name(enterprise.getName())
                    .logoId(enterprise.getLogoId())
                    .categories(
                            CategoryMapper.fromEntityStatic(
                                    enterprise.getCategories()
                            )
                    )
                    .build();
        }
        return null;
    }

    public EnterpriseWithProductsDTO fromEntityToEDTOWithProducts(Enterprise enterprise){
        if(enterprise != null){
            return EnterpriseWithProductsDTO.builder()
                    .id(enterprise.getId())
                    .name(enterprise.getName())
                    .logoId(enterprise.getLogoId())
                    .products(
                            ProductMapper.fromEntityStatic(
                                    enterprise.getProducts()
                            )
                    )
                    .build();
        }
        return null;
    }


    public static UpdateEnterpriseDTO fromEntityToUpdateDTOStatic(Enterprise enterprise){
        return UpdateEnterpriseDTO.builder()
                .id(enterprise.getId())
                .name(enterprise.getName())
                .logoId(enterprise.getLogoId())
                .build();
    }

    public static Enterprise fromUpdateDTOToEntityStatic(UpdateEnterpriseDTO dto){
        return Enterprise.builder()
                .id(dto.getId())
                .name(dto.getName())
                .logoId(dto.getLogoId())
                .build();
    }






}
