package com.projetoFastHub.fasthub.aplicacao.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {



    public void sendEmail(String to, String subject, String text) {

    }
}
