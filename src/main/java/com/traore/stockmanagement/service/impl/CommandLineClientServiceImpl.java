package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.CommandLineClient.CommandLineClientDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.mapper.CommandLineClientMapper;;
import com.traore.stockmanagement.model.CommandLineClient;
import com.traore.stockmanagement.model.Product;
import com.traore.stockmanagement.repository.CommandClientRepo;
import com.traore.stockmanagement.repository.CommandLineClientRepo;
import com.traore.stockmanagement.repository.ProductRepo;
import com.traore.stockmanagement.service.CommandLineClientService;
import com.traore.stockmanagement.validator.CommandLineClientValidator;
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
public class CommandLineClientServiceImpl implements CommandLineClientService {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineClientServiceImpl.class);
    private CommandLineClientRepo repository;
    private CommandLineClientMapper mapper;
    private CommandClientRepo commandClientRepo;
    private ProductRepo productRepo;


    @Override
    public CommandLineClientDTO save(CommandLineClientDTO dto) {
        List<String> errors = CommandLineClientValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Command line client dto {} is not valid",dto);
            throw new InvalidEntityException("Ligne de commande client invalide", ErrorCodes.COMMAND_LINE_CLIENT_NOT_VALID, errors);
        }

        if (!commandClientRepo.existsById(dto.getCommandClient().getId())){
            throw new EntityNotFoundException(String.format("Commande client avec ID {%s} non trouvée", dto.getCommandClient().getId()), ErrorCodes.COMMAND_CLIENT_NOT_FOUND);
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
    public List<CommandLineClientDTO> save(List<CommandLineClientDTO> dtos) {
        if (dtos!=null && !dtos.isEmpty()){
            List<CommandLineClientDTO> resultsDTO = new ArrayList<>();
            for (CommandLineClientDTO dto : dtos){
                resultsDTO.add(
                        save(dto)
                );
            }
            return resultsDTO;
        }
        throw new InvalidEntityException("Liste de lignes de commande client invalide", ErrorCodes.COMMAND_LINE_CLIENT_NOT_VALID);
    }

    @Override
    public CommandLineClientDTO update(CommandLineClientDTO dto) {
        List<String> errors = CommandLineClientValidator.updateValidation(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Command line client dto {} is not valid",dto);
            throw new InvalidEntityException("Ligne de commande client invalide", ErrorCodes.COMMAND_LINE_CLIENT_NOT_VALID, errors);
        }
        CommandLineClient commandLine = repository.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Ligne de commande client avec ID {%s} non trouvée", dto.getId()), ErrorCodes.COMMAND_LINE_CLIENT_NOT_FOUND)
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
    public void delete(String commandLineClientId) {
        if (commandLineClientId!=null && !commandLineClientId.isBlank()){
            boolean isCommandLineExists = repository.existsById(commandLineClientId);
            if (isCommandLineExists){
                repository.deleteById(commandLineClientId);
            }else {
                throw new EntityNotFoundException(String.format("Ligne de commande client avec id {%s} non trouvée",commandLineClientId), ErrorCodes.COMMAND_LINE_CLIENT_NOT_FOUND);
            }
        }else {
            throw new InvalidEntityException("Identifiant de ligne de commande client invalide", ErrorCodes.COMMAND_LINE_CLIENT_NOT_VALID);
        }
    }
}
