package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.roles.RolesDTO;

import java.util.List;

public interface RolesService {
    RolesDTO save(RolesDTO dto);
    RolesDTO update(RolesDTO rolesDTO);
    List<RolesDTO> listRoles();
    void delete(Integer roleId);
}
