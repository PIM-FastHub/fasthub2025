package com.projetoFastHub.fasthub.aplicacao.categoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaDAO {
    @PersistenceContext
    private EntityManager manager;

    private static String TABELA = CategoriaModel.class.getSimpleName();

    CategoriaDAO(EntityManager entityManager){
        this.manager = entityManager;
    }

    @Transactional
    public void insereCategoria(CategoriaModel item) {
        this.manager.persist(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void alteraCategoria(CategoriaModel item) {
        this.manager.merge(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void excluirCategoria(CategoriaModel item) {
        this.manager.remove(this.manager.find(CategoriaModel.class, item.getId()));
    }

    public List<CategoriaModel> listaTodasCategorias() {
        String jpql = "SELECT i FROM " + TABELA + " i ORDER BY i.descricao ASC";
        TypedQuery<CategoriaModel> query = this.manager.createQuery(jpql, CategoriaModel.class);
        return query.getResultList();
    }

    public CategoriaModel buscaCategoriaPorId(Long id) {
        String jpql = "SELECT i FROM " + TABELA + " i WHERE i.id = :id";
        TypedQuery<CategoriaModel> query = this.manager.createQuery(jpql, CategoriaModel.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


}
