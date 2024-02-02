package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.CommandClient.CommandClientDTO;
import com.traore.stockmanagement.dto.CommandClient.CommandClientOnlyDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderDTO;

public interface CommandClientService {
    CommandClientDTO saveInCascade(CommandClientDTO dto);
    CommandClientDTO save(CommandClientDTO dto);
    CommandClientDTO update(CommandClientOnlyDTO dto);
    void delete(String commandClientId);
}
