package com.projetoFastHub.fasthub.infra.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceTeste {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(String para, String assunto, String corpo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(para);
        mensagem.setSubject(assunto);
        mensagem.setText(corpo);
        mensagem.setFrom("joaopedrovaleclaudio10@gmail.com"); // deve bater com o configurado

        mailSender.send(mensagem);
    }
}
