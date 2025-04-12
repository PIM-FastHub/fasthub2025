package com.projetoFastHub.fasthub.aplicacao.configuracoesDoSistema;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConfiguracoesDoSistemaDAO {
    private final String TABELA = ConfiguracoesDoSistema.class.getSimpleName();

    @PersistenceContext
    private EntityManager em;

    ConfiguracoesDoSistemaDAO(EntityManager em){
        this.em = em;
    }


    @Transactional
    public void insereConfiguracao(ConfiguracoesDoSistema item) {
        this.em.persist(item);
        this.em.flush();
        this.em.detach(item);
    }

    public ConfiguracoesDoSistema buscaPorPropriedade(String propriedade) {
        String jpql = "SELECT c FROM " + TABELA + " c WHERE c.propriedade = :propriedade";
        TypedQuery<ConfiguracoesDoSistema> query = this.em.createQuery(jpql, ConfiguracoesDoSistema.class);
        query.setParameter("propriedade", propriedade);

        List<ConfiguracoesDoSistema> resultados = query.getResultList();

        if (resultados.isEmpty()) {
            return null;
        } else {
            return resultados.get(0);
        }
    }


    //@PostConstruct
    public void init(){
        adicionarConfiguracaoSeNaoExistir("EMAIL_FROM", "joao.claudio@uscsonline.com.br");
        adicionarConfiguracaoSeNaoExistir("EMAIL_SENHA", "Senha00@");
        adicionarConfiguracaoSeNaoExistir("EMAIL_SMTP", "smtp.gmail.com");
        adicionarConfiguracaoSeNaoExistir("EMAIL_PORTA", "587");
        adicionarConfiguracaoSeNaoExistir("EMAIL_ASSUNTO",
                "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;'><div style='max-width: 600px; margin: 20px auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);'><h2 style='color: #333;'>Olá prezado(a),</h2><p style='color: #555; font-size: 16px;'>" +
                "Recebemos uma solicitação de reset de senha. Para alterar sua senha, clique no botão abaixo:</p><p style='text-align: center;'><a href='{url}' style='background: #E91E63; color: #fff; padding: 12px 20px; text-decoration: none; border-radius: 5px; font-size: 16px; display: inline-block;'>Alterar Senha</a></p><p style='color: #555; font-size: 14px;'>Se o botão acima não funcionar, clique no link abaixo ou copie e cole no seu navegador:</p><p style='word-break: break-word; font-size: 14px; text-align: center;'>" +
                "<a href='{url}' style='color: #E91E63; text-decoration: none;'>{url}</a></p><hr style='border: 0; border-top: 1px solid #ddd;'><p style='color: #777; font-size: 14px;'>" +
                "Este e-mail é automático. Se você não solicitou a alteração, pode ignorá-lo.</p><p style='color: #777; font-size: 14px; text-align: center;'>Atenciosamente,<br><strong>Equipe FastHub</strong></p></div></body>");

    }

    private void adicionarConfiguracaoSeNaoExistir(String propriedade, String valor) {
        ConfiguracoesDoSistema config = buscaPorPropriedade(propriedade);
        if (config == null) {
            insereConfiguracao(new ConfiguracoesDoSistema(propriedade, valor));
        } else {
            System.out.println("Configuração já existe: " + propriedade);
        }
    }

}
