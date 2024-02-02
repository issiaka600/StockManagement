package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreWithLineStoragesDTO;
import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreWithStockMovementDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.mapper.DepartmentStoreMapper;
import com.traore.stockmanagement.model.DepartmentStore;
import com.traore.stockmanagement.repository.DepartmentStoreRepo;
import com.traore.stockmanagement.repository.EnterpriseRepo;
import com.traore.stockmanagement.service.DepartmentStoreService;
import com.traore.stockmanagement.validator.DepartmentStoreValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentStoreServiceImpl implements DepartmentStoreService {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentStoreServiceImpl.class);
    private DepartmentStoreRepo repository;
    private DepartmentStoreMapper mapper;
    private EnterpriseRepo enterpriseRepo;

    @Override
    public DepartmentStoreDTO save(DepartmentStoreDTO dto) {
        List<String> errors = DepartmentStoreValidator.validate(dto);
        if(!errors.isEmpty()){
            for (String error  : errors){
                logger.error(error);
            }
            logger.error("DepartmentStoreDTO is not valid {}", dto);
            throw new InvalidEntityException("Magasin invalide", ErrorCodes.DEPARTMENT_STORE_NOT_VALID, errors);
        }
        if (!enterpriseRepo.existsById(dto.getEnterprise().getId())){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", ErrorCodes.ENTERPRISE_NOT_FOUND));
        }

        return mapper.fromEntity(
                repository.save(
                        mapper.toEntity(dto)
                )
        ) ;
    }

    @Override
    public DepartmentStoreDTO update(DepartmentStoreDTO dto) {
        List<String> errors = DepartmentStoreValidator.updateValidation(dto);
        if(!errors.isEmpty()){
            for (String error  : errors){
                logger.error(error);
            }
            logger.error("DepartmentStoreDTO is not valid {}", dto);
            throw new InvalidEntityException("Format de modification du magasin invalide", ErrorCodes.DEPARTMENT_STORE_NOT_VALID, errors);
        }

        DepartmentStore departmentStore = repository.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Magasin avec ID {%s} non trouvé", dto.getId()), ErrorCodes.DEPARTMENT_STORE_NOT_FOUND)
        );
        departmentStore.setName(dto.getName());

        return mapper.fromEntity(
                repository.save(
                        mapper.toEntity(dto)
                )
        ) ;
    }

    @Override
    public DepartmentStoreWithLineStoragesDTO getDepartmentStoreWithLineStorages(Long departmentStoreId) {
       DepartmentStore departmentStore = repository.findById(departmentStoreId).orElseThrow(
               ()->new EntityNotFoundException(String.format("Magasin avec ID {%s} non trouvé", departmentStoreId), ErrorCodes.DEPARTMENT_STORE_NOT_FOUND)
       );
        return mapper.fromEntityToDepartmentStoreDetails(
                departmentStore
        );
    }
    @Override
    public DepartmentStoreWithStockMovementDTO getDepartmentStoreWithStockMovements(Long departmentStoreId) {
        DepartmentStore departmentStore = repository.findById(departmentStoreId).orElseThrow(
                ()->new EntityNotFoundException(String.format("Magasin avec ID {%s} non trouvé", departmentStoreId), ErrorCodes.DEPARTMENT_STORE_NOT_FOUND)
        );
        return mapper.fromEntityToDepartmentStoreWithStockMvt(
                departmentStore
        );
    }

    @Override
    public void delete(Long departmentStoreId) {
        if (departmentStoreId!=null){
            DepartmentStore departmentStore = repository.findById(departmentStoreId).orElseThrow(
                    ()->new EntityNotFoundException(String.format("Magasin avec ID {%s} non trouvée",departmentStoreId), ErrorCodes.DEPARTMENT_STORE_NOT_FOUND)
            );

            if (departmentStore.getLineStorages().isEmpty()  && departmentStore.getStockMovements().isEmpty()){
                repository.deleteById(departmentStoreId);
            }else {
                throw new InvalidOperationException(String.format("Magasin avec ID {%s} est utilisé",departmentStoreId), ErrorCodes.DEPARTMENT_STORE_ALREADY_IN_USE);
            }

        }else {
            throw new InvalidEntityException("Identifiant incorrect pour le magasin", ErrorCodes.DEPARTMENT_STORE_NOT_VALID);
        }
    }
}
