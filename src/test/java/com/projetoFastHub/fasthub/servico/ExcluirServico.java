package com.projetoFastHub.fasthub.servico;
import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico.ExcluirServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExcluirServicoTest {

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private ExcluirServico excluirServico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExcluirServico_Sucesso() {
        // Arrange
        Long servicoId = 1L;
        ServicoModel servicoMock = new ServicoModel();

        when(servicoRepository.buscaServicoPorId(servicoId)).thenReturn(servicoMock);

        // Act
        String resultado = excluirServico.excluirServico(servicoId);

        // Assert
        assertEquals("Excluido com Sucesso", resultado);
        verify(servicoRepository, times(1)).buscaServicoPorId(servicoId);
        verify(servicoRepository, times(1)).excluirServico(servicoMock);
    }

    @Test
    void testExcluirServico_ServicoNaoEncontrado() {
        // Arrange
        Long servicoId = 1L;

        // Simula que o serviço não é encontrado
        when(servicoRepository.buscaServicoPorId(servicoId)).thenReturn(null);

        // Act
        String resultado = excluirServico.excluirServico(servicoId);

        // Assert
        assertEquals("Erro ao Excluir Servico: Serviço não encontrado", resultado);
        verify(servicoRepository, times(1)).buscaServicoPorId(servicoId);
        verify(servicoRepository, never()).excluirServico(any());
    }

    @Test
    void testExcluirServico_ErroAoExcluir() {
        // Arrange
        Long servicoId = 1L;
        ServicoModel servicoMock = new ServicoModel();

        when(servicoRepository.buscaServicoPorId(servicoId)).thenReturn(servicoMock);
        doThrow(new RuntimeException("Erro simulado")).when(servicoRepository).excluirServico(servicoMock);

        // Act
        String resultado = excluirServico.excluirServico(servicoId);

        // Assert
        assertEquals("Erro ao Excluir Servico Erro simulado", resultado);
        verify(servicoRepository, times(1)).buscaServicoPorId(servicoId);
        verify(servicoRepository, times(1)).excluirServico(servicoMock);
    }
}
