package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.client.ClientDTO;
import com.traore.stockmanagement.dto.client.CreateClientDTO;
import com.traore.stockmanagement.enums.ClientStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientService {
    ClientDTO save(CreateClientDTO dto);
    ClientDTO save(MultipartFile file, String firstName, String lastName, String email, String phone, String address, ClientStatus status, Long enterpriseId);
    CreateClientDTO update(CreateClientDTO dto);
    ClientDTO getClient(String clientId);
    List<ClientDTO> getClients(Long enterpriseId);
    void delete(String clientId);
}
