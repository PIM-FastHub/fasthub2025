package com.projetoFastHub.fasthub.servico;
import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoResponseDTO;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico.EditarServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EditarServicoTest {

    @Mock
    private ServicoRepository servicoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private EditarServico editarServico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEditarServico_Sucesso() {
        // Arrange
        Long servicoId = 1L;
        ServicoResponseDTO data = new ServicoResponseDTO(
                "Novo Nome", // Nome
                "Nova Descrição", // Descrição
                2L // Categoria ID
        );

        ServicoModel servicoModel = new ServicoModel();
        CategoriaModel categoriaModel = new CategoriaModel();

        when(servicoRepository.buscaServicoPorId(servicoId)).thenReturn(servicoModel);
        when(categoriaRepository.buscaCategoriaPorId(2L)).thenReturn(categoriaModel);

        // Act
        String resultado = editarServico.editarServico(servicoId, data);

        // Assert
        assertEquals("Alterado com Sucesso", resultado);
        verify(servicoRepository, times(1)).buscaServicoPorId(servicoId);
        verify(categoriaRepository, times(1)).buscaCategoriaPorId(2L);
        verify(servicoRepository, times(1)).alteraServico(any(ServicoModel.class));
    }

    @Test
    void testEditarServico_ServicoNaoEncontrado() {
        // Arrange
        Long servicoId = 1L;
        ServicoResponseDTO data = new ServicoResponseDTO(
                "Nome do Serviço", // Nome
                "Descrição do Serviço", // Descrição
                2L // Categoria ID
        );

        // Simula que o serviço não é encontrado
        when(servicoRepository.buscaServicoPorId(servicoId)).thenReturn(null);

        // Act
        String resultado = editarServico.editarServico(servicoId, data);

        // Assert
        assertEquals("Erro ao Editar: Serviço não encontrado", resultado);
        verify(servicoRepository, times(1)).buscaServicoPorId(servicoId);
        verify(categoriaRepository, never()).buscaCategoriaPorId(anyLong());
        verify(servicoRepository, never()).alteraServico(any());
    }

    @Test
    void testEditarServico_ErroNoRepositorio() {
        // Arrange
        Long servicoId = 1L;
        ServicoResponseDTO data = new ServicoResponseDTO(
                "Nome com Erro", // Nome
                "Descrição com Erro", // Descrição
                2L // Categoria ID
        );

        ServicoModel servicoModel = new ServicoModel();
        CategoriaModel categoriaModel = new CategoriaModel();

        when(servicoRepository.buscaServicoPorId(servicoId)).thenReturn(servicoModel);
        when(categoriaRepository.buscaCategoriaPorId(2L)).thenReturn(categoriaModel);
        doThrow(new RuntimeException("Erro simulado")).when(servicoRepository).alteraServico(any(ServicoModel.class));

        // Act
        String resultado = editarServico.editarServico(servicoId, data);

        // Assert
        assertEquals("Erro ao Editar Erro simulado", resultado);
        verify(servicoRepository, times(1)).buscaServicoPorId(servicoId);
        verify(categoriaRepository, times(1)).buscaCategoriaPorId(2L);
        verify(servicoRepository, times(1)).alteraServico(any());
    }
}