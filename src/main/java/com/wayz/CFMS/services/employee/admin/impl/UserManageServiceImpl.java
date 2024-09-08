package com.wayz.CFMS.services.employee.admin.impl;

import com.wayz.CFMS.models.User;
import com.wayz.CFMS.models.subModels.UserActivityStatus;
import com.wayz.CFMS.repositories.UserRepository;
import com.wayz.CFMS.services.employee.admin.UserManageService;
import com.wayz.CFMS.services.employee.UserInfoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserManageServiceImpl implements UserManageService {

    private final UserInfoService userInfoService;
    private final UserRepository userRepository;

    public UserManageServiceImpl(UserInfoService userInfoService, UserRepository userRepository) {
        this.userInfoService = userInfoService;
        this.userRepository = userRepository;
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

        userRepository.save(userForUpdate);
        return userForUpdate;
    }

    @Override
    public User updateUserStatus(String login, UserActivityStatus userStatus) { // TODO перенести в раздел админа
        User userForUpdate = userInfoService.getUserByLogin(login);
        Optional.ofNullable(userStatus).ifPresent(userForUpdate::setStatus);

        userRepository.save(userForUpdate);
        return userForUpdate;
    }

    @Override
    public void deleteUser(String login) {
        if (userInfoService.existsUserByLogin(login)) {
            userRepository.delete(userInfoService.getUserByLogin(login));
        } else {
            throw new IllegalArgumentException("Ошибка удаления пользователя. Пользователь с таким login не найден: " + login);
        }
    }

}
