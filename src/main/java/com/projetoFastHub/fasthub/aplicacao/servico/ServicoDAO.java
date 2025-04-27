package com.projetoFastHub.fasthub.aplicacao.servico;

import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServicoDAO implements ServicoRepository {
    private static final String TABELA = ServicoModel.class.getSimpleName();

    @PersistenceContext
    private EntityManager manager;

    public ServicoDAO(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    @Transactional
    public void inserirServico(ServicoModel item) {
        this.manager.persist(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Override
    @Transactional
    public void alteraServico(ServicoModel item) {
        this.manager.merge(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Override
    @Transactional
    public void excluirServico(ServicoModel item) {
        this.manager.remove(this.manager.find(ServicoModel.class, item.getId()));
    }

    @Override
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

    @Override
    public ServicoModel buscaServicoPorId(Long id) {
        return (ServicoModel)this.manager.find(ServicoModel.class, id);
    }

    public List<ServicoModel> buscaServicoPorCategoria(CategoriaModel categoria) {
        String jpql = "SELECT i FROM " + TABELA + " i WHERE i.categoria =: categoria";
        TypedQuery<ServicoModel> query = this.manager.createQuery(jpql, ServicoModel.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }



}