package com.wayz.CFMS.services.user.mail;

/**
 * Интерфейс сервиса почтовых рассылок
 */
public interface MailService {

    /**
     * Метод отправки сообщения на указанную почту
     *
     * @param emailTo адрес почты отправки
     * @param text    текст сообщения
     * @param title   заголовок сообщения
     */
    void sendMailNotification(String emailTo, String text, String title);
}
