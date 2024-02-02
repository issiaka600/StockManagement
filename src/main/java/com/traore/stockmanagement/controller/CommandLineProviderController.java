package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.CommandLineProviderAPI;
import com.traore.stockmanagement.dto.commandLineProvider.CommandLineProviderDTO;
import com.traore.stockmanagement.service.CommandLineProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommandLineProviderController implements CommandLineProviderAPI {
    private CommandLineProviderService service;

    @Override
    public CommandLineProviderDTO save(CommandLineProviderDTO dto) {
        return service.save(dto);
    }

    @Override
    public CommandLineProviderDTO update(CommandLineProviderDTO dto) {
        return service.update(dto);
    }

    @Override
    public void delete(String commandLineProviderId) {
        service.delete(commandLineProviderId);
    }
}
