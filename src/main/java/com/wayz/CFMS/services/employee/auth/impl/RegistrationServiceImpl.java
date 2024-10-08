package com.wayz.CFMS.services.employee.auth.impl;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import com.wayz.CFMS.repositories.UserRepository;
import com.wayz.CFMS.services.employee.UserInfoService;
import com.wayz.CFMS.services.employee.auth.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Реализация интерфейса регистрации пользователей
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserInfoService userInfoService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public RegistrationServiceImpl(UserInfoService userInfoService,
                                   PasswordEncoder passwordEncoder,
                                   UserRepository userRepository) {
        this.userInfoService = userInfoService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User registrationUser(UserRegistrationData regData) {
        try {
            validateRegistrationUser(regData);
            User newUser = new User(
                    regData.getLogin(),
                    passwordEncoder.encode(regData.getPassword()),
                    regData.getFirstName(),
                    regData.getLastName(),
                    regData.getEmail(),
                    regData.getDateBirth());

            userRepository.save(newUser);
            return newUser;
        } catch (NullPointerException e) {
            throw new NullPointerException("Ошибка при создании пользователя: " + e);
        }
    }

    /**
     * Валидация параметров регистрации пользователя
     *
     * @param regData данные при регистрации пользователя
     */
    public void validateRegistrationUser(UserRegistrationData regData) {
        validateEmail(regData.getEmail());
        validateLogin(regData.getLogin());
    }

    /**
     * Валидация почты. Проверка есть ли такой пользователь в системе
     *
     * @param email почта пользователя
     */
    public void validateEmail(String email) {
        if (userInfoService.existsUserByEmail(email)) {
            throw new RuntimeException("Ошибка регистрации. Пользователь с таким email уже существует!: " + email);
        }
    }

    /**
     * Валидация логина. Проверка есть ли такой пользователь в системе
     *
     * @param login логин пользователя
     */
    public void validateLogin(String login) {
        if (userInfoService.existsUserByEmail(login)) {
            throw new RuntimeException("Ошибка регистрации. Пользователь с таким login уже существует!: " + login);
        }
    }
}
