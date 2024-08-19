package com.wayz.CFMS.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Объект DTO регистрации пользователя
 */
public class UserRegistrationData {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime dateBirth;

    public UserRegistrationData(String login,
                                String password,
                                String firstName,
                                String lastName,
                                String email,
                                LocalDateTime dateBirth) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateBirth = dateBirth;
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

    public LocalDateTime getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDateTime dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationData that = (UserRegistrationData) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(dateBirth, that.dateBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, firstName, lastName, email, dateBirth);
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateBirth=" + dateBirth +
                '}';
    }
}
