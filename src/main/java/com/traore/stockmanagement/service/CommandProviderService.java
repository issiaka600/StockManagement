package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.commandProvider.CommandProviderDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderOnlyDTO;

public interface CommandProviderService {

    CommandProviderDTO save(CommandProviderDTO dto);
    CommandProviderDTO update(CommandProviderOnlyDTO dto);
    void delete(String commandProviderId);
}
