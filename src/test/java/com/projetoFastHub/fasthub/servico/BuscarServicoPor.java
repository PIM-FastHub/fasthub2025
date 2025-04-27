package com.projetoFastHub.fasthub.servico;
import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico.BuscaServicoPor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BuscaServicoPorTest {

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private BuscaServicoPor buscaServicoPor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPorId_Sucesso() {
        // Arrange
        Long id = 1L;
        ServicoModel servicoEsperado = new ServicoModel();
        servicoEsperado.setId(id);
        servicoEsperado.setNome("Serviço Teste");

        when(servicoRepository.buscaServicoPorId(id)).thenReturn(servicoEsperado);

        // Act
        ServicoModel resultado = buscaServicoPor.porId(id);

        // Assert
        assertEquals(servicoEsperado, resultado);
        verify(servicoRepository, times(1)).buscaServicoPorId(id);
    }

    @Test
    void testPorId_Nulo() {
        // Arrange
        Long id = 1L;

        when(servicoRepository.buscaServicoPorId(id)).thenReturn(null);

        // Act
        ServicoModel resultado = buscaServicoPor.porId(id);

        // Assert
        assertEquals(null, resultado);
        verify(servicoRepository, times(1)).buscaServicoPorId(id);
    }
}


