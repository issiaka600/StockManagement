package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.commandLineProvider.CommandLineProviderDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderOnlyDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.mapper.CommandProviderMapper;
import com.traore.stockmanagement.model.CommandProvider;
import com.traore.stockmanagement.repository.CommandProviderRepo;
import com.traore.stockmanagement.repository.ProviderRepo;
import com.traore.stockmanagement.repository.UserRepo;
import com.traore.stockmanagement.service.CommandLineProviderService;
import com.traore.stockmanagement.service.CommandProviderService;
import com.traore.stockmanagement.validator.CommandProviderValidator;
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
public class CommandProviderServiceImpl implements CommandProviderService {
    private static final Logger logger = LoggerFactory.getLogger(CommandProviderServiceImpl.class);
    private CommandProviderRepo repository;
    private CommandProviderMapper mapper;
    private CommandLineProviderService lineService;
    private ProviderRepo providerRepo;
    private UserRepo userRepo;

    @Override
    public CommandProviderDTO save(CommandProviderDTO dto) {
        List<String> errors = CommandProviderValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Command provider dto {} is not valid", dto);
            throw new InvalidEntityException("Commande fournisseur invalide", ErrorCodes.COMMAND_PROVIDER_NOT_VALID, errors);
        }
        if (!providerRepo.existsById(dto.getProvider().getId())){
            throw new EntityNotFoundException(String.format("Provider with dto %s not found in database", dto.getProvider()), ErrorCodes.PROVIDER_NOT_FOUND);
        }
        if (!userRepo.existsById(dto.getUser().getId())){
            throw new EntityNotFoundException(String.format("User with dto %s not found in database", dto.getUser()), ErrorCodes.USER_NOT_FOUND);
        }

        CommandProvider command = repository.save(
                mapper.toEntity(dto)
        );

        //Injecting new command id to commmand lines dto

        List<CommandLineProviderDTO> updatedDTOList = new ArrayList<>();
        for (CommandLineProviderDTO lineDto : dto.getCommandLineProviders()){
;            lineDto.setCommandProvider(
                    CommandProviderMapper.fromEntityToCommandProviderOnlyDTO(
                            command
                    )
            );
            updatedDTOList.add(lineDto);
        }
        //Inserting commandLines
        updatedDTOList = lineService.save(updatedDTOList);

        if (!updatedDTOList.isEmpty()){
            CommandProvider refreshedCommand = repository.findById(command.getId()).orElseThrow(
                    ()->new InvalidOperationException("Echec de création d'une nouvelle commande fournisseur")
            );

            return CommandProviderMapper.fromEntity(
                    refreshedCommand
            );
        }
        throw new InvalidEntityException(String.format("Commande fournisseur dto {%s} invalide",dto), ErrorCodes.COMMAND_PROVIDER_NOT_VALID);
    }

    @Override
    public CommandProviderDTO update(CommandProviderOnlyDTO dto) {
        List<String> errors = CommandProviderValidator.updateValidation(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Command only provider dto {} is not valid", dto);
            throw new InvalidEntityException("Commande fournisseur invalide", ErrorCodes.COMMAND_PROVIDER_NOT_VALID, errors);
        }

        CommandProvider commandProvider = repository.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Commande fournisseur avec ID {%s} non trouvée",dto.getId()), ErrorCodes.COMMAND_PROVIDER_NOT_FOUND)
        );
        if (dto.getStatus()!= null){
            commandProvider.setStatus(dto.getStatus());
        }
        if (dto.getDate()!=null){
            commandProvider.setDate(dto.getDate());
        }
        return CommandProviderMapper.fromEntity(
                repository.save(commandProvider)
        );
    }

    @Override
    public void delete(String commandProviderId) {
        if (commandProviderId != null && !commandProviderId.isBlank()){
            boolean isCommandExists = repository.existsById(commandProviderId);
            if (isCommandExists){
                repository.deleteById(commandProviderId);
            }else {
                throw new EntityNotFoundException(String.format("Command provider with id {%s} not found",commandProviderId), ErrorCodes.COMMAND_PROVIDER_NOT_FOUND);
            }
        }else {
            throw new InvalidEntityException("Identifiant de commande fournisseur non valide", ErrorCodes.COMMAND_PROVIDER_NOT_VALID);
        }

    }
}
