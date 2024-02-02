package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.CommandProviderAPI;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderOnlyDTO;
import com.traore.stockmanagement.service.CommandProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommandProviderController implements CommandProviderAPI {
    private CommandProviderService service;

    @Override
    public CommandProviderDTO save(CommandProviderDTO dto) {
        return service.save(dto);
    }

    @Override
    public CommandProviderDTO update(CommandProviderOnlyDTO dto) {
        return service.update(dto);
    }

    @Override
    public void delete(String commandProviderId) {
        service.delete(commandProviderId);
    }
}
