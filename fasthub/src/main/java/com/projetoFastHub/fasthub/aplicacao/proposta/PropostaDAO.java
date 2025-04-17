package com.projetoFastHub.fasthub.aplicacao.proposta;

import com.projetoFastHub.fasthub.adapters.proposta.PropostaRepository;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropostaDAO implements PropostaRepository {
    private static final String TABELA = PropostaModel.class.getSimpleName();

    @PersistenceContext
    private EntityManager manager;

    public PropostaDAO(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public void salvar(PropostaModel p) {
        this.manager.persist(p);
        this.manager.flush();
        this.manager.detach(p);
    }

    @Override
    public void editar(PropostaModel p) {
        this.manager.merge(p);
        this.manager.flush();
        this.manager.detach(p);
    }

    @Override
    public void excluir(PropostaModel p) {
        this.manager.remove(this.manager.find(PropostaModel.class, p.getId()));
    }

    @Override
    public List<PropostaModel> listarTodas() {
        String jpql = "SELECT i FROM " + TABELA + " i ORDER BY i.descricao ASC";
        TypedQuery<PropostaModel> query = this.manager.createQuery(jpql, PropostaModel.class);
        return query.getResultList();
    }

    @Override
    public PropostaModel buscarPorId(Long id) {
        String jpql = "SELECT c FROM " + TABELA + " c WHERE c.id =:id ";
        TypedQuery<PropostaModel> query = this.manager.createQuery(jpql, PropostaModel.class);
        query.setParameter("id", id);
        PropostaModel resultado = (PropostaModel)query.getSingleResult();
        if (resultado != null) {
            return resultado;
        }
        return null;
    }

    @Override
    public List<PropostaModel> buscaPropostaPorPrestador(User prestador) {
        String jpql = "SELECT c FROM " + TABELA + " c WHERE c.prestador =:prestador ";
        TypedQuery<PropostaModel> query = this.manager.createQuery(jpql, PropostaModel.class);
        query.setParameter("prestador", prestador);


        return query.getResultList();
    }
}
