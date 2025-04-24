package com.projetoFastHub.fasthub.aplicacao.email;

import com.projetoFastHub.fasthub.infra.seguranca.EmailServiceTeste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailServiceTeste envioEmailService;

    @GetMapping("administracao/enviar-email-teste")
    public String enviarEmail() {
        envioEmailService.enviarEmail("joao.claudio@uscsonline.com.br", "TESTE ASSUNTO", "CORPO ASSUNTO");
        return "Sucesso";
    }
}
