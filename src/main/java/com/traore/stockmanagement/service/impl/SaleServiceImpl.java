package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.sale.SaleDTO;
import com.traore.stockmanagement.dto.sale.SaleOnlyDTO;
import com.traore.stockmanagement.dto.saleLine.SaleLineDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.mapper.SaleMapper;
import com.traore.stockmanagement.model.Sale;
import com.traore.stockmanagement.repository.EnterpriseRepo;
import com.traore.stockmanagement.repository.SaleRepo;
import com.traore.stockmanagement.repository.UserRepo;
import com.traore.stockmanagement.service.SaleLineService;
import com.traore.stockmanagement.service.SaleService;
import com.traore.stockmanagement.validator.SaleValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {
    private static final Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);
    private SaleRepo repository;
    private SaleMapper mapper;
    private EnterpriseRepo enterpriseRepo;
    private SaleLineService lineService;
    private UserRepo userRepo;

    @Override
    public SaleDTO save(SaleDTO dto) {
        List<String> errors = SaleValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Sale dto {} is not valid", dto);
            throw new InvalidEntityException("Vente invalide", ErrorCodes.SALE_NOT_VALID,errors);
        }

        if (!userRepo.existsById(dto.getUser().getId())){
            throw new EntityNotFoundException(String.format("Utilisateur avec ID {%s} non trouvé", dto.getUser().getId()), ErrorCodes.USER_NOT_FOUND);
        }

        Sale sale = repository.save(
                mapper.toEntity(dto)
        );

        //Injecting new sale id to sale lines dto

        List<SaleLineDTO> updatedDTOList = new ArrayList<>();
        for (SaleLineDTO lineDto : dto.getSaleLines()){
            lineDto.setSale(
                    SaleMapper.fromEntityToSaleOnlyDTO(
                            sale
                    )
            );
            updatedDTOList.add(lineDto);
        }

        int linesNumber = updatedDTOList.size();
        //Inserting sale lines
        updatedDTOList = lineService.save(updatedDTOList);

        if (!updatedDTOList.isEmpty() && updatedDTOList.size()==linesNumber){
            Sale refreshedSale = repository.findById(sale.getId()).orElseThrow(
                    ()->new InvalidOperationException("Echec de création d'une nouvelle vente")
            );

            return mapper.fromEntityToSaleDTO(refreshedSale);
        }
        throw new InvalidEntityException(String.format("Vente dto {%s} invalide",dto), ErrorCodes.SALE_NOT_VALID);
    }

    @Override
    public SaleDTO update(SaleOnlyDTO dto) {
        List<String> errors = SaleValidator.updateValidation(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Sale only dto {} is not valid", dto);
            throw new InvalidEntityException("Vente invalide", ErrorCodes.SALE_NOT_VALID, errors);
        }
        Sale sale = repository.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Vente avec ID {%s} non trouvée",dto.getId()), ErrorCodes.SALE_NOT_FOUND)
        );

        if (dto.getStatus()!= null){
            sale.setStatus(dto.getStatus());
        }
        if (dto.getDate()!=null){
            sale.setDate(dto.getDate());
        }

        return mapper.fromEntityToSaleDTO(
                repository.save(sale)
        );
    }

    @Override
    public SaleDTO getSale(String saleId) {
        if (saleId != null && !saleId.isBlank()){
            Sale sale = repository.findById(saleId).orElseThrow(
                    ()->new EntityNotFoundException(String.format("Vente avec ID {%s} non trouvée", saleId), ErrorCodes.SALE_LINE_NOT_FOUND)
            );


            return mapper.fromEntityToSaleDTO(sale);
        }else {
            throw new InvalidEntityException("Identifiant invalide pour vente", ErrorCodes.SALE_NOT_VALID);
        }
    }

    @Override
    public List<SaleDTO> getSales(Long enterpriseId) {
        if (enterpriseId!=null){
            if (enterpriseRepo.existsById(enterpriseId)){
                return mapper.fromEntityToSaleDTO(
                        repository.findSalesByUser_Enterprise_IdOrderByDateDesc(enterpriseId)
                );

            }else {
                throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée",enterpriseId), ErrorCodes.ENTERPRISE_NOT_FOUND);
            }
        }else {
            throw new InvalidEntityException("Identifiant invalide pour entreprise", ErrorCodes.ENTERPRISE_NOT_VALID);
        }

    }

    @Override
    public void delete(String saleId) {
        if (saleId != null && !saleId.isBlank()){
            boolean isSaleExists = repository.existsById(saleId);
            if (isSaleExists){
                repository.deleteById(saleId);
            }else {
                throw new EntityNotFoundException(String.format("Vente avec ID {%s} non trouvée",saleId), ErrorCodes.SALE_NOT_FOUND);
            }
        }else {
            throw new InvalidEntityException("Identifiant invalide pour vente", ErrorCodes.SALE_NOT_VALID);
        }
    }
}
