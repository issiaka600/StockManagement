package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.provider.CreateProviderDTO;
import com.traore.stockmanagement.dto.provider.ProviderDTO;
import com.traore.stockmanagement.model.Provider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProviderMapper {
    public ProviderDTO fromEntity(Provider provider){
        if(provider != null){
            return ProviderDTO.builder()
                    .id(provider.getId())
                    .firstName(provider.getFirstName())
                    .lastName(provider.getLastName())
                    .email(provider.getEmail())
                    .address(provider.getAddress())
                    .phone(provider.getPhone())
                    .photo(provider.getPhoto())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    provider.getEnterprise()
                            )
                    )
                    .commandProviders(
                            CommandProviderMapper.fromEntity(
                                    provider.getCommandProviders()
                            )
                    )
                    .build();
        }
        return null;
    }
    public List<ProviderDTO> fromEntity(List<Provider> providers){
        if (providers != null){
            List<ProviderDTO> dtos = new ArrayList<>();
            for (Provider provider : providers){
                dtos.add(
                        fromEntity(provider)
                );
            }
            return dtos;
        }
        return null;
    }

    public CreateProviderDTO fromEntityToCreateProviderDTO(Provider provider){
        if(provider != null){
            return CreateProviderDTO.builder()
                    .id(provider.getId())
                    .firstName(provider.getFirstName())
                    .lastName(provider.getLastName())
                    .email(provider.getEmail())
                    .address(provider.getAddress())
                    .phone(provider.getPhone())
                    .photo(provider.getPhoto())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    provider.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }

    /*
    *Return an adapted Provider object that constains dto.properties or null for properties undefined in dto
    *
     */
    public Provider fromCreateProviderDTOToEntity(CreateProviderDTO dto){
        if(dto != null){
            return Provider.builder()
                    .firstName( dto.getFirstName()!=null ? dto.getFirstName().trim() : null)
                    .lastName(dto.getLastName()!= null ?  dto.getLastName().trim() : null)
                    .email(dto.getEmail()!=null ?  dto.getEmail().trim() : null)
                    .address(dto.getAddress()!=null ? dto.getAddress().trim() : null)
                    .phone(dto.getPhone() !=null ? dto.getPhone().trim() : null)
                    .photo(dto.getPhoto()!=null ?  dto.getPhoto().trim() :  null)
                    .enterprise(
                            EnterpriseMapper.fromUpdateDTOToEntityStatic(
                                    dto.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }

    public static Provider fromCreateProviderDTOToEntityStatic(CreateProviderDTO dto){
        if(dto != null){
            return Provider.builder()
                    .id(dto.getId())
                    .firstName( dto.getFirstName()!=null ? dto.getFirstName().trim() : null)
                    .lastName(dto.getLastName()!= null ?  dto.getLastName().trim() : null)
                    .email(dto.getEmail()!=null ?  dto.getEmail().trim() : null)
                    .address(dto.getAddress()!=null ? dto.getAddress().trim() : null)
                    .phone(dto.getPhone() !=null ? dto.getPhone().trim() : null)
                    .photo(dto.getPhoto()!=null ?  dto.getPhoto().trim() :  null)
                    .enterprise(
                            EnterpriseMapper.fromUpdateDTOToEntityStatic(
                                    dto.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }


    public static CreateProviderDTO fromEntityToCreateProviderDTOStatic(Provider provider){
        if(provider != null){
            return CreateProviderDTO.builder()
                    .id(provider.getId())
                    .firstName(provider.getFirstName())
                    .lastName(provider.getLastName())
                    .email(provider.getEmail())
                    .address(provider.getAddress())
                    .phone(provider.getPhone())
                    .photo(provider.getPhoto())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    provider.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }
}
