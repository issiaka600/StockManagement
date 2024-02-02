package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.ClientAPI;
import com.traore.stockmanagement.dto.client.ClientDTO;
import com.traore.stockmanagement.dto.client.CreateClientDTO;
import com.traore.stockmanagement.enums.ClientStatus;
import com.traore.stockmanagement.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController implements ClientAPI {
    private ClientService service;

    @Override
    public ClientDTO save(CreateClientDTO dto) {
        return service.save(dto);
    }

    @Override
    public ClientDTO save(MultipartFile file, String firstName, String lastName, String email, String phone, String address, ClientStatus status, Long enterpriseId) {
        return service.save(file, firstName, lastName, email, phone, address, status, enterpriseId);
    }

    @Override
    public CreateClientDTO update(CreateClientDTO dto) {
        return service.update(dto);
    }

    @Override
    public ClientDTO getClient(String clientId) {
        return service.getClient(clientId);
    }

    @Override
    public List<ClientDTO> getClients(Long enterpriseId) {
        return service.getClients(enterpriseId);
    }

    @Override
    public void delete(String clientId) {
        service.delete(clientId);
    }
}
