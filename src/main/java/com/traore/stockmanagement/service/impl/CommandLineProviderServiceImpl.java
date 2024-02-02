package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.commandLineProvider.CommandLineProviderDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.mapper.CommandLineProviderMapper;
import com.traore.stockmanagement.model.CommandLineProvider;
import com.traore.stockmanagement.repository.CommandLineProviderRepo;
import com.traore.stockmanagement.repository.CommandProviderRepo;
import com.traore.stockmanagement.repository.ProductRepo;
import com.traore.stockmanagement.service.CommandLineProviderService;
import com.traore.stockmanagement.validator.CommandLineProviderValidator;
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
public class CommandLineProviderServiceImpl implements CommandLineProviderService {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineProviderServiceImpl.class);
    private CommandLineProviderRepo repository;
    private CommandLineProviderMapper mapper;
    private CommandProviderRepo commandProviderRepo;
    private ProductRepo productRepo;


    @Override
    public CommandLineProviderDTO save(CommandLineProviderDTO dto) {
        List<String> errors = CommandLineProviderValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Command line provider dto {} is not valid",dto);
            throw new InvalidEntityException("Ligne de commande fournisseur invalide", ErrorCodes.COMMAND_LINE_PROVIDER_NOT_VALID, errors);
        }
        if (!commandProviderRepo.existsById(dto.getCommandProvider().getId())){
            throw new EntityNotFoundException(String.format("Commande fournisseur avec ID {%s} non trouvée", dto.getCommandProvider().getId()), ErrorCodes.COMMAND_PROVIDER_NOT_FOUND);
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
    public List<CommandLineProviderDTO> save(List<CommandLineProviderDTO> dtos) {
        if (dtos!=null && !dtos.isEmpty()){
            List<CommandLineProviderDTO> resultsDTO = new ArrayList<>();
            for (CommandLineProviderDTO dto : dtos){
                resultsDTO.add(
                        save(dto)
                );
            }
            return resultsDTO;
        }
        throw new InvalidEntityException("Liste de lignes de commande fournisseur invalide", ErrorCodes.COMMAND_LINE_PROVIDER_NOT_VALID);
    }

    @Override
    public CommandLineProviderDTO update(CommandLineProviderDTO dto) {
        List<String> errors = CommandLineProviderValidator.updateValidation(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Command line provider dto {} is not valid",dto);
            throw new InvalidEntityException("Ligne de commande fournisseur invalide", ErrorCodes.COMMAND_LINE_PROVIDER_NOT_VALID, errors);
        }
        CommandLineProvider commandLine = repository.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Command line provider with id {%s} not found", dto.getId()), ErrorCodes.COMMAND_LINE_PROVIDER_NOT_FOUND)
        );

        if (dto.getUnitPrice().compareTo(BigDecimal.ZERO)>0){
            commandLine.setUnitPrice(dto.getUnitPrice());
        }

        if (dto.getQuantity().compareTo(BigDecimal.ZERO)>0){
            commandLine.setQuantity(dto.getQuantity());
        }

        return mapper.fromEntity(
                repository.save(commandLine)
        );
    }

    @Override
    public void delete(String commandLineProviderId) {
        if (commandLineProviderId!=null && !commandLineProviderId.isBlank()){
            boolean isCommandLineExists = repository.existsById(commandLineProviderId);
            if (isCommandLineExists){
                repository.deleteById(commandLineProviderId);
            }else {
                throw new EntityNotFoundException(String.format("Ligne de commande fournisseur avec id {%s} non trouvée",commandLineProviderId), ErrorCodes.COMMAND_LINE_CLIENT_NOT_FOUND);
            }
        }else {
            throw new InvalidEntityException("Identifiant de ligne de commande client invalid", ErrorCodes.COMMAND_LINE_PROVIDER_NOT_VALID);
        }
    }
}
