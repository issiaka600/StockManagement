package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.client.ClientDTO;
import com.traore.stockmanagement.dto.client.CreateClientDTO;
import com.traore.stockmanagement.model.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper {
    public ClientDTO fromEntity(Client client){
        if(client != null){
            return ClientDTO.builder()
                    .id(client.getId())
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .email(client.getEmail())
                    .address(client.getAddress())
                    .phone(client.getPhone())
                    .photo(client.getPhoto())
                    .status(client.getStatus())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    client.getEnterprise()
                            )
                    )
                    .commandClients(
                            CommandClientMapper.fromEntity(
                                    client.getCommandClients()
                            )
                    )
                    .build();
        }
        return null;
    }
    public List<ClientDTO> fromEntity(List<Client> clients){
        if (clients!=null){
            List<ClientDTO> dtos = new ArrayList<>();
            for (Client client : clients){
                dtos.add(fromEntity(client));
            }
            return dtos;
        }
        return null;
    }

    public Client toEntity(CreateClientDTO dto){
        if(dto != null){
            return Client.builder()
                    .firstName(dto.getFirstName()!=null ? dto.getFirstName().trim() : null)
                    .lastName(dto.getLastName()!=null ? dto.getLastName().trim() : null)
                    .email(dto.getEmail() != null ? dto.getEmail().trim() : null)
                    .address(dto.getAddress()!=null ? dto.getAddress().trim() : null)
                    .phone(dto.getPhone()!=null ? dto.getPhone().trim() : null)
                    .photo(dto.getPhoto()!=null ? dto.getPhoto().trim() : null)
                    .status(dto.getStatus()!=null ? dto.getStatus() : null)
                    .enterprise(dto.getEnterprise() != null ?
                            EnterpriseMapper.fromUpdateDTOToEntityStatic(
                                    dto.getEnterprise()
                            ) : null
                    )
                    .build();
        }
        return null;
    }
    public CreateClientDTO fromEntityToCreateClientDTO(Client client){
        if(client != null){
            return CreateClientDTO.builder()
                    .id(client.getId())
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .email(client.getEmail())
                    .address(client.getAddress())
                    .phone(client.getPhone())
                    .photo(client.getPhoto())
                    .status(client.getStatus())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    client.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }

    public static CreateClientDTO fromEntityToCreateClientDTOStatic(Client client){
        if(client != null){
            return CreateClientDTO.builder()
                    .id(client.getId())
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .email(client.getEmail())
                    .address(client.getAddress())
                    .phone(client.getPhone())
                    .photo(client.getPhoto())
                    .status(client.getStatus())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    client.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }
    public static Client toEntityStatic(CreateClientDTO dto){
        if(dto != null){
            return Client.builder()
                    .id(dto.getId())
                    .firstName(dto.getFirstName()!=null ? dto.getFirstName().trim() : null)
                    .lastName(dto.getLastName()!=null ? dto.getLastName().trim() : null)
                    .email(dto.getEmail() != null ? dto.getEmail().trim() : null)
                    .address(dto.getAddress()!=null ? dto.getAddress().trim() : null)
                    .phone(dto.getPhone()!=null ? dto.getPhone().trim() : null)
                    .photo(dto.getPhoto()!=null ? dto.getPhoto().trim() : null)
                    .status(dto.getStatus()!=null ? dto.getStatus() : null)
                    .enterprise(dto.getEnterprise() != null ?
                            EnterpriseMapper.fromUpdateDTOToEntityStatic(
                                    dto.getEnterprise()
                            ) : null
                    )
                    .build();
        }
        return null;
    }
}
