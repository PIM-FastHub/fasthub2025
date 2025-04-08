package com.projetoFastHub.fasthub.solicitacao;

import com.projetoFastHub.fasthub.servico.ServicoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SolicitacaoDAO {

    private static final String TABELA = SolicitacaoModel.class.getSimpleName();

    @PersistenceContext
    private EntityManager manager;

    public SolicitacaoDAO(EntityManager manager) {
        this.manager = manager;
    }


    @Transactional
    public void insere(SolicitacaoModel item) {
        this.manager.persist(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void altera(SolicitacaoModel item) {
        this.manager.merge(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void excluir(SolicitacaoModel item) {
        this.manager.remove(this.manager.find(SolicitacaoModel.class, item.getId()));
    }


    public List<SolicitacaoModel> listaTodasSolicitacoes() {
        String jpql = "SELECT i FROM " + TABELA+ " i";
        TypedQuery<SolicitacaoModel> query = this.manager.createQuery(jpql, SolicitacaoModel.class);
        return query.getResultList();
    }

    public SolicitacaoModel buscaPorID(Long id) {
        String jpql = "SELECT c FROM " + TABELA + " c WHERE c.id =:id ";
        TypedQuery<SolicitacaoModel> query = this.manager.createQuery(jpql, SolicitacaoModel.class);
        query.setParameter("id", id);
        SolicitacaoModel resultado = (SolicitacaoModel)query.getSingleResult();
        if (resultado != null) {
            return resultado;
        }
        return null;
    }
}
