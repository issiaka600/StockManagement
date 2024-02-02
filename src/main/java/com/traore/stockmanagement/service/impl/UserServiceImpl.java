package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import com.traore.stockmanagement.dto.roles.RolesDTO;
import com.traore.stockmanagement.dto.user.CreateUserDTO;
import com.traore.stockmanagement.dto.user.UpdateUserPasswordDTO;
import com.traore.stockmanagement.dto.user.UserDTO;
import com.traore.stockmanagement.enums.RoleName;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.mapper.RolesMapper;
import com.traore.stockmanagement.mapper.UserMapper;
import com.traore.stockmanagement.model.Roles;
import com.traore.stockmanagement.model.User;
import com.traore.stockmanagement.repository.EnterpriseRepo;
import com.traore.stockmanagement.repository.UserRepo;
import com.traore.stockmanagement.service.AttachmentService;
import com.traore.stockmanagement.service.RolesService;
import com.traore.stockmanagement.service.UserService;
import com.traore.stockmanagement.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepo userRepo;
    private UserMapper userMapper;
    private EnterpriseRepo enterpriseRepo;
    private AttachmentService attachmentService;
    private RolesService rolesService;

    @Override
    public CreateUserDTO save(CreateUserDTO dto) {
        List<String> errors = UserValidator.validate(dto);
        if(!errors.isEmpty()){
            for (String error:errors) {
                logger.error(error);
            }
            logger.error("Utilisateur is not valid {}", dto);
            throw new InvalidEntityException("Utilisateur invalide", ErrorCodes.USER_NOT_VALID, errors);
        }
        if (!enterpriseRepo.existsById(dto.getEnterprise().getId())){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", dto.getEnterprise().getId()), ErrorCodes.ENTERPRISE_NOT_FOUND);
        }

        User user = userRepo.save(
                userMapper.fromCreateUserDTOtoEntity(dto)
        );
        return userMapper.FromEntityToCreateUserDTO(user);
    }

    @Override
    public CreateUserDTO save(MultipartFile file, String firstName, String lastName, String email, String password, String phone, String address, Long enterpriseId, RoleName role) {
        CreateUserDTO dto = CreateUserDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .address(address)
                .enterprise(
                        UpdateEnterpriseDTO.builder()
                                .id(enterpriseId)
                                .build()
                )
                .build();

        List<String> errors = UserValidator.validate(dto);
        if(!errors.isEmpty()){
            for (String error:errors) {
                logger.error(error);
            }
            logger.error("Utilisateur is not valid {}", dto);
            throw new InvalidEntityException("Utilisateur invalide", ErrorCodes.USER_NOT_VALID, errors);
        }
        if (!enterpriseRepo.existsById(dto.getEnterprise().getId())){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", dto.getEnterprise().getId()), ErrorCodes.ENTERPRISE_NOT_FOUND);
        }
        //Inserting image
        if (!file.isEmpty()){
            dto.setPhoto(
                    attachmentService.saveImage(file)
            );
        }
        User user = userRepo.save(
                userMapper.fromCreateUserDTOtoEntity(dto)
        );

        //Adding role if defined
        if (role!=null){
            RolesDTO rolesDTO = RolesDTO.builder()
                    .name(role)
                    .user(
                            userMapper.FromEntityToCreateUserDTO(user)
                    )
                    .build();
            rolesService.save(rolesDTO);
        }


        return userMapper.FromEntityToCreateUserDTO(user);

    }

    @Override
    public UserDTO getUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(
                ()->new EntityNotFoundException(String.format("Utilisateur avec ID {%s} non trouvée", userId), ErrorCodes.USER_NOT_FOUND)
        );
        return userMapper.fromEntity(user);
    }

    @Override
    public UserDTO updateUserPassword(UpdateUserPasswordDTO dto) {
        User existingUser = userRepo.findById(dto.getId()).orElseThrow(
                ()->new InvalidEntityException("Format de modification de mots de passe utilisateur invalide", ErrorCodes.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID)
        );
        existingUser.setPassword(dto.getPassword());
        User updatedUser = userRepo.save(existingUser);
        return userMapper.fromEntity(updatedUser);
    }

    @Override
    public List<UserDTO> getUsers(Long enterpriseId) {
        if (!enterpriseRepo.existsById(enterpriseId)){
            throw new EntityNotFoundException(String.format("Enterprise avec ID {%s} non trouvée", enterpriseId), ErrorCodes.ENTERPRISE_NOT_FOUND);
        }

        return userMapper.fromEntity(
                userRepo.findUsersByEnterprise_Id(enterpriseId)
        );
    }
    @Override
    public void delete(Long userId) {
        boolean isUserExists = userRepo.existsById(userId);
        if(isUserExists){
            userRepo.deleteById(userId);
        }else{
            throw new EntityNotFoundException(String.format("User with {%s} not found",userId));
        }

    }
}
