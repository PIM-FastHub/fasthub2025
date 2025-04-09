package com.projetoFastHub.fasthub.aplicacao.servico;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServicoDAO {
    private static final String TABELA = ServicoModel.class.getSimpleName();

    @PersistenceContext
    private EntityManager manager;

    public ServicoDAO(EntityManager manager) {
        this.manager = manager;
    }


    @Transactional
    public void insereServico(ServicoModel item) {
        this.manager.persist(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void alteraServico(ServicoModel item) {
        this.manager.merge(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void excluirServico(ServicoModel item) {
        this.manager.remove(this.manager.find(ServicoModel.class, item.getId()));
    }

    public List<ServicoModel> listaTodosServicos() {
        String jpql = "SELECT i FROM " + TABELA + " i ORDER BY i.descricao ASC";
        TypedQuery<ServicoModel> query = this.manager.createQuery(jpql, ServicoModel.class);
        return query.getResultList();
    }

    public ServicoModel buscaPorID(Long id) {
        String jpql = "SELECT c FROM " + TABELA + " c WHERE c.id =:id ";
        TypedQuery<ServicoModel> query = this.manager.createQuery(jpql, ServicoModel.class);
        query.setParameter("id", id);
        ServicoModel resultado = (ServicoModel)query.getSingleResult();
        if (resultado != null) {
            return resultado;
        }
        return null;
    }

    public ServicoModel buscaServicoPorId(Long id) {
        return (ServicoModel)this.manager.find(ServicoModel.class, id);
    }

    public List<ServicoModel> buscaServicoPorIdCategoria(ServicoModel categoria) {
        String jpql = "SELECT i FROM " + TABELA + " i WHERE i.categoria =: categoria";
        TypedQuery<ServicoModel> query = this.manager.createQuery(jpql, ServicoModel.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }
}