package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.RolesAPI;
import com.traore.stockmanagement.dto.roles.RolesDTO;
import com.traore.stockmanagement.service.RolesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RolesController implements RolesAPI {
    private RolesService rolesService;

    @Override
    public RolesDTO save(RolesDTO dto) {
        return rolesService.save(dto);
    }

    @Override
    public RolesDTO update(RolesDTO rolesDTO) {
        return rolesService.update(rolesDTO);
    }

    @Override
    public void delete(Integer roleId) {
        rolesService.delete(roleId);
    }
}
