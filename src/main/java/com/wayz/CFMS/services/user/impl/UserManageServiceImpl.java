package com.wayz.CFMS.services.user.impl;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import com.wayz.CFMS.models.subModels.UserActivityStatus;
import com.wayz.CFMS.services.user.UserInfoService;
import com.wayz.CFMS.services.user.UserManageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Реализация интерфейса UserManageService для управления пользователями и их данными
 */
@Service
public class UserManageServiceImpl implements UserManageService {

    private final UserInfoService userInfoService;

    public UserManageServiceImpl(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public User updateCurrentUser(UserRegistrationData updateData) {
        // TODO Доделать когда реализую Security
        return null;
    }

    @Override
    public User updateUserByAdmin(User updateData) {
        User userForUpdate = userInfoService.getUserById(updateData.getId());

        Optional.ofNullable(updateData.getBirthdayDate()).ifPresent(userForUpdate::setBirthdayDate);
        Optional.ofNullable(updateData.getEmail()).ifPresent(userForUpdate::setEmail);
        Optional.ofNullable(updateData.getFirstName()).ifPresent(userForUpdate::setFirstName);
        Optional.ofNullable(updateData.getLastName()).ifPresent(userForUpdate::setLastName);
        Optional.ofNullable(updateData.getRole()).ifPresent(userForUpdate::setRole);
        Optional.ofNullable(updateData.getStatus()).ifPresent(userForUpdate::setStatus);
        Optional.ofNullable(updateData.getPassword()).ifPresent(userForUpdate::setPassword);
        Optional.ofNullable(updateData.getLogin()).ifPresent(userForUpdate::setLogin);

        userForUpdate.setUpdatedDate(LocalDateTime.now());

        userInfoService.saveUserInDataBase(userForUpdate);

        return userForUpdate;
    }

    @Override
    public User updateUserStatus(String login, UserActivityStatus userStatus) {
        User userForUpdate = userInfoService.getUserByLogin(login);
        Optional.ofNullable(userStatus).ifPresent(userForUpdate::setStatus);

        userInfoService.saveUserInDataBase(userForUpdate);
        return userForUpdate;
    }
}
