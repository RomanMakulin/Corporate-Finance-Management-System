package com.wayz.CFMS.services.user.auth.impl;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import com.wayz.CFMS.services.user.UserInfoService;
import com.wayz.CFMS.services.user.auth.LoginUserService;
import com.wayz.CFMS.services.user.auth.RegistrationService;
import com.wayz.CFMS.services.user.auth.UserAuthService;
import com.wayz.CFMS.services.user.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Сервис, реализующий интерфейс UserAuthService
 */
@Service
public class UserAuthServiceImpl implements UserDetailsService, UserAuthService {

    /**
     * Объект интерфейса получения информации о пользователях
     */
    private final UserInfoService userInfoService;

    /**
     * Объект интерфейса регистрации пользователей в системе
     */
    private final RegistrationService registrationService;

    /**
     * Объект интерфейса входа пользователя в систему
     */
    private final LoginUserService loginUserService;

    public UserAuthServiceImpl(UserInfoService userInfoService,
                               RegistrationService registrationService, LoginUserService loginUserService) {

        this.userInfoService = userInfoService;
        this.registrationService = registrationService;
        this.loginUserService = loginUserService;
    }

    @Override
    public User registrationUser(UserRegistrationData userRegistrationData) {
        return registrationService.registrationUser(userRegistrationData);
    }

    @Override
    public ResponseEntity<String> loginUserInSystem(String login, String password) {
        return loginUserService.login(login, password);
    }

    @Override
    public void logoutUserFromSystem() {

    }

    @Override
    public String resetPassword() {
        return "";
    }

    /**
     * Метод Security, загруждающий пользователя по логину при авторизации в систему
     *
     * @param login логин пользователя для поиска его в БД
     * @return кастомный объект данных об авторизованном пользователе
     * @throws UsernameNotFoundException ошибка получения пользователя по логину
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userInfoService.getUserByLogin(login);
        return new CustomUserDetails(user);
    }
}
