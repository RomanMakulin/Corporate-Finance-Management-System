package com.wayz.CFMS.services.employee.impl;

import com.wayz.CFMS.models.User;
import com.wayz.CFMS.repositories.UserRepository;
import com.wayz.CFMS.services.employee.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Сервис получения информации о пользователе и прямое взаимодействие с базой данных
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserRepository userRepository;

    public UserInfoServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ошибка получения пользователя по ID"));
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUsersByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Ошибка получения пользователя по login"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUsersByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Ошибка получения пользователя по email"));
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public boolean existsUserByLogin(String login) {
        return userRepository.existsUserByLogin(login);
    }

    @Override
    public boolean existsUserByEmailOrLogin(String email, String login) {
        return userRepository.existsUserByEmailOrLogin(email, login);
    }

    @Override
    public boolean existsUserById(UUID id) {
        return userRepository.existsById(id);
    }

    @Override
    public String checkLastUserLoginDate(String login) {
        User user = getUserByLogin(login);
        return user.getFirstName() + " " + user.getLastName() + " последний раз был онлайн: " + user.getLastLoginDate();
    }

    @Override
    public String checkLastUserUpdateDate(String login) {
        User user = getUserByLogin(login);
        return user.getFirstName() + " " + user.getLastName() + " последний раз был обновлен: " + user.getUpdatedDate();
    }

    @Override
    public String getUserRegistrationDate(String login) {
        User user = getUserByLogin(login);
        return user.getFirstName() + " " + user.getLastName() + " был зарегистрирован в системе: " + user.getRegistrationDate();
    }

}
