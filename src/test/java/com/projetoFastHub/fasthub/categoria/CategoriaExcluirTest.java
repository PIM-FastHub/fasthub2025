package com.projetoFastHub.fasthub.categoria;

import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria.ExcluirCategoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaExcluirTest {

    @InjectMocks
    private ExcluirCategoria excluirCategoria;

    @Mock
    private CategoriaRepository repository;

    @Test
    void excluirCategoria_Sucesso() {
        // Arrange
        Long categoriaId = 1L;
        CategoriaModel categoriaExistente = new CategoriaModel();
        categoriaExistente.setId(categoriaId);

        when(repository.buscaCategoriaPorId(categoriaId)).thenReturn(categoriaExistente);

        // Act
        String resultado = excluirCategoria.excluirCategoria(categoriaId);

        // Assert
        assertEquals("Excluído com Sucesso", resultado);
        verify(repository, times(1)).buscaCategoriaPorId(categoriaId);
        verify(repository, times(1)).excluirCategoria(categoriaExistente);
    }


    @Test
    void excluirCategoria_ErroAoExcluir() {
        // Arrange
        Long categoriaId = 1L;
        CategoriaModel categoriaExistente = new CategoriaModel();
        categoriaExistente.setId(categoriaId);

        when(repository.buscaCategoriaPorId(categoriaId)).thenReturn(categoriaExistente);
        doThrow(new RuntimeException("Erro simulado ao excluir")).when(repository).excluirCategoria(categoriaExistente);

        // Act
        String resultado = excluirCategoria.excluirCategoria(categoriaId);

        // Assert
        assertEquals("Erro no excluir categoria Erro simulado ao excluir", resultado);
        verify(repository, times(1)).buscaCategoriaPorId(categoriaId);
        verify(repository, times(1)).excluirCategoria(categoriaExistente);
    }


}