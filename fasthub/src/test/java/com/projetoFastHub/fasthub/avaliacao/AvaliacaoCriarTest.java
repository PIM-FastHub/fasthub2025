package com.projetoFastHub.fasthub.avaliacao;


import com.projetoFastHub.fasthub.adapters.avaliacao.AvaliacaoRepository;
import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.avaliacao.AvaliacaoDTO;
import com.projetoFastHub.fasthub.aplicacao.avaliacao.AvaliacaoModel;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.avaliacao.AvaliacaoCriar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvaliacaoCriarTest {

    @InjectMocks
    private AvaliacaoCriar avaliacaoCriar;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private SolicitacaoRepository solicitacaoRepository;

    @Test
    void avaliar_Sucesso() {
        // Arrange
        Long solicitacaoId = 1L;
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(5, "Ótimo serviço!",solicitacaoId);

        SolicitacaoModel solicitacaoExistente = new SolicitacaoModel();
        solicitacaoExistente.setId(solicitacaoId);
        User prestador = new User();
        prestador.setId(2L);
        solicitacaoExistente.setPrestador(prestador);

        AvaliacaoModel avaliacaoSalva = new AvaliacaoModel();
        avaliacaoSalva.setComentario("Ótimo serviço!");
        avaliacaoSalva.setNota(5);
        avaliacaoSalva.setSolicitacao(solicitacaoExistente);
        avaliacaoSalva.setPrestador(prestador);

        when(solicitacaoRepository.buscaPorID(solicitacaoId)).thenReturn(solicitacaoExistente);

        // Act
        String resultado = avaliacaoCriar.avaliar(avaliacaoDTO);

        // Assert
        assertEquals("Avaliado Com Sucesso", resultado);
        verify(solicitacaoRepository, times(1)).buscaPorID(solicitacaoId);
        verify(avaliacaoRepository, times(1)).inserir(argThat(avaliacao ->
                avaliacao.getComentario().equals("Ótimo serviço!") &&
                        avaliacao.getNota() == 5 &&
                        avaliacao.getSolicitacao().equals(solicitacaoExistente) &&
                        avaliacao.getPrestador().equals(prestador)
        ));
        verify(solicitacaoRepository, times(1)).altera(argThat(solicitacao ->
                solicitacao.getId().equals(solicitacaoId) &&
                        solicitacao.getAvaliacao() != null && // Verificamos que a avaliação foi associada
                        solicitacao.getAvaliacao().getComentario().equals("Ótimo serviço!") &&
                        solicitacao.getAvaliacao().getNota() == 5
        ));
    }

    @Test
    void avaliar_SolicitacaoNaoEncontrada() {
        // Arrange
        Long solicitacaoId = 1L;
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO( 1, "Ruim",solicitacaoId);

        when(solicitacaoRepository.buscaPorID(solicitacaoId)).thenReturn(null);

        // Act
        String resultado = avaliacaoCriar.avaliar(avaliacaoDTO);

        // Assert
        assertEquals("Erro ao Avaliar: Cannot invoke \"com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel.getPrestador()\" because \"solicitacao\" is null", resultado);
        verify(solicitacaoRepository, times(1)).buscaPorID(solicitacaoId);
        verify(avaliacaoRepository, never()).inserir(any());
        verify(solicitacaoRepository, never()).altera(any());
    }

    @Test
    void avaliar_ErroAoInserirAvaliacao() {
        // Arrange
        Long solicitacaoId = 1L;
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO( 3,"Mediano", solicitacaoId); // Inverti a ordem dos argumentos para corresponder ao AvaliacaoDTO

        SolicitacaoModel solicitacaoExistente = new SolicitacaoModel();
        solicitacaoExistente.setId(solicitacaoId);
        User prestador = new User();
        prestador.setId(2L);
        solicitacaoExistente.setPrestador(prestador);

        when(solicitacaoRepository.buscaPorID(solicitacaoId)).thenReturn(solicitacaoExistente);
        doThrow(new RuntimeException("Erro ao inserir avaliação")).when(avaliacaoRepository).inserir(any(AvaliacaoModel.class)); // Use doThrow para métodos void

        // Act
        String resultado = avaliacaoCriar.avaliar(avaliacaoDTO);

        // Assert
        assertEquals("Erro ao Avaliar: Erro ao inserir avaliação", resultado);
        verify(solicitacaoRepository, times(1)).buscaPorID(solicitacaoId);
        verify(avaliacaoRepository, times(1)).inserir(any(AvaliacaoModel.class));
        verify(solicitacaoRepository, never()).altera(any());
    }
    @Test
    void avaliar_ErroAoAlterarSolicitacao() {
        // Arrange
        Long solicitacaoId = 1L;
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(4, "Bom", solicitacaoId); // Inverti a ordem dos argumentos

        SolicitacaoModel solicitacaoExistente = new SolicitacaoModel();
        solicitacaoExistente.setId(solicitacaoId);
        User prestador = new User();
        prestador.setId(2L);
        solicitacaoExistente.setPrestador(prestador);

        AvaliacaoModel avaliacaoSalva = new AvaliacaoModel();
        avaliacaoSalva.setComentario("Bom");
        avaliacaoSalva.setNota(4);
        avaliacaoSalva.setSolicitacao(solicitacaoExistente);
        avaliacaoSalva.setPrestador(prestador);

        when(solicitacaoRepository.buscaPorID(solicitacaoId)).thenReturn(solicitacaoExistente);
        // Não precisamos simular o retorno de um método void
        doNothing().when(avaliacaoRepository).inserir(any(AvaliacaoModel.class)); // Usamos doNothing para métodos void que não lançam exceção imediatamente
        doThrow(new RuntimeException("Erro ao alterar solicitação")).when(solicitacaoRepository).altera(any());

        // Act
        String resultado = avaliacaoCriar.avaliar(avaliacaoDTO);

        // Assert
        assertEquals("Erro ao Avaliar: Erro ao alterar solicitação", resultado);
        verify(solicitacaoRepository, times(1)).buscaPorID(solicitacaoId);
        verify(avaliacaoRepository, times(1)).inserir(any(AvaliacaoModel.class));
        verify(solicitacaoRepository, times(1)).altera(any());
    }
}