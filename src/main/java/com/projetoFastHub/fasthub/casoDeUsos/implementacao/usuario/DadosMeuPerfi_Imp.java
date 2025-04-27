package com.projetoFastHub.fasthub.casoDeUsos.implementacao.usuario;

import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.aplicacao.Endereco.Endereco;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.user.DadosPerfilDTO;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.usuario.DadosMeuPerfil;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Component;

@Component
public class DadosMeuPerfi_Imp implements DadosMeuPerfil {

    @Autowired
    UserRepository repository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public DadosPerfilDTO retornaDadosMeuPerfil(User user) {
        Endereco endereco = user.getEndereco();
        CategoriaModel categoria = user.getCategoria();

        String cep = endereco != null ? endereco.getCep() : null;
        String rua = endereco != null ? endereco.getRua() : null;
        String estado = endereco != null ? endereco.getEstado() : null;
        Long categoriaId = categoria != null ? categoria.getId() : null;

        DadosPerfilDTO dto = new DadosPerfilDTO(
                user.getNome(),
                user.getSobrenome(),
                cep,
                rua,
                estado,
                categoriaId,
                user.getEmail(),
                user.getTelefone()
        );





        return dto;
    }

    @Override
    public User alterar(DadosPerfilDTO dto, Long id) {
        try{
            User usuario = repository.findById(id).orElse(null);
            usuario.setNome(dto.nome());
            usuario.setSobrenome(dto.sobrenome());
            usuario.setCategoriaModel(categoriaRepository.buscaCategoriaPorId(dto.categoriaId()));
            usuario.setEmail(dto.email());
            usuario.setTelefone(dto.telefone());
            Endereco endereco = new Endereco();
            endereco.setCep(dto.cep());
            endereco.setRua(dto.rua());
            endereco.setEstado(dto.estado());
            usuario.setEndereco(endereco);
            repository.save(usuario);
            return usuario;

        }catch (Exception e){
            return null;
        }


    }
}




