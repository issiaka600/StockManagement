package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.CommandClient.CommandClientDTO;
import com.traore.stockmanagement.dto.CommandClient.CommandClientOnlyDTO;
import com.traore.stockmanagement.dto.CommandLineClient.CommandLineClientDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.mapper.CommandClientMapper;
import com.traore.stockmanagement.model.CommandClient;
import com.traore.stockmanagement.repository.ClientRepo;
import com.traore.stockmanagement.repository.CommandClientRepo;
import com.traore.stockmanagement.repository.UserRepo;
import com.traore.stockmanagement.service.CommandClientService;
import com.traore.stockmanagement.service.CommandLineClientService;
import com.traore.stockmanagement.validator.CommandClientValidator;
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

public class CommandClientServiceImpl implements CommandClientService {
    private static final Logger logger = LoggerFactory.getLogger(CommandClientServiceImpl.class);
    private CommandClientRepo repository;
    private ClientRepo clientRepo;
    private CommandClientMapper mapper;
    private CommandLineClientService lineService;
    private UserRepo userRepo;


    @Override
    public CommandClientDTO saveInCascade(CommandClientDTO dto) {
        List<String> errors = CommandClientValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Command client dto {} is not valid", dto);
            throw new InvalidEntityException("Commande client invalide", ErrorCodes.COMMAND_CLIENT_NOT_VALID,errors);
        }
        if (!clientRepo.existsById(dto.getClient().getId())){
            throw new EntityNotFoundException(String.format("Client with dto %s not found in database", dto.getClient()), ErrorCodes.CLIENT_NOT_FOUND);
        }
        if (!userRepo.existsById(dto.getUser().getId())){
            throw new EntityNotFoundException(String.format("User with dto %s not found in database", dto.getUser()), ErrorCodes.USER_NOT_FOUND);
        }

        CommandClient command = repository.save(
                mapper.toEntity(dto)
        );

        return mapper.fromEntity(command);
    }

    @Override
    public CommandClientDTO save(CommandClientDTO dto) {
        List<String> errors = CommandClientValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Command client dto {} is not valid", dto);
            throw new InvalidEntityException("Commande client invalide", ErrorCodes.COMMAND_CLIENT_NOT_VALID, errors);
        }
        if (!clientRepo.existsById(dto.getClient().getId())){
            throw new EntityNotFoundException(String.format("Client with dto %s not found in database", dto.getClient()), ErrorCodes.CLIENT_NOT_FOUND);
        }
        if (!userRepo.existsById(dto.getUser().getId())){
            throw new EntityNotFoundException(String.format("User with dto %s not found in database", dto.getUser()), ErrorCodes.USER_NOT_FOUND);
        }

        CommandClient command = repository.save(
                mapper.toEntity(dto)
        );

        //Injecting new command id to commmand lines dto

        List<CommandLineClientDTO> updatedDTOList = new ArrayList<>();
        for (CommandLineClientDTO lineDto : dto.getCommandLineClients()){
            lineDto.setCommandClient(
                    CommandClientMapper.fromEntityToCommandClientOnlyDTO(
                            command
                    )
            );
            updatedDTOList.add(lineDto);
        }

        int linesNumber = updatedDTOList.size();
        //Inserting commandLines
        updatedDTOList = lineService.save(updatedDTOList);

        if (!updatedDTOList.isEmpty() && updatedDTOList.size()==linesNumber){
            CommandClient refreshedCommand = repository.findById(command.getId()).orElseThrow(
                    ()->new InvalidOperationException("Echec de création d'une nouvelle commande client")
            );

            return CommandClientMapper.fromEntityStatic(
                    refreshedCommand
            );
        }
        throw new InvalidEntityException(String.format("Command client dto {%s} invalide",dto), ErrorCodes.COMMAND_CLIENT_NOT_VALID);
    }

    @Override
    public CommandClientDTO update(CommandClientOnlyDTO dto) {
        List<String> errors = CommandClientValidator.updateValidation(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Command only client dto {} is not valid", dto);
            throw new InvalidEntityException("Commande client invalide", ErrorCodes.COMMAND_CLIENT_NOT_VALID, errors);
        }
        CommandClient commandClient = repository.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Command client with id {%s} not found",dto.getId()), ErrorCodes.COMMAND_CLIENT_NOT_FOUND)
        );
        if (dto.getStatus()!= null){
            commandClient.setStatus(dto.getStatus());
        }
        if (dto.getDate()!=null){
            commandClient.setDate(dto.getDate());
        }
        return CommandClientMapper.fromEntityStatic(
                repository.save(commandClient)
        );
    }

    @Override
    public void delete(String commandClientId) {
        if (commandClientId != null && !commandClientId.isBlank()){
            boolean isCommandExists = repository.existsById(commandClientId);
            if (isCommandExists){
                repository.deleteById(commandClientId);
            }else {
                throw new EntityNotFoundException(String.format("Commande client avec ID {%s} non trouvée",commandClientId), ErrorCodes.COMMAND_CLIENT_NOT_FOUND);
            }
        }else {
            throw new InvalidEntityException("Invalid ID for getting command client", ErrorCodes.COMMAND_CLIENT_NOT_VALID);
        }

    }
}
