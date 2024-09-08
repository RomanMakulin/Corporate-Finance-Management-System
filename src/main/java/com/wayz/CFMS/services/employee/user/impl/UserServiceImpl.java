package com.wayz.CFMS.services.employee.user.impl;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import com.wayz.CFMS.repositories.UserRepository;
import com.wayz.CFMS.services.employee.UserInfoService;
import com.wayz.CFMS.services.employee.user.UserService;
import org.springframework.stereotype.Service;

/**
 * Реализация интерфейса UserManageService для управления пользователями и их данными
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserInfoService userInfoService;

    public UserServiceImpl(UserRepository userRepository, UserInfoService userInfoService) {
        this.userRepository = userRepository;
        this.userInfoService = userInfoService;
    }

    @Override
    public User updateCurrentUser(UserRegistrationData updateData) {
        // TODO Доделать когда реализую Security
        return null;
    }

    /**
     * Saves the given user to the database.
     *
     * @param user The user object to be saved. This object must be fully initialized and validated.
     *             It is assumed that the user's ID is not set, as the method will generate a new ID for the user.
     * @throws IllegalArgumentException If the given user object is null.
     */
    @Override
    public void saveUserInDataBase(User user) {
        try {
            userRepository.save(user);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user object: " + user);
        }
    }
}
