package com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico;

import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoResponseDTO;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.servico.IncluirServicoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class CriarServico implements IncluirServicoCase {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ServicoRepository servicoRepository;



    @Override
    public String criarServico(ServicoResponseDTO servico) {
        try{
        CategoriaModel categoriaModel = categoriaRepository.buscaCategoriaPorId(servico.categoriaId());

        ServicoModel servicoNovo = new ServicoModel();
        servicoNovo.setCategoria(categoriaModel);
        servicoNovo.setNome(servico.nome());
        servicoNovo.setDescricao(servico.descricao());
        servicoNovo.setDataInclusao(Calendar.getInstance());
        servicoRepository.inserirServico(servicoNovo);
        return "Serviço criado com sucesso";
    }catch (Exception e){
            return "Erro ao Incluir "+ e.getMessage();
        }
    }
}
