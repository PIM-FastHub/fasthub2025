package com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico;

import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.aplicacao.avaliacao.AvaliacaoDTO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoResponseDTO;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.servico.EditarServicoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class EditarServico implements EditarServicoCase {

    @Autowired
    ServicoRepository servicoRepository;

   @Autowired
    CategoriaRepository categoriaRepository;
    @Override
    public String editarServico(Long id,  ServicoResponseDTO data) {

        try {
            ServicoModel servico = servicoRepository.buscaServicoPorId(id);
            servico.setCategoria(categoriaRepository.buscaCategoriaPorId(data.categoriaId()));
            servico.setDataAlteracao(Calendar.getInstance());
            servico.setNome(data.nome());
            servico.setDescricao(data.descricao());
            servicoRepository.alteraServico(servico);
            return "Alterado com Sucesso";
        }catch (Exception e){
            return "Erro ao Editar "+ e.getMessage();
        }

    }
}
