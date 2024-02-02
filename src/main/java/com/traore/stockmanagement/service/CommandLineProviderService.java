package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.commandLineProvider.CommandLineProviderDTO;

import java.util.List;

public interface CommandLineProviderService {
    CommandLineProviderDTO save(CommandLineProviderDTO dto);
    List<CommandLineProviderDTO> save(List<CommandLineProviderDTO> dtos);
    CommandLineProviderDTO update(CommandLineProviderDTO dto);
    void delete(String commandLineProviderId);
}
