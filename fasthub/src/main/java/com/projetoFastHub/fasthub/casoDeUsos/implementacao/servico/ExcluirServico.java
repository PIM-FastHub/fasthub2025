package com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico;


import com.projetoFastHub.fasthub.aplicacao.servico.ServicoDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.servico.ExcluirServicoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExcluirServico implements ExcluirServicoCase {
    @Autowired
    ServicoDAO dao;
    @Override
    public String excluirServico(Long id) {
        try{
            ServicoModel servico = dao.buscaServicoPorId(id);
            dao.excluirServico(servico);
            return "Excluido com Sucesso";
        }catch (Exception e){
            return "Erro ao Excluir Servico "+ e.getMessage();
        }


    }
}
