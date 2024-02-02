package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.saleLine.SaleLineDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.mapper.SaleLineMapper;
import com.traore.stockmanagement.model.SaleLine;
import com.traore.stockmanagement.repository.ProductRepo;
import com.traore.stockmanagement.repository.SaleLineRepo;
import com.traore.stockmanagement.repository.SaleRepo;
import com.traore.stockmanagement.service.SaleLineService;
import com.traore.stockmanagement.validator.SaleLineValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SaleLineServiceImpl implements SaleLineService {
    private static final Logger logger = LoggerFactory.getLogger(SaleLineServiceImpl.class);
    private SaleLineRepo repository;
    private SaleLineMapper mapper;
    private SaleRepo saleRepo;
    private ProductRepo productRepo;


    @Override
    public SaleLineDTO save(SaleLineDTO dto) {
        List<String> errors = SaleLineValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Sale line dto {} is not valid", dto);
            throw new InvalidEntityException("Ligne de vente invalide", ErrorCodes.SALE_LINE_NOT_VALID, errors);
        }
        if (!saleRepo.existsById(dto.getSale().getId())){
            throw new EntityNotFoundException(String.format("Vente avec ID {%s} non trouvée", dto.getSale().getId()), ErrorCodes.SALE_NOT_FOUND);
        }
        if (!productRepo.existsById(dto.getProduct().getId())){
            throw new EntityNotFoundException(String.format("Produit avec ID {%s} non trouvé", dto.getProduct().getId()), ErrorCodes.PRODUCT_NOT_FOUND);
        }

        return mapper.fromEntity(
                repository.save(
                        mapper.toEntity(dto)
                )
        );
    }

    @Override
    public List<SaleLineDTO> save(List<SaleLineDTO> dtos) {
        if (dtos!=null && !dtos.isEmpty()){
            List<SaleLineDTO> resultsDTO = new ArrayList<>();
            for (SaleLineDTO dto : dtos){
                resultsDTO.add(
                        save(dto)
                );
            }
            return resultsDTO;
        }
        throw new InvalidEntityException("Liste de lignes de vente invalide", ErrorCodes.SALE_LINE_NOT_VALID);
    }

    @Override
    public SaleLineDTO update(SaleLineDTO dto) {
        List<String> errors = SaleLineValidator.updateValidation(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Sale line dto {} is not valid", dto);
            throw new InvalidEntityException("Ligne de vente invalide", ErrorCodes.SALE_LINE_NOT_VALID, errors);
        }
        SaleLine saleLine = repository.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Vente avec ID {%s} non trouvée",dto.getId()), ErrorCodes.SALE_LINE_NOT_FOUND)
        );

        if (dto.getUnitPrice().compareTo(BigDecimal.ZERO)>0){
            saleLine.setUnitPrice(dto.getUnitPrice());
        }

        if (dto.getQuantity().compareTo(BigDecimal.ZERO)>0){
            saleLine.setQuantity(dto.getQuantity());
        }

        return mapper.fromEntity(
                repository.save(saleLine)
        );
    }

    @Override
    public void delete(String saleLineId) {
        if (saleLineId!=null && !saleLineId.isBlank()){
            boolean isSaleLineExists = repository.existsById(saleLineId);
            if (isSaleLineExists){
                repository.deleteById(saleLineId);
            }else {
                throw new EntityNotFoundException(String.format("Ligne de vente avec ID {%s} non trouvée",saleLineId), ErrorCodes.SALE_LINE_NOT_FOUND);
            }
        }else {
            throw new InvalidEntityException("Identifiant de ligne de vente invalide", ErrorCodes.SALE_LINE_NOT_VALID);
        }
    }
}
