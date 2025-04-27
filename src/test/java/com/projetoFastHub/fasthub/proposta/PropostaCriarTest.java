package com.projetoFastHub.fasthub.proposta;
import com.projetoFastHub.fasthub.adapters.proposta.PropostaRepository;
import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaCriarDTO;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaModel;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaStatusEnum;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.proposta.PropostaCriar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PropostaCriarTest {

    @InjectMocks
    private PropostaCriar propostaCriar; // Classe que será testada, com mocks injetados

    @Mock
    private PropostaRepository propostaRepository; // Mock do repositório de propostas

    @Mock
    private UserRepository userRepository; // Mock do repositório de usuários

    @Mock
    private SolicitacaoRepository solicitacaoRepository; // Mock do repositório de solicitações

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void deveCriarPropostaComSucesso() {
        // Arrange (Preparação dos dados)
        PropostaCriarDTO propostaCriarDTO = new PropostaCriarDTO();
        propostaCriarDTO.setValor(1000.0);
        propostaCriarDTO.setSolicitacaoId(1L);
        propostaCriarDTO.setIdPrestador(1L);

        PropostaModel solicitacaoMock = new PropostaModel();
        PropostaModel prestadorMock = new PropostaModel();


        // Act (Execução do método que será testado)
        String resultado = propostaCriar.criarProposta(propostaCriarDTO);

        // Assert (Verificação do resultado esperado)
        assertEquals("Proposta Enviada", resultado);
        verify(propostaRepository, times(1)).salvar(any(PropostaModel.class));
    }


}
