package com.wayz.CFMS.services.user.auth;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    void logoutUserFromSystem(HttpServletRequest request, HttpServletResponse response);

    /**
     * Изменить пароль у авторизованного пользователя
     *
     * @param password новый пароль
     * @return сообщение о статусе изменения пароля
     */
    String resetPassword(String password);


    /**
     * Восстановление пароля по логину. Новый пароль придет на почту.
     * В аргументах пароль НЕ передается.
     *
     * @param login логин пользователя для восстановления пароля
     * @return сообщение о статусе изменения пароля
     */
    String resetPasswordByLogin(String login);

    /**
     * Получить текущего авторизованного пользователя
     *
     * @return объект пользователя
     */
    User getCurrentAuthUser();
}
