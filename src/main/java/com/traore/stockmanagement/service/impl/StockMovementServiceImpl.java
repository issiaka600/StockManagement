package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.lineStorage.LineStorageDTO;
import com.traore.stockmanagement.dto.stockMovement.StockMovementDTO;
import com.traore.stockmanagement.enums.MovementType;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.mapper.StockMovementMapper;
import com.traore.stockmanagement.model.StockMovement;
import com.traore.stockmanagement.repository.DepartmentStoreRepo;
import com.traore.stockmanagement.repository.ProductRepo;
import com.traore.stockmanagement.repository.StockMovementRepo;
import com.traore.stockmanagement.service.LineStorageService;
import com.traore.stockmanagement.service.StockMovementService;
import com.traore.stockmanagement.validator.StockMovementValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StockMovementServiceImpl implements StockMovementService {
    private static final Logger logger = LoggerFactory.getLogger(StockMovementServiceImpl.class);
    private StockMovementRepo repository;
    private StockMovementMapper mapper;
    private LineStorageService lineStorageService;
    private DepartmentStoreRepo departmentStoreRepo;
    private ProductRepo productRepo;

    @Override
    public StockMovementDTO save(StockMovementDTO dto) {
        List<String> errors = StockMovementValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Stock movement dto {} invalid", dto);
            throw new InvalidEntityException("Mouvement de stock invalide", ErrorCodes.STOCK_MOVEMENT_NOT_VALID, errors);
        }

        if (!departmentStoreRepo.existsById(dto.getDepartmentStore().getId())){
            throw new EntityNotFoundException(String.format("Magasin avec ID {%s} non trouvé", dto.getDepartmentStore().getId()), ErrorCodes.DEPARTMENT_STORE_NOT_FOUND);
        }
        if (!productRepo.existsById(dto.getProduct().getId())){
            throw new EntityNotFoundException(String.format("Produit avec ID {%s} non trouvé", dto.getProduct().getId()), ErrorCodes.PRODUCT_NOT_FOUND);
        }

        LineStorageDTO lineStorageDTO = null;
        try {
            lineStorageDTO= lineStorageService.getLineStorage(
                    dto.getProduct().getId(),dto.getDepartmentStore().getId()
            );
            switch (dto.getType()){
                case SALE,CLIENT,MANAGER_OUTPUT ->{
                    if (lineStorageDTO.getQuantity().compareTo(dto.getQuantity())>=0){
                       lineStorageDTO.setQuantity(
                               lineStorageDTO.getQuantity().subtract(dto.getQuantity())
                       );

                    }else {
                        throw new InvalidEntityException(String.format("Erreur : la quantité de produit dans ce magasin {%s} est inférieure à la quantité de retrait {%s}",
                                lineStorageDTO.getQuantity(),dto.getQuantity()), ErrorCodes.STOCK_MOVEMENT_NOT_VALID);
                    }
                }
                case PROVIDER,MANAGER_INPUT -> lineStorageDTO.setQuantity(
                            lineStorageDTO.getQuantity().add(
                            dto.getQuantity()
                    ));

            }
            lineStorageDTO = lineStorageService.update(lineStorageDTO.getId(),lineStorageDTO.getQuantity());

        }catch (EntityNotFoundException exception){
            if (exception.getErrorCode().getCode() == ErrorCodes.LINE_STORAGE_NOT_FOUND.getCode()){
                logger.warn("line storage dto1 : {}", lineStorageDTO);
                // Line storage doesn't exist.
                if (dto.getType().equals(MovementType.PROVIDER) ||
                        dto.getType().equals(MovementType.MANAGER_INPUT)){
                    logger.warn("entered");
                    lineStorageDTO = LineStorageDTO.builder()
                            .departmentStore(dto.getDepartmentStore())
                            .product(dto.getProduct())
                            .quantity(dto.getQuantity())
                            .build();
                    lineStorageDTO = lineStorageService.save(lineStorageDTO);
                    logger.warn("line storage dto2 : {}", lineStorageDTO);
                }else {
                    throw new EntityNotFoundException(String.format("Line storage doesn't exists for product with id {%s} in department store id {%s}",
                            dto.getProduct().getId(), dto.getDepartmentStore().getId()), ErrorCodes.LINE_STORAGE_NOT_FOUND);
                }
            }

        }

        logger.warn("line storage dto3 : {}", lineStorageDTO);
        if (lineStorageDTO != null){
            logger.warn("line storage dto4 : {}", lineStorageDTO);
            StockMovement movement = repository.save(
                    mapper.toEntity(dto)
            );

            logger.warn("movement 1 : {}", movement);

            return mapper.fromEntity(movement);


        }else {
            logger.error("Please check stock movement dto {}", dto);
            throw new InvalidOperationException("Error inserting stock movement...", ErrorCodes.STOCK_MOVEMENT_NOT_VALID);
        }
    }

    @Override
    public void delete(String stockMovementId) {
        if (stockMovementId.isEmpty() || stockMovementId.isBlank()){
            throw new InvalidEntityException("Identifiant de mouvement de stock invalid", ErrorCodes.STOCK_MOVEMENT_NOT_VALID);
        }
        boolean isStockMovementExists = repository.existsById(stockMovementId);
        if (isStockMovementExists){
            repository.deleteById(stockMovementId);
        }else{
            throw new EntityNotFoundException(String.format("Mouvement de stock avec ID {%s} non trouvé",stockMovementId), ErrorCodes.SALE_NOT_FOUND);
        }
    }
}
