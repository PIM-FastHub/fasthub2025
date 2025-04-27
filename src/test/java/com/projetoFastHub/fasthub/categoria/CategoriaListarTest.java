package com.projetoFastHub.fasthub.categoria;


import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria.ListarCategoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoriaListarTest {

    @InjectMocks
    private ListarCategoria listarCategoria;

    @Mock
    private CategoriaDAO dao;

    @Test
    void listaTodasCategorias_ComCategoriasExistentes() {
        // Arrange
        CategoriaModel categoria1 = new CategoriaModel();
        categoria1.setId(1L);
        categoria1.setDescricao("Categoria A");

        CategoriaModel categoria2 = new CategoriaModel();
        categoria2.setId(2L);
        categoria2.setDescricao("Categoria B");

        List<CategoriaModel> categoriasSimuladas = Arrays.asList(categoria1, categoria2);
        when(dao.listarTodasCategorias()).thenReturn(categoriasSimuladas);

        // Act
        List<CategoriaModel> resultado = listarCategoria.listaTodasCategorias();

        // Assert
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(categoria1));
        assertTrue(resultado.contains(categoria2));
        verify(dao, times(1)).listarTodasCategorias();
    }

    @Test
    void listaTodasCategorias_SemCategoriasExistentes() {
        // Arrange
        when(dao.listarTodasCategorias()).thenReturn(Collections.emptyList());

        // Act
        List<CategoriaModel> resultado = listarCategoria.listaTodasCategorias();

        // Assert
        assertTrue(resultado.isEmpty());
        verify(dao, times(1)).listarTodasCategorias();
    }

    @Test
    void listaTodasCategorias_ErroAoListar() {
        // Arrange
        when(dao.listarTodasCategorias()).thenThrow(new RuntimeException("Erro ao acessar o banco"));

        // Act
        List<CategoriaModel> resultado = null;
        try {
            resultado = listarCategoria.listaTodasCategorias();
        } catch (Exception e) {
            assertEquals("Erro ao acessar o banco", e.getMessage());
        }

        // Assert
        assertEquals(null, resultado); // Ou verifique se a exceção foi lançada corretamente
        verify(dao, times(1)).listarTodasCategorias();
    }
}