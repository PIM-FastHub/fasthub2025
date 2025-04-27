package com.projeto.fastHub.fastHub.proposta;
import com.projetoFastHub.fasthub.adapters.proposta.PropostaRepository;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaEditarDTO;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaModel;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaStatusEnum;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.proposta.PropostaEditar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PropostaEditarTest {

    @InjectMocks
    private PropostaEditar propostaEditar; // Classe que será testada

    @Mock
    private PropostaRepository propostaRepository; // Mock do repositório

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks antes de cada teste
    }

    @Test
    void deveEditarPropostaComSucesso() {
        // Arrange
        Long id = 1L;

        PropostaEditarDTO dto = new PropostaEditarDTO();
        dto.setValor(1200.0);
        dto.setDescricao("Descrição Atualizada");

        PropostaModel propostaExistente = new PropostaModel();
        propostaExistente.setId(id);
        propostaExistente.setValorProposta(1000.0);
        propostaExistente.setDescricao("Descrição Antiga");
        propostaExistente.setDataAlteracao(null);

        when(propostaRepository.buscarPorId(id)).thenReturn(propostaExistente);

        // Act
        String resultado = propostaEditar.editar(dto, id);

        // Assert
        assertEquals("Editado com sucesso", resultado);
        verify(propostaRepository, times(1)).editar(propostaExistente);
        assertEquals(1200.0, propostaExistente.getValorProposta());
        assertEquals("Descrição Atualizada", propostaExistente.getDescricao());
        assertNotNull(propostaExistente.getDataAlteracao()); // Garantir que a data de alteração foi atualizada
    }

    @Test
    void deveRetornarErroQuandoPropostaNaoExistir() {
        // Arrange
        Long id = 2L;

        PropostaEditarDTO dto = new PropostaEditarDTO();
        dto.setValor(1500.0);
        dto.setDescricao("Descrição Nova");

        // Simula exceção ao buscar proposta inexistente
        when(propostaRepository.buscarPorId(id)).thenThrow(new RuntimeException("Proposta não encontrada"));

        // Act
        String resultado = propostaEditar.editar(dto, id);

        // Assert
        assertEquals("Erro ao editar Proposta não encontrada", resultado);
        verify(propostaRepository, never()).editar(any(PropostaModel.class));
    }

    @Test
    void deveEditarComDescricaoENovoStatus() {
        // Arrange
        Long id = 5L;

        PropostaEditarDTO dto = new PropostaEditarDTO();
        dto.setValor(1200.0);
        dto.setDescricao("Nova descrição atualizada");
        dto.setPropostaStatusEnum(PropostaStatusEnum.ACEITA);
// <- Novo status!

        PropostaModel propostaExistente = new PropostaModel();
        propostaExistente.setId(id);
        propostaExistente.setValorProposta(1000.0);
        propostaExistente.setDescricao("Descrição Antiga");
        propostaExistente.setStatusPropostaEnum(PropostaStatusEnum. AGUARDANDO_RESPOSTA);

        when(propostaRepository.buscarPorId(id)).thenReturn(propostaExistente);

        // Act
        String resultado = propostaEditar.editar(dto, id);

        // Assert
        assertEquals("Editado com sucesso", resultado);
        verify(propostaRepository, times(1)).editar(propostaExistente);

        // Verifica as atualizações
        assertEquals(1200.0, propostaExistente.getValorProposta());
        assertEquals("Nova descrição atualizada", propostaExistente.getDescricao());
        assertEquals(PropostaStatusEnum.ACEITA, propostaExistente.getStatusPropostaEnum());
        assertNotNull(propostaExistente.getDataAlteracao());
    }
}


