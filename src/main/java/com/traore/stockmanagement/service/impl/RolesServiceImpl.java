package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.roles.RolesDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.mapper.RolesMapper;
import com.traore.stockmanagement.model.Roles;
import com.traore.stockmanagement.repository.RolesRepo;
import com.traore.stockmanagement.repository.UserRepo;
import com.traore.stockmanagement.service.RolesService;
import com.traore.stockmanagement.validator.RolesValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RolesServiceImpl implements RolesService {
    private RolesRepo rolesRepo;
    private RolesMapper rolesMapper;
    private UserRepo userRepo;

    @Override
    public RolesDTO save(RolesDTO dto) {
        List<String> errors = RolesValidator.validate(dto);
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Role incorrect", ErrorCodes.ROLES_NOT_VALID, errors);
        }
        if (!userRepo.existsById(dto.getUser().getId())){
            throw new EntityNotFoundException(String.format("Utilisateur avec ID {%s} non trouvé", dto.getUser().getId()), ErrorCodes.USER_NOT_FOUND);
        }

        return rolesMapper.fromEntity(
                rolesRepo.save(
                        rolesMapper.toEntity(dto)
                )
        );
    }

    @Override
    public RolesDTO update(RolesDTO dto) {
        List<String> errors = RolesValidator.validate(dto);
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Role incorrect", ErrorCodes.ROLES_NOT_VALID, errors);
        }
        Roles role = rolesRepo.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Role avec ID {%s} non trouvé",dto.getId()), ErrorCodes.ROLES_NOT_FOUND)
        );
        role.setName(dto.getName());

        return rolesMapper.fromEntity(
                rolesRepo.save(role)
        );
    }

    @Override
    public List<RolesDTO> listRoles() {
        return rolesMapper.fromEntity(
                rolesRepo.findAll()
        );
    }

    @Override
    public void delete(Integer roleId) {
        if (roleId!=null){
            Roles role = rolesRepo.findById(roleId).orElseThrow(
                    ()->new EntityNotFoundException(String.format("Rôle avec ID {%s} non trouvé",roleId), ErrorCodes.ROLES_NOT_FOUND)
            );
            if (role.getUser()==null){
                rolesRepo.deleteById(roleId);
            }else {
                throw new InvalidOperationException(String.format("Rôle avec ID {%s} est déjà attribué à un utilisateur", roleId), ErrorCodes.ROLES_ALREADY_IN_USE);
            }
        }else {
            throw new InvalidEntityException("Identifiant invalide pour rôle", ErrorCodes.ROLES_NOT_VALID);
        }

    }
}
