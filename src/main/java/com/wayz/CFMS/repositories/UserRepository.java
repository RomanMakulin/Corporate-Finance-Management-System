package com.wayz.CFMS.repositories;

import com.wayz.CFMS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс взаимодействия с базой данных. Таблица "users"
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Проверить если ли пользователь в БД по заданным параметрам: email, login
     *
     * @param email почта
     * @param login логин
     * @return логическое выражение true/false
     */
    boolean existsUserByEmailAndLogin(String email, String login);

    Optional<User> getUsersByLogin(String login);

    Optional<User> getUsersByEmail(String email);
}
