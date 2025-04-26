package com.projetoFastHub.fasthub.avaliacao;


import com.projetoFastHub.fasthub.adapters.avaliacao.AvaliacaoRepository;
import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.avaliacao.AvaliacaoDTO;
import com.projetoFastHub.fasthub.aplicacao.avaliacao.AvaliacaoModel;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.avaliacao.AvaliarEditar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvaliacaoEditarTest {

    @InjectMocks
    private AvaliarEditar avaliarEditar;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private SolicitacaoRepository solicitacaoRepository;

    @Test
    void editar_Sucesso() {
        // Arrange
        Long avaliacaoId = 1L;
        Long solicitacaoId = 2L;
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO( 5, "Serviço excelente!",solicitacaoId);

        AvaliacaoModel avaliacaoExistente = new AvaliacaoModel();
        avaliacaoExistente.setId(avaliacaoId);
        avaliacaoExistente.setComentario("Serviço bom");
        avaliacaoExistente.setNota(4);

        SolicitacaoModel solicitacaoExistente = new SolicitacaoModel();
        solicitacaoExistente.setId(solicitacaoId);
        User prestador = new User();
        prestador.setId(3L);
        solicitacaoExistente.setPrestador(prestador);

        when(avaliacaoRepository.buscaPorId(avaliacaoId)).thenReturn(avaliacaoExistente);
        when(solicitacaoRepository.buscaPorID(solicitacaoId)).thenReturn(solicitacaoExistente);

        // Act
        String resultado = avaliarEditar.editar(avaliacaoDTO, avaliacaoId);

        // Assert
        assertEquals("Editado com Sucesso", resultado);
        verify(avaliacaoRepository, times(1)).buscaPorId(avaliacaoId);
        verify(solicitacaoRepository, times(1)).buscaPorID(avaliacaoDTO.solicitacaoId());
        verify(avaliacaoRepository, times(1)).editar(argThat(avaliacao ->
                avaliacao.getId().equals(avaliacaoId) &&
                        avaliacao.getComentario().equals("Serviço excelente!") &&
                        avaliacao.getNota() == 5 &&
                        avaliacao.getSolicitacao().equals(solicitacaoExistente) &&
                        avaliacao.getPrestador().equals(prestador)
        ));
    }
    @Test
    void editar_SolicitacaoNaoEncontrada() {
        // Arrange
        Long avaliacaoId = 1L;
        Long solicitacaoId = 2L;
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(4,"Bom",  solicitacaoId);

        AvaliacaoModel avaliacaoExistente = new AvaliacaoModel();
        avaliacaoExistente.setId(avaliacaoId);
        avaliacaoExistente.setComentario("Ok");
        avaliacaoExistente.setNota(3);

        when(avaliacaoRepository.buscaPorId(avaliacaoId)).thenReturn(avaliacaoExistente);
        when(solicitacaoRepository.buscaPorID(solicitacaoId)).thenReturn(null);

        // Act
        String resultado = avaliarEditar.editar(avaliacaoDTO, avaliacaoId);

        // Assert
        assertEquals("Erro ao Editar Cannot invoke \"com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel.getPrestador()\" because \"solicitacaoModel\" is null", resultado);
        verify(avaliacaoRepository, times(1)).buscaPorId(avaliacaoId);
        verify(solicitacaoRepository, times(1)).buscaPorID(avaliacaoDTO.solicitacaoId());
        verify(avaliacaoRepository, never()).editar(any());
    }

    @Test
    void editar_ErroAoEditarAvaliacao() {
        // Arrange
        Long avaliacaoId = 1L;
        Long solicitacaoId = 2L;
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO( 5, "Excelente",solicitacaoId);

        AvaliacaoModel avaliacaoExistente = new AvaliacaoModel();
        avaliacaoExistente.setId(avaliacaoId);
        avaliacaoExistente.setComentario("Bom");
        avaliacaoExistente.setNota(4);

        SolicitacaoModel solicitacaoExistente = new SolicitacaoModel();
        solicitacaoExistente.setId(solicitacaoId);
        User prestador = new User();
        prestador.setId(3L);
        solicitacaoExistente.setPrestador(prestador);

        when(avaliacaoRepository.buscaPorId(avaliacaoId)).thenReturn(avaliacaoExistente);
        when(solicitacaoRepository.buscaPorID(solicitacaoId)).thenReturn(solicitacaoExistente);
        doThrow(new RuntimeException("Erro simulado ao editar")).when(avaliacaoRepository).editar(any());

        // Act
        String resultado = avaliarEditar.editar(avaliacaoDTO, avaliacaoId);

        // Assert
        assertEquals("Erro ao Editar Erro simulado ao editar", resultado);
        verify(avaliacaoRepository, times(1)).buscaPorId(avaliacaoId);
        verify(solicitacaoRepository, times(1)).buscaPorID(avaliacaoDTO.solicitacaoId());
        verify(avaliacaoRepository, times(1)).editar(any());
    }
}