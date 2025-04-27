package com.projetoFastHub.fasthub.servico;
import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoResponseDTO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico.CriarServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CriarServicoTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private CriarServico criarServico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarServico_Sucesso() {
        // Arrange
        ServicoResponseDTO servicoResponseDTO = new ServicoResponseDTO(
                "Nome do Serviço", // Nome
                "Descrição do Serviço", // Descrição
                1L // Categoria ID
        );

        CategoriaModel categoriaModel = new CategoriaModel();
        when(categoriaRepository.buscaCategoriaPorId(1L)).thenReturn(categoriaModel);

        // Act
        String resultado = criarServico.criarServico(servicoResponseDTO);

        // Assert
        assertEquals("Serviço criado com sucesso", resultado);
        verify(categoriaRepository, times(1)).buscaCategoriaPorId(1L);
        verify(servicoRepository, times(1)).inserirServico(any(ServicoModel.class));
    }

    @Test
    void testCriarServico_CategoriaNaoEncontrada() {
        // Arrange
        ServicoResponseDTO servicoResponseDTO = new ServicoResponseDTO(
                "Serviço Inválido", // Nome
                "Descrição Inválida", // Descrição
                2L // Categoria ID não existente
        );

        when(categoriaRepository.buscaCategoriaPorId(2L)).thenReturn(null);

        // Act
        String resultado = criarServico.criarServico(servicoResponseDTO);

        // Assert
        assertEquals("Erro ao Incluir null", resultado); // Aqui valida a mensagem de erro
        verify(categoriaRepository, times(1)).buscaCategoriaPorId(2L);
        verify(servicoRepository, never()).inserirServico(any());
    }

    @Test
    void testCriarServico_ErroNoRepositorio() {
        // Arrange
        ServicoResponseDTO servicoResponseDTO = new ServicoResponseDTO(
                "Serviço com erro", // Nome
                "Descrição com erro", // Descrição
                1L // Categoria ID válido
        );

        CategoriaModel categoriaModel = new CategoriaModel();
        when(categoriaRepository.buscaCategoriaPorId(1L)).thenReturn(categoriaModel);
        doThrow(new RuntimeException("Erro simulado")).when(servicoRepository).inserirServico(any(ServicoModel.class));

        // Act
        String resultado = criarServico.criarServico(servicoResponseDTO);

        // Assert
        assertEquals("Erro ao Incluir Erro simulado", resultado);
        verify(categoriaRepository, times(1)).buscaCategoriaPorId(1L);
        verify(servicoRepository, times(1)).inserirServico(any());
    }
}