package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.CommandLineClient.CommandLineClientDTO;
import com.traore.stockmanagement.dto.commandLineProvider.CommandLineProviderDTO;

import java.util.List;

public interface CommandLineClientService {
    CommandLineClientDTO save(CommandLineClientDTO dto);
    List<CommandLineClientDTO> save(List<CommandLineClientDTO> dtos);
    CommandLineClientDTO update(CommandLineClientDTO dto);
    void delete(String commandLineClientId);
}
