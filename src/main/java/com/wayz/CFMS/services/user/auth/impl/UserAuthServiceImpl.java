package com.wayz.CFMS.services.user.auth.impl;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import com.wayz.CFMS.services.user.UserInfoService;
import com.wayz.CFMS.services.user.UserManageService;
import com.wayz.CFMS.services.user.auth.LoginUserService;
import com.wayz.CFMS.services.user.auth.PasswordGenerator;
import com.wayz.CFMS.services.user.auth.RegistrationService;
import com.wayz.CFMS.services.user.auth.UserAuthService;
import com.wayz.CFMS.services.user.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    /**
     * Объект интерфейса кодирования пароля через Bcrypt
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Объект интерфейса управления пользователями
     */
    private final UserManageService userManageService;

    /**
     * Объект интерфейса генерации паролей
     */
    private final PasswordGenerator passwordGenerator;


    public UserAuthServiceImpl(@Lazy UserInfoService userInfoService,
                               @Lazy UserManageService userManageService,
                               RegistrationService registrationService,
                               LoginUserService loginUserService,
                               PasswordEncoder passwordEncoder,
                               PasswordGenerator passwordGenerator) {

        this.userInfoService = userInfoService;
        this.registrationService = registrationService;
        this.loginUserService = loginUserService;
        this.passwordEncoder = passwordEncoder;
        this.userManageService = userManageService;
        this.passwordGenerator = passwordGenerator;
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
    public void logoutUserFromSystem(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @Override
    public String resetPassword(String password) {
        User user = getCurrentAuthUser();
        user.setPassword(passwordEncoder.encode(password));

        userManageService.saveUserInDataBase(user);
        return "Поздравляю! Пароль успешно изменен. Login: " + user.getLogin() + ", password: " + password;
    }

    @Override
    public String resetPasswordByLogin(String login) {
        User user = userInfoService.getUserByLogin(login);
        user.setPassword(passwordGenerator.generatePassword(12));

        userManageService.saveUserInDataBase(user); // TODO прикрутить отправку пароля по почте
        return "Поздравляю! Пароль успешно изменен и отправлен Вам на почту.";
    }

    @Override
    public User getCurrentAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
            return customUserDetails.user();
        }
        throw new NullPointerException("Ошибка запроса текущего пользователя. Нет авторизованного пользователя в текущей сессии");
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
