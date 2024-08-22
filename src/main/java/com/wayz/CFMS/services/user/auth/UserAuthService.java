package com.wayz.CFMS.services.user.auth;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import org.springframework.http.ResponseEntity;

/**
 * Интерфейс управления авторизацией и регистрацией пользователя в системе
 */
public interface UserAuthService {

    /**
     * Метод регистрации пользователя в системе
     *
     * @param userRegistrationData объект с данными о пользователе
     * @return созданный объект нового пользователя
     */
    User registrationUser(UserRegistrationData userRegistrationData);

    /**
     * Метод авторизации пользователя в системе по заданным параметрам
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @return объект авторизованного пользователя
     */
    ResponseEntity<String> loginUserInSystem(String login, String password);

    /**
     * Выход пользователя из системы
     */
    void logoutUserFromSystem();

    /**
     * Сбросить пароль у текущего авторизованного пользователя
     *
     * @return новый пароль
     */
    String resetPassword();
}
