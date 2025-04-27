package com.projetoFastHub.fasthub.categoria;

import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.categoria.InclusaoCategoriaDTO;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria.CriarCategoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaIncluirTest {

    @InjectMocks
    private CriarCategoria criarCategoria;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    void criarCategoria_Sucesso() {
        // Arrange
        InclusaoCategoriaDTO inclusaoDTO = new InclusaoCategoriaDTO("Nova Categoria");
        CategoriaModel categoriaSalva = new CategoriaModel();
        categoriaSalva.setDescricao("Nova Categoria");
        // Não precisamos configurar a dataInclusao, pois ela é gerada dentro do método

        // Act
        String resultado = criarCategoria.criarCategoria(inclusaoDTO);

        // Assert
        assertEquals("Criado com Sucesso", resultado);
        verify(categoriaRepository, times(1)).insereCategoria(org.mockito.ArgumentMatchers.any(CategoriaModel.class));
    }

    @Test
    void criarCategoria_FalhaAoInserir() {
        // Arrange
        InclusaoCategoriaDTO inclusaoDTO = new InclusaoCategoriaDTO("Outra Categoria");
        doThrow(new DataAccessException("Erro simulado no banco") {}).
                when(categoriaRepository).insereCategoria(org.mockito.ArgumentMatchers.any(CategoriaModel.class));

        // Act & Assert
        String resultado = criarCategoria.criarCategoria(inclusaoDTO);
        assertEquals("Erro ao Criar", resultado);
        verify(categoriaRepository, times(1)).insereCategoria(org.mockito.ArgumentMatchers.any(CategoriaModel.class));
    }

    @Test
    void criarCategoria_DescricaoVazia() {
        // Arrange
        InclusaoCategoriaDTO inclusaoDTO = new InclusaoCategoriaDTO("");

        // Act
        String resultado = criarCategoria.criarCategoria(inclusaoDTO);

        // Assert
        assertEquals("Criado com Sucesso", resultado);
        verify(categoriaRepository, times(1)).insereCategoria(org.mockito.ArgumentMatchers.any(CategoriaModel.class));
        // Poderia adicionar uma verificação para garantir que a descrição foi realmente salva como ""
        // se essa for a lógica desejada.
    }

    @Test
    void criarCategoria_DescricaoNula() {
        // Arrange
        InclusaoCategoriaDTO inclusaoDTO = new InclusaoCategoriaDTO(null);

        // Act
        String resultado = criarCategoria.criarCategoria(inclusaoDTO);

        // Assert
        assertEquals("Criado com Sucesso", resultado);
        verify(categoriaRepository, times(1)).insereCategoria(org.mockito.ArgumentMatchers.any(CategoriaModel.class));
        // Similar ao caso da descrição vazia, pode-se verificar se o valor nulo é tratado corretamente ao salvar.
    }
}