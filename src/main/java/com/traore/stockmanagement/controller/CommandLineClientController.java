package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.CommandLineClientAPI;
import com.traore.stockmanagement.dto.CommandLineClient.CommandLineClientDTO;
import com.traore.stockmanagement.service.CommandLineClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class CommandLineClientController implements CommandLineClientAPI {
    private CommandLineClientService service;

    @Override
    public CommandLineClientDTO save(CommandLineClientDTO dto) {
        return service.save(dto);
    }

    @Override
    public CommandLineClientDTO update(CommandLineClientDTO dto) {
        return service.update(dto);
    }

    @Override
    public void delete(String commandLineClientId) {
        service.delete(commandLineClientId);
    }
}
