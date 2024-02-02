package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.CommandClientAPI;
import com.traore.stockmanagement.dto.CommandClient.CommandClientDTO;
import com.traore.stockmanagement.dto.CommandClient.CommandClientOnlyDTO;
import com.traore.stockmanagement.service.CommandClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommandClientController implements CommandClientAPI {
    private CommandClientService service;

    @Override
    public CommandClientDTO save(CommandClientDTO dto) {
        return service.save(dto);
    }

    @Override
    public CommandClientDTO update(CommandClientOnlyDTO dto) {
        return service.update(dto);
    }

    @Override
    public void delete(String commandClientId) {
        service.delete(commandClientId);
    }
}
