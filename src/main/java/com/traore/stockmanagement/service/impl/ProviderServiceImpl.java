package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import com.traore.stockmanagement.dto.provider.CreateProviderDTO;
import com.traore.stockmanagement.dto.provider.ProviderDTO;
import com.traore.stockmanagement.enums.ClientStatus;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.mapper.ProviderMapper;
import com.traore.stockmanagement.model.Provider;
import com.traore.stockmanagement.repository.EnterpriseRepo;
import com.traore.stockmanagement.repository.ProviderRepo;
import com.traore.stockmanagement.service.AttachmentService;
import com.traore.stockmanagement.service.ProviderService;
import com.traore.stockmanagement.validator.ProviderValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private static final Logger logger = LoggerFactory.getLogger(ProviderServiceImpl.class);
    private ProviderRepo repository;
    private ProviderMapper mapper;
    private EnterpriseRepo enterpriseRepo;
    private AttachmentService attachmentService;

    private static List<String> errors = new ArrayList<>();

    @Override
    public ProviderDTO save(CreateProviderDTO dto) {
        errors = ProviderValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Provider creation dto {} is not valide", dto);
            throw new InvalidEntityException("Fournisseur invalide", ErrorCodes.PROVIDER_NOT_VALID, errors);
        }
        if (!enterpriseRepo.existsById(dto.getEnterprise().getId())){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", dto.getEnterprise().getId()), ErrorCodes.ENTERPRISE_NOT_FOUND);
        }

        return mapper.fromEntity(
                repository.save(
                        mapper.fromCreateProviderDTOToEntity(
                                dto
                        )
                )
        );
    }

    @Override
    public ProviderDTO save(MultipartFile file, String firstName, String lastName, String email, String phone, String address, ClientStatus status, Long enterpriseId) {
        CreateProviderDTO dto = CreateProviderDTO.builder()
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

        errors = ProviderValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Provider creation dto {} is not valide", dto);
            throw new InvalidEntityException("Fournisseur invalide", ErrorCodes.PROVIDER_NOT_VALID, errors);
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
                        mapper.fromCreateProviderDTOToEntity(
                                dto
                        )
                )
        );
    }

    @Override
    public CreateProviderDTO update(CreateProviderDTO dto) {
        errors = ProviderValidator.updateValidation(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Provider creation dto {} is not valide", dto);
            throw new InvalidEntityException("Fournisseur invalide", ErrorCodes.PROVIDER_NOT_VALID, errors);
        }

        Provider provider = repository.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Fournisseur avec ID {%s} non trouvé", dto.getId()), ErrorCodes.PROVIDER_NOT_FOUND)
        );
        if (dto.getFirstName()!=null && !dto.getFirstName().isBlank()){
            provider.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName()!=null && !dto.getLastName().isBlank()){
            provider.setLastName(dto.getLastName());
        }
        if (dto.getEmail()!=null && !dto.getEmail().isBlank()){
            provider.setEmail(dto.getEmail());
        }
        if (dto.getAddress() != null && !dto.getAddress().isBlank()){
            provider.setAddress(dto.getAddress());
        }
        if (dto.getPhoto() != null && !dto.getPhoto().isBlank()){
            provider.setPhoto(dto.getPhoto());
        }
        if (dto.getPhone()!=null && !dto.getPhone().isBlank()){
            provider.setPhone(dto.getPhone());
        }

        return mapper.fromEntityToCreateProviderDTO(
                repository.save(provider)
        );
    }

    @Override
    public ProviderDTO getProvider(String providerId) {
        if (providerId!=null && !providerId.isBlank()){
            Provider provider = repository.findById(providerId).orElseThrow(
                    ()->new EntityNotFoundException(String.format("Fournisseur avec ID {%s} non trouvé", providerId), ErrorCodes.PROVIDER_NOT_FOUND)
            );
            return mapper.fromEntity(provider);
        }else {
            throw new InvalidEntityException("Identifiant du fournisseur incorrect", ErrorCodes.PROVIDER_NOT_VALID);
        }
    }

    @Override
    public List<ProviderDTO> getProviders(Long enterpriseId) {
        if (!enterpriseRepo.existsById(enterpriseId)){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvé",enterpriseId),ErrorCodes.ENTERPRISE_NOT_FOUND);
        }

        return mapper.fromEntity(
                repository.findProvidersByEnterprise_Id(enterpriseId)
        );
    }

    @Override
    public void delete(String providerId) {
        if (providerId!=null && !providerId.isBlank()){
            Provider provider = repository.findById(providerId).orElseThrow(
                    ()-> new EntityNotFoundException(String.format("Fournisseur avec ID {%s} non trouvé", providerId), ErrorCodes.PROVIDER_NOT_FOUND)
            );
            if (provider.getCommandProviders().isEmpty()){
                repository.deleteById(providerId);
            }else {
                throw new InvalidOperationException(String.format("Fournisseur avec ID {%s} est sur au moins une commande",providerId), ErrorCodes.PROVIDER_ALREADY_IN_USE);
            }

        }else{
            throw new InvalidEntityException("Identifiant de fournisseur invalide", ErrorCodes.PROVIDER_NOT_VALID);
        }
    }
}
