package com.projetoFastHub.fasthub.usuario;
import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.aplicacao.Endereco.Endereco;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.user.DadosPerfilDTO;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.usuario.DadosMeuPerfi_Imp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DadosMeuPerfiImpTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private DadosMeuPerfi_Imp dadosMeuPerfiImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetornaDadosMeuPerfil() {
        // Arrange
        User user = new User();
        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        endereco.setRua("Rua das Flores");
        endereco.setEstado("SP");
        user.setEndereco(endereco);

        CategoriaModel categoria = new CategoriaModel();
        categoria.setId(1L);
        user.setCategoria(categoria);

        user.setNome("João");
        user.setSobrenome("Silva");
        user.setEmail("joao.silva@example.com");
        user.setTelefone("123456789");

        // Act
        DadosPerfilDTO dto = dadosMeuPerfiImp.retornaDadosMeuPerfil(user);

        // Assert
        assertNotNull(dto);
        assertEquals("João", dto.nome());
        assertEquals("Silva", dto.sobrenome());
        assertEquals("12345-678", dto.cep());
        assertEquals("Rua das Flores", dto.rua());
        assertEquals("SP", dto.estado());
        assertEquals(1L, dto.categoriaId());
        assertEquals("joao.silva@example.com", dto.email());
        assertEquals("123456789", dto.telefone());
    }

    @Test
    void testAlterar_Sucesso() {
        // Arrange
        Long userId = 1L;
        DadosPerfilDTO dto = new DadosPerfilDTO(
                "João",
                "Silva",
                "12345-678",
                "Rua das Flores",
                "SP",
                1L,
                "joao.silva@example.com",
                "123456789"
        );

        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        CategoriaModel categoria = new CategoriaModel();
        when(categoriaRepository.buscaCategoriaPorId(1L)).thenReturn(categoria);

        // Act
        User resultado = dadosMeuPerfiImp.alterar(dto, userId);

        // Assert
        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        assertEquals("Silva", resultado.getSobrenome());
        assertEquals(categoria, resultado.getCategoria());
        assertEquals("joao.silva@example.com", resultado.getEmail());
        assertEquals("123456789", resultado.getTelefone());
        assertEquals("12345-678", resultado.getEndereco().getCep());
        assertEquals("Rua das Flores", resultado.getEndereco().getRua());
        assertEquals("SP", resultado.getEndereco().getEstado());
        verify(userRepository, times(1)).save(resultado);
    }

    @Test
    void testAlterar_Erro() {
        // Arrange
        Long userId = 1L;
        DadosPerfilDTO dto = new DadosPerfilDTO(
                "João",
                "Silva",
                null,
                null,
                null,
                null,
                "joao.silva@example.com",
                "123456789"
        );
        when(userRepository.findById(userId)).thenThrow(new RuntimeException("Erro simulado"));

        // Act
        User resultado = dadosMeuPerfiImp.alterar(dto, userId);

        // Assert
        assertNull(resultado);
    }
}
