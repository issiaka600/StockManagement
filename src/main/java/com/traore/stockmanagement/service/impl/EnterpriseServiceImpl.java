package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.enterprise.*;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.mapper.EnterpriseMapper;
import com.traore.stockmanagement.model.Attachment;
import com.traore.stockmanagement.model.Enterprise;
import com.traore.stockmanagement.repository.EnterpriseRepo;
import com.traore.stockmanagement.service.AttachmentService;
import com.traore.stockmanagement.service.EnterpriseService;
import com.traore.stockmanagement.validator.EnterpriseValidator;
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
public class EnterpriseServiceImpl implements EnterpriseService {
    private static final Logger logger = LoggerFactory.getLogger(EnterpriseServiceImpl.class);

    private EnterpriseRepo enterpriseRepo;
    private EnterpriseMapper enterpriseMapper;
    private AttachmentService attachmentService;

    @Override
    public EnterpriseWithAttachmentDTO save(MultipartFile file, String name) {
        CreateEnterpriseDTO dto = CreateEnterpriseDTO.builder()
                .name(name)
                .logo(file)
                .build();
        return save(dto);
    }

    @Override
    public EnterpriseWithAttachmentDTO save(CreateEnterpriseDTO dto) {
        List<String> errors = EnterpriseValidator.validate(dto);
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Entreprise invalide", ErrorCodes.ENTERPRISE_NOT_VALID, errors);
        }

        if (enterpriseRepo.existsByNameEqualsIgnoreCase(dto.getName())){
            throw new InvalidEntityException(String.format("Une entreprise porte déjà ce nom {%s} dans la base de données", dto.getName()), ErrorCodes.ENTERPRISE_ALREADY_EXISTS);
        }

        dto.setLogoId(
                attachmentService.saveImage(dto.getLogo())
        );

        EnterpriseWithAttachmentDTO enterprise = enterpriseMapper.fromEntityToEnterpriseWithAttachment(
                enterpriseRepo.save(
                        enterpriseMapper.fromCreateDTOToEntity(dto)
                )
        );
        enterprise.setAttachment(
                attachmentService.getAttachment(enterprise.getLogoId())
        );

        return enterprise;
    }

    @Override
    public UpdateEnterpriseDTO update(UpdateEnterpriseDTO dto) {
        List<String> errors = EnterpriseValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            throw new InvalidEntityException("Format de modification de l'entreprise non valide",ErrorCodes.ENTERPRISE_NOT_VALID,errors);
        }
        Enterprise existingEnterprise = enterpriseRepo.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException("Entreprise non trouvée", ErrorCodes.ENTERPRISE_NOT_FOUND)
        );
        if(!(dto.getName().isEmpty() && dto.getName().isBlank())){
            existingEnterprise.setName(dto.getName());
        }
        /*if(!(dto.getLogo().isEmpty() && dto.getLogo().isBlank())){
            existingEnterprise.setLogo(dto.getLogo());
        }

         */

        return enterpriseMapper.fromEntityToUpdateDTO(
                enterpriseRepo.save(
                        enterpriseMapper.fromUpdateDTOToEntity(
                                dto
                        )
                )
        );
    }

    @Override
    public List<EnterpriseWithAttachmentDTO> getEnterprisesOnly() {
        List<Enterprise> enterprises = enterpriseRepo.findAll();
        List<EnterpriseWithAttachmentDTO> list = new ArrayList<>();
        for (Enterprise enterprise : enterprises) {
            Attachment attachment = attachmentService.getAttachment(
                    enterprise.getLogoId()
            );
            EnterpriseWithAttachmentDTO enterpriseWithAttachmentDTO = enterpriseMapper.fromEntityToEnterpriseWithAttachment(
                    enterprise
            );
            enterpriseWithAttachmentDTO.setAttachment(attachment);
            list.add(enterpriseWithAttachmentDTO);
        }
        return list;

    }

    @Override
    public EnterpriseWithUsersDTO getEnterpriseAndUsers(Long enterpriseId) {
        Enterprise enterprise = enterpriseRepo.findById(enterpriseId).orElseThrow(
                ()->new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", enterpriseId), ErrorCodes.ENTERPRISE_NOT_FOUND)
        );
        return enterpriseMapper.fromEntityToDTOWithUsers(enterprise);
    }

    @Override
    public EnterpriseWithDepartmentStoreDTO getEnterpriseAndDepartmentStores(Long enterpriseId) {
        Enterprise enterprise = enterpriseRepo.findById(enterpriseId).orElseThrow(
                ()->new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", enterpriseId), ErrorCodes.ENTERPRISE_NOT_FOUND)
        );
        return enterpriseMapper.fromEntityToDTOWithDepartmentStores(enterprise);
    }

    @Override
    public EnterpriseWithCategories getEnterpriseWithCategories(Long enterpriseId) {
        Enterprise enterprise = enterpriseRepo.findById(enterpriseId).orElseThrow(
                ()->new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", enterpriseId), ErrorCodes.ENTERPRISE_NOT_FOUND)
        );
        return enterpriseMapper.fromEntityToEnterpriseWithCategories(enterprise);
    }

    @Override
    public EnterpriseWithProductsDTO getEnterpriseWithProducts(Long enterpriseId) {
        Enterprise enterprise = enterpriseRepo.findById(enterpriseId).orElseThrow(
                ()->new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", enterpriseId), ErrorCodes.ENTERPRISE_NOT_FOUND)
        );
        return enterpriseMapper.fromEntityToEDTOWithProducts(enterprise);
    }
}
