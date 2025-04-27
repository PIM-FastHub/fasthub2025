package com.projetoFastHub.fasthub.aplicacao.avaliacao;

import com.projetoFastHub.fasthub.adapters.avaliacao.AvaliacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvaliacaoDAO implements AvaliacaoRepository {
    @PersistenceContext
    private EntityManager manager;

    private static String TABELA = AvaliacaoModel.class.getSimpleName();


    AvaliacaoDAO(EntityManager entityManager){
        this.manager = entityManager;
    }


    @Transactional
    @Override
    public void inserir(AvaliacaoModel a) {
        this.manager.persist(a);
        this.manager.flush();
        this.manager.detach(a);
    }

    @Override
    public List<AvaliacaoModel> listaTudo() {
        String jpql = "SELECT i FROM " + TABELA+ " i";
        TypedQuery<AvaliacaoModel> query = this.manager.createQuery(jpql, AvaliacaoModel.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void editar(AvaliacaoModel a) {
        this.manager.merge(a);
        this.manager.flush();
        this.manager.detach(a);
    }

    @Override
    public AvaliacaoModel buscaPorId(Long id) {
        String jpql = "SELECT i FROM " + TABELA + " i WHERE i.id = :id";
        TypedQuery<AvaliacaoModel> query = this.manager.createQuery(jpql, AvaliacaoModel.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<AvaliacaoModel> buscarPorPrestador(Long prestadorId) {
        return manager.createQuery("SELECT a FROM AvaliacaoModel a WHERE a.prestador.id = :id", AvaliacaoModel.class)
                .setParameter("id", prestadorId)
                .getResultList();
    }

    public Double calcularMediaPorPrestador(Long prestadorId) {
        return manager.createQuery("SELECT AVG(a.nota) FROM AvaliacaoModel a WHERE a.prestador.id = :id", Double.class)
                .setParameter("id", prestadorId)
                .getSingleResult();
    }

}
