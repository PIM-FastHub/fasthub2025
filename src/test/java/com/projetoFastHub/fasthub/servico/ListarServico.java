package com.projetoFastHub.fasthub.servico;
import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico.ListarServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListarServicoTest {

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private ListarServico listarServico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListaTodos_Sucesso() {
        // Arrange
        ServicoModel servico1 = new ServicoModel();
        servico1.setId(1L);
        servico1.setNome("Serviço 1");

        ServicoModel servico2 = new ServicoModel();
        servico2.setId(2L);
        servico2.setNome("Serviço 2");

        List<ServicoModel> servicoList = Arrays.asList(servico1, servico2);

        when(servicoRepository.listaTodosServicos()).thenReturn(servicoList);

        // Act
        List<ServicoModel> resultado = listarServico.listaTodos();

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("Serviço 1", resultado.get(0).getNome());
        assertEquals("Serviço 2", resultado.get(1).getNome());
        verify(servicoRepository, times(1)).listaTodosServicos();
    }

    @Test
    void testListaTodos_ListaVazia() {
        // Arrange
        when(servicoRepository.listaTodosServicos()).thenReturn(Arrays.asList());

        // Act
        List<ServicoModel> resultado = listarServico.listaTodos();

        // Assert
        assertEquals(0, resultado.size());
        verify(servicoRepository, times(1)).listaTodosServicos();
    }
}
