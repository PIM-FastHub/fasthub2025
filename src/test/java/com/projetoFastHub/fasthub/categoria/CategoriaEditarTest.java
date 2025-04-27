package com.projetoFastHub.fasthub.categoria;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.categoria.InclusaoCategoriaDTO;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria.EditarCategoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaEditarTest {

    @InjectMocks
    private EditarCategoria editarCategoria;

    @Mock
    private CategoriaDAO dao;

    @Test
    void editarCategoria_Sucesso() {
        // Arrange
        Long categoriaId = 1L;
        InclusaoCategoriaDTO dadosEdicao = new InclusaoCategoriaDTO("Categoria Atualizada");
        CategoriaModel categoriaExistente = new CategoriaModel();
        categoriaExistente.setId(categoriaId);
        categoriaExistente.setDescricao("Categoria Original");
        categoriaExistente.setDataInclusao(Calendar.getInstance());

        when(dao.buscaCategoriaPorId(categoriaId)).thenReturn(categoriaExistente);

        // Act
        String resultado = editarCategoria.editarCategoria(categoriaId, dadosEdicao);

        // Assert
        assertEquals("Editado com Sucesso", resultado);
        verify(dao, times(1)).buscaCategoriaPorId(categoriaId);
        verify(dao, times(1)).alterarCategoria(argThat(categoria ->
                categoria.getId().equals(categoriaId) &&
                        categoria.getDescricao().equals("Categoria Atualizada") &&
                        categoria.getDataInclusao().equals(categoriaExistente.getDataInclusao()) &&
                        categoria.getDataAlteracao() != null
        ));
    }

    @Test
    void editarCategoria_CategoriaNaoEncontrada() {
        // Arrange
        Long categoriaId = 1L;
        InclusaoCategoriaDTO dadosEdicao = new InclusaoCategoriaDTO("Categoria Atualizada");

        when(dao.buscaCategoriaPorId(categoriaId)).thenReturn(null);

        // Act
        String resultado = editarCategoria.editarCategoria(categoriaId, dadosEdicao);

        // Assert
        assertEquals("Erro ao Editar", resultado);
        verify(dao, times(1)).buscaCategoriaPorId(categoriaId);
        verify(dao, never()).alterarCategoria(any());
    }

    @Test
    void editarCategoria_ErroAoAlterar() {
        // Arrange
        Long categoriaId = 1L;
        InclusaoCategoriaDTO dadosEdicao = new InclusaoCategoriaDTO("Categoria Atualizada");
        CategoriaModel categoriaExistente = new CategoriaModel();
        categoriaExistente.setId(categoriaId);
        categoriaExistente.setDescricao("Categoria Original");
        categoriaExistente.setDataInclusao(Calendar.getInstance());

        when(dao.buscaCategoriaPorId(categoriaId)).thenReturn(categoriaExistente);
        doThrow(new RuntimeException("Erro simulado ao alterar")).when(dao).alterarCategoria(any());

        // Act
        String resultado = editarCategoria.editarCategoria(categoriaId, dadosEdicao);

        // Assert
        assertEquals("Erro ao Editar", resultado);
        verify(dao, times(1)).buscaCategoriaPorId(categoriaId);
        verify(dao, times(1)).alterarCategoria(any());
    }

    @Test
    void editarCategoria_DescricaoVazia() {
        // Arrange
        Long categoriaId = 1L;
        InclusaoCategoriaDTO dadosEdicao = new InclusaoCategoriaDTO("");
        CategoriaModel categoriaExistente = new CategoriaModel();
        categoriaExistente.setId(categoriaId);
        categoriaExistente.setDescricao("Categoria Original");
        categoriaExistente.setDataInclusao(Calendar.getInstance());

        when(dao.buscaCategoriaPorId(categoriaId)).thenReturn(categoriaExistente);

        // Act
        String resultado = editarCategoria.editarCategoria(categoriaId, dadosEdicao);

        // Assert
        assertEquals("Editado com Sucesso", resultado);
        verify(dao, times(1)).buscaCategoriaPorId(categoriaId);
        verify(dao, times(1)).alterarCategoria(argThat(categoria ->
                categoria.getId().equals(categoriaId) &&
                        categoria.getDescricao().equals("") &&
                        categoria.getDataInclusao().equals(categoriaExistente.getDataInclusao()) &&
                        categoria.getDataAlteracao() != null
        ));
    }

    @Test
    void editarCategoria_DescricaoNula() {
        // Arrange
        Long categoriaId = 1L;
        InclusaoCategoriaDTO dadosEdicao = new InclusaoCategoriaDTO(null);
        CategoriaModel categoriaExistente = new CategoriaModel();
        categoriaExistente.setId(categoriaId);
        categoriaExistente.setDescricao("Categoria Original");
        categoriaExistente.setDataInclusao(Calendar.getInstance());

        when(dao.buscaCategoriaPorId(categoriaId)).thenReturn(categoriaExistente);

        // Act
        String resultado = editarCategoria.editarCategoria(categoriaId, dadosEdicao);

        // Assert
        assertEquals("Editado com Sucesso", resultado);
        verify(dao, times(1)).buscaCategoriaPorId(categoriaId);
        verify(dao, times(1)).alterarCategoria(argThat(categoria ->
                categoria.getId().equals(categoriaId) &&
                        categoria.getDescricao() == null &&
                        categoria.getDataInclusao().equals(categoriaExistente.getDataInclusao()) &&
                        categoria.getDataAlteracao() != null
        ));
    }
}