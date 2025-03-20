package com.projetoFastHub.fasthub.solicitacaoServico;

import com.projetoFastHub.fasthub.servico.ServicoModel;
import com.projetoFastHub.fasthub.solicitacaoServico.SolicitacaoServicoModel;
import com.projetoFastHub.fasthub.user.UserDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SolicitacaoServicoDAO {

    private static final String TABELA = SolicitacaoServicoModel.class.getSimpleName();

    @PersistenceContext
    private EntityManager manager;

    public SolicitacaoServicoDAO(EntityManager manager) {
        this.manager = manager;
    }



    @Transactional
    public void insereSolicitacaoServico(SolicitacaoServicoModel item) {
        this.manager.persist(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void alteraSolicitacaoServico(SolicitacaoServicoModel item) {
        this.manager.merge(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void excluirSolicitacaoServico(SolicitacaoServicoModel item) {
        this.manager.remove(this.manager.find(SolicitacaoServicoModel.class, item.getId()));
    }

    public List<SolicitacaoServicoModel> listaSolicitacaoByServico(ServicoModel servico){
        String jpql = "SELECT c FROM "+TABELA+ " c WHERE c.servicoId = :servico";
        TypedQuery<SolicitacaoServicoModel> query = this.manager.createQuery(jpql,SolicitacaoServicoModel.class);
        query.setParameter("servico", servico);
        List<SolicitacaoServicoModel> listagem = query.getResultList();
        return listagem;
    }


    public SolicitacaoServicoModel buscaPorID(Long id) {
        String jpql = "SELECT c FROM " + TABELA + " c WHERE c.id =:id ";
        TypedQuery<SolicitacaoServicoModel> query = this.manager.createQuery(jpql, SolicitacaoServicoModel.class);
        query.setParameter("id", id);
        SolicitacaoServicoModel resultado = (SolicitacaoServicoModel)query.getSingleResult();
        if (resultado != null) {
            return resultado;
        }
        return null;
    }

    public SolicitacaoServicoModel buscaSolicitacaoServicoPorId(Long id) {
        return (SolicitacaoServicoModel)this.manager.find(SolicitacaoServicoModel.class, id);
    }


    public List<SolicitacaoServicoModel> listaSolicitacoesByPrestador(UserDTO usuario){
        String jpql = "SELECT c FROM "+ TABELA + " c WHERE c.prestadorId = :usuario";
        TypedQuery<SolicitacaoServicoModel> query = this.manager.createQuery(jpql, SolicitacaoServicoModel.class);
        query.setParameter("usuario", usuario);
        List<SolicitacaoServicoModel> resultado = query.getResultList();
        if(resultado !=null){
            return resultado;
        }else{
            return null;
        }

    }

    public List<SolicitacaoServicoModel> listaSolicitacoesByPrestador_Ultimas(UserDTO usuario){
        String jpql = "SELECT c FROM "+ TABELA + " c WHERE c.prestadorId = :usuario ORDER BY c.id DESC ";
        TypedQuery<SolicitacaoServicoModel> query = this.manager.createQuery(jpql, SolicitacaoServicoModel.class).setMaxResults(3);
        query.setParameter("usuario", usuario);
        List<SolicitacaoServicoModel> resultado = query.getResultList();
        if(resultado !=null){
            return resultado;
        }else{
            return null;
        }

    }




    public List<SolicitacaoServicoModel> listaSolicitacoesByCliente(UserDTO usuario) {
        String jpql = "SELECT c FROM " + TABELA + " c WHERE c.clienteId = :usuario";
        TypedQuery<SolicitacaoServicoModel> query = this.manager.createQuery(jpql, SolicitacaoServicoModel.class);
        query.setParameter("usuario", usuario);
        List<SolicitacaoServicoModel> resultado = query.getResultList();
        if (resultado != null) {
            return resultado;
        } else {
            return null;
        }
    }
}
