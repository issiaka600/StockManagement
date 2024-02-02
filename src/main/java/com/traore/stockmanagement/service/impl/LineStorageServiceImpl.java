package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.lineStorage.LineStorageDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.mapper.LineStorageMapper;
import com.traore.stockmanagement.model.LineStorage;
import com.traore.stockmanagement.repository.DepartmentStoreRepo;
import com.traore.stockmanagement.repository.LineStorageRepo;
import com.traore.stockmanagement.repository.ProductRepo;
import com.traore.stockmanagement.service.LineStorageService;
import com.traore.stockmanagement.validator.LineStorageValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class LineStorageServiceImpl implements LineStorageService {
    private static final Logger logger = LoggerFactory.getLogger(LineStorageServiceImpl.class);
    private LineStorageRepo repository;
    private LineStorageMapper mapper;
    private DepartmentStoreRepo departmentStoreRepo;
    private ProductRepo productRepo;


    @Override
    public LineStorageDTO save(LineStorageDTO dto) {
        List<String> errors = LineStorageValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Line Storage dto {} invalid",dto);
            throw new InvalidEntityException("Ligne de stockage invalide", ErrorCodes.LINE_STORAGE_NOT_VALID, errors);
        }
        if (!departmentStoreRepo.existsById(dto.getDepartmentStore().getId())){
            throw new EntityNotFoundException(String.format("Magasing avec ID {%s} non trouvé", dto.getDepartmentStore().getId()), ErrorCodes.DEPARTMENT_STORE_NOT_FOUND);
        }
        if (!productRepo.existsById(dto.getProduct().getId())){
            throw new EntityNotFoundException(String.format("Produit avec ID {%s} non trouvé", dto.getProduct().getId()), ErrorCodes.PRODUCT_NOT_FOUND);
        }


        LineStorage existingLineStorage = repository.findLineStorageByDepartmentStore_IdAndProduct_Id(
                dto.getDepartmentStore().getId(), dto.getProduct().getId()
        ).orElse(null);

        if (existingLineStorage != null){
            throw new InvalidEntityException(String.format("Une ligne de stockage avec ID {%s} existe pour ce produit {%s} dans ce magasin {%s}",
                    existingLineStorage.getId(), existingLineStorage.getProduct().getName(),existingLineStorage.getDepartmentStore().getName()), ErrorCodes.LINE_STORAGE_ALREADY_EXISTS);
        }

        return mapper.fromEntity(
                repository.save(
                        mapper.toEntity(dto)
                )
        );
    }

    @Override
    public LineStorageDTO update(String id, BigDecimal quantity) {
        List<String> errors = LineStorageValidator.updateValidation(id,quantity);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Invalid quantity for line Storage : {}", quantity);
            throw new InvalidEntityException("Ligne de stockage invalide", ErrorCodes.LINE_STORAGE_NOT_VALID, errors);
        }
        LineStorage lineStorage = repository.findById(id).orElseThrow(
                ()->new EntityNotFoundException(String.format("Ligne de stockage avec ID {%s} non trouvée", id), ErrorCodes.LINE_STORAGE_NOT_FOUND)
        );
        lineStorage.setQuantity(quantity);

        return mapper.fromEntity(
                repository.save(
                        lineStorage
                )
        );

    }
    @Override
    public LineStorageDTO getLineStorage(String productId, Long departmentStore) {
        LineStorage lineStorage = repository.findLineStorageByDepartmentStore_IdAndProduct_Id(
                departmentStore,productId
        ).orElseThrow(
                ()->new EntityNotFoundException(String.format("Aucune ligne de stockage trouvée pour ce produit avec ID {%s} dans le magasin avec ID {%s}",
                        productId, departmentStore), ErrorCodes.LINE_STORAGE_NOT_FOUND)
        );

        return mapper.fromEntity(lineStorage);
    }

    @Override
    public void delete(String lineStorageId) {
        if (lineStorageId.isEmpty() || lineStorageId.isBlank()){
            throw new InvalidEntityException("Identifiant de ligne de stockage invalide", ErrorCodes.LINE_STORAGE_NOT_VALID);
        }
        boolean isLineStorageExists = repository.existsById(lineStorageId);
        if (isLineStorageExists){
            repository.deleteById(lineStorageId);
        }else{
            throw new EntityNotFoundException(String.format("Ligne de stockage avec ID {%s} non trouvée", lineStorageId), ErrorCodes.LINE_STORAGE_NOT_FOUND);
        }
    }
}
