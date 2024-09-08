package com.wayz.CFMS.services.employee.auth.impl;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import com.wayz.CFMS.repositories.UserRepository;
import com.wayz.CFMS.services.employee.UserInfoService;
import com.wayz.CFMS.services.employee.auth.LoginUserService;
import com.wayz.CFMS.services.employee.auth.PasswordGenerator;
import com.wayz.CFMS.services.employee.auth.RegistrationService;
import com.wayz.CFMS.services.employee.auth.UserAuthService;
import com.wayz.CFMS.services.mail.MailService;
import com.wayz.CFMS.services.employee.security.CustomUserDetails;
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

    private final UserInfoService userInfoService;
    private final RegistrationService registrationService;
    private final LoginUserService loginUserService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordGenerator passwordGenerator;
    private final MailService mailService;
    private final UserRepository userRepository;


    public UserAuthServiceImpl(@Lazy UserInfoService userInfoService,
                               RegistrationService registrationService,
                               LoginUserService loginUserService,
                               PasswordEncoder passwordEncoder,
                               PasswordGenerator passwordGenerator,
                               MailService mailService,
                               UserRepository userRepository) {

        this.userInfoService = userInfoService;
        this.registrationService = registrationService;
        this.loginUserService = loginUserService;
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.mailService = mailService;
        this.userRepository = userRepository;
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

        userRepository.save(user);
        return "Поздравляю! Пароль успешно изменен. Login: " + user.getLogin() + ", password: " + password;
    }

    @Override
    public String resetPasswordByLogin(String login) {
        User user = userInfoService.getUserByLogin(login);
        String password = passwordGenerator.generatePassword(12);

        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        mailService.sendMailNotification(user.getEmail(), "Ваш новый пароль для входа в систему: " + password, "Восстановление пароя");
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
