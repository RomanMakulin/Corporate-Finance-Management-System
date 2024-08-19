package com.wayz.CFMS.models;

import com.wayz.CFMS.models.subModels.UserActivityStatus;
import com.wayz.CFMS.models.subModels.UserRole;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Сущность пользователей
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Уникальный идентификатор пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Логин для аутентификации
     */
    private String login;

    /**
     * Пароль для аутентификации
     */
    private String password;

    /**
     * Имя пользователя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * EMAIL пользователя
     */
    private String email;

    /**
     * Роль пользователя
     */
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * День рождения пользователя
     */
    @Column(name = "birthday_date")
    private LocalDateTime birthdayDate;

    /**
     * Статус активности пользователя
     */
    @Enumerated(EnumType.STRING)
    private UserActivityStatus status;

    /**
     * Дата регистрации (создания) пользователя
     */
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    /**
     * Дата последнего обновления пользователя
     */
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    /**
     * Дата последнего входа в систему
     */
    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    // region CONSTRUCTORS
    /**
     * Базовый конструктор регистрации пользователя
     *
     * @param login        логин из запроса
     * @param password     пароль из запроса
     * @param firstName    имя из запроса
     * @param lastName     фамилия из запроса
     * @param email        почта из запроса
     * @param birthdayDate дата рождения из запроса
     */
    public User(String login,
                String password,
                String firstName,
                String lastName,
                String email,
                LocalDateTime birthdayDate) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.role = UserRole.EMPLOYEE;
        this.status = UserActivityStatus.ACTIVE;
        this.registrationDate = LocalDateTime.now();
    }

    /**
     * Пустой конструктор для инициализации Entity
     */
    public User() {
    }
    // endregion

    // region GETTERS AND SETTERS

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDateTime birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public UserActivityStatus getStatus() {
        return status;
    }

    public void setStatus(UserActivityStatus status) {
        this.status = status;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    // endregion

    // region HASH CODE AND EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(email, user.email)
                && role == user.role
                && Objects.equals(birthdayDate, user.birthdayDate)
                && status == user.status
                && Objects.equals(registrationDate, user.registrationDate)
                && Objects.equals(updatedDate, user.updatedDate)
                && Objects.equals(lastLoginDate, user.lastLoginDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, email,
                role, birthdayDate, status, registrationDate, updatedDate, lastLoginDate);
    }
    //endregion

    // region TO STRING
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", birthdayDate=" + birthdayDate +
                ", status=" + status +
                ", registrationDate=" + registrationDate +
                ", updatedDate=" + updatedDate +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }
    // endregion
}
