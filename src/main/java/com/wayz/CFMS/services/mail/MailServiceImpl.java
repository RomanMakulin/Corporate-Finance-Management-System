package com.wayz.CFMS.services.mail;

import com.wayz.CFMS.configuration.MailConfig;
import org.springframework.stereotype.Service;

/**
 * Сервис, реализующий интерфейс почтовых рассылок
 */
@Service
public class MailServiceImpl implements MailService {

    private final MailConfig mailConfig;

    public MailServiceImpl(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    @Override
    public void sendMailNotification(String emailTo, String text, String title) {
        mailConfig.messageManage(emailTo, text, title);
    }
}
