package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.client.ClientDTO;
import com.traore.stockmanagement.dto.client.CreateClientDTO;
import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import com.traore.stockmanagement.enums.ClientStatus;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.mapper.ClientMapper;
import com.traore.stockmanagement.model.Client;
import com.traore.stockmanagement.repository.ClientRepo;
import com.traore.stockmanagement.repository.EnterpriseRepo;
import com.traore.stockmanagement.service.AttachmentService;
import com.traore.stockmanagement.service.ClientService;
import com.traore.stockmanagement.validator.ClientValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    private ClientRepo repository;
    private ClientMapper mapper;
    private EnterpriseRepo enterpriseRepo;
    private AttachmentService attachmentService;



    @Override
    public ClientDTO save(CreateClientDTO dto) {
        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Client creation dto {} is not valid",dto);
            throw new InvalidEntityException("Client invalide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        if (!enterpriseRepo.existsById(dto.getEnterprise().getId())){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", dto.getEnterprise().getId()), ErrorCodes.ENTERPRISE_NOT_FOUND);
        }

        return mapper.fromEntity(
                repository.save(
                        mapper.toEntity(dto)
                )
        );
    }

    @Override
    public ClientDTO save(MultipartFile file, String firstName, String lastName, String email, String phone, String address, ClientStatus status, Long enterpriseId) {
        CreateClientDTO dto = CreateClientDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .address(address)
                .status(status)
                .enterprise(
                        UpdateEnterpriseDTO.builder()
                                .id(enterpriseId)
                                .build()
                )
                .build();

        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Client creation dto {} is not valid",dto);
            throw new InvalidEntityException("Client invalide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        if (!enterpriseRepo.existsById(dto.getEnterprise().getId())){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", dto.getEnterprise().getId()), ErrorCodes.ENTERPRISE_NOT_FOUND);
        }

        //Inserting image
        if (!file.isEmpty()){
            dto.setPhoto(
                    attachmentService.saveImage(file)
            );
        }

        return mapper.fromEntity(
                repository.save(
                        mapper.toEntity(dto)
                )
        );
    }

    @Override
    public CreateClientDTO update(CreateClientDTO dto) {
        List<String> errors = ClientValidator.updateValidation(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Client creation dto {} is not valid",dto);
            throw new InvalidEntityException("Format de modification du client invalide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }

        Client client = repository.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Client avec ID {%s} non trouvé", dto.getId()), ErrorCodes.CLIENT_NOT_FOUND)
        );

        if (dto.getFirstName()!=null && !dto.getFirstName().isBlank()){
            client.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName()!=null && !dto.getLastName().isBlank()){
            client.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null){
            client.setEmail(dto.getEmail());
        }
        if (dto.getAddress()!=null && !dto.getAddress().isBlank()){
            client.setAddress(dto.getAddress());
        }
        if (dto.getPhone()!=null && !dto.getPhone().isBlank()){
            client.setPhone(dto.getPhone());
        }
        if (dto.getPhoto()!=null && !dto.getPhoto().isBlank()){
            client.setPhoto(dto.getPhoto());
        }
        if (dto.getStatus()!=null){
            client.setStatus(dto.getStatus());
        }

        return mapper.fromEntityToCreateClientDTO(
                repository.save(client)
        );
    }

    @Override
    public ClientDTO getClient(String clientId) {
        if (clientId!=null && !clientId.isBlank()){
            Client client = repository.findById(clientId).orElseThrow(
                    ()->new EntityNotFoundException(String.format("Client avec ID {%s} non trouvé", clientId), ErrorCodes.CLIENT_NOT_FOUND)
            );

            return mapper.fromEntity(client);
        }else {
            throw new InvalidEntityException("Identifiant du client incorrect");
        }

    }

    @Override
    public List<ClientDTO> getClients(Long enterpriseId) {
        if (!enterpriseRepo.existsById(enterpriseId)){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée",enterpriseId),ErrorCodes.ENTERPRISE_NOT_FOUND);
        }

        return mapper.fromEntity(
                repository.findClientsByEnterprise_Id(enterpriseId)
        );
    }

    @Override
    public void delete(String clientId) {
        if (clientId!=null && !clientId.isBlank()){
            Client client = repository.findById(clientId).orElseThrow(
                    ()-> new EntityNotFoundException(String.format("Client avec ID {%s} non trouvé", clientId), ErrorCodes.CLIENT_NOT_FOUND)
            );
            if (client.getCommandClients().isEmpty()){
                repository.deleteById(clientId);
            }else {
                throw new InvalidOperationException(String.format("Client avec ID {%s} est sur au moins une commande",clientId), ErrorCodes.CLIENT_ALREADY_IN_USE);
            }

        }else{
            throw new InvalidEntityException("Identifiant de client invalide", ErrorCodes.CLIENT_NOT_VALID);
        }
    }
}
