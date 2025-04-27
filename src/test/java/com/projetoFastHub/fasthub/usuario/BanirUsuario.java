package com.projetoFastHub.fasthub.usuario;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.usuario.BanirUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BanirUsuarioTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BanirUsuario banirUsuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBanirUsuario_Sucesso() {
        // Arrange
        Long userId = 1L;
        User usuario = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(usuario));

        // Act
        String resultado = banirUsuario.banirUsuario(userId);

        // Assert
        assertEquals("Usuário banido com sucesso", resultado);
        verify(userRepository, times(1)).delete(usuario);
    }

    @Test
    void testBanirUsuario_NaoEncontrado() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        String resultado = banirUsuario.banirUsuario(userId);

        // Assert
        assertEquals("Usuário não encontrado", resultado);
        verify(userRepository, never()).delete(any());
    }

    @Test
    void testBanirUsuario_Erro() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenThrow(new RuntimeException("Erro simulado"));

        // Act
        String resultado = banirUsuario.banirUsuario(userId);

        // Assert
        assertEquals("Erro ao tentar banir o usuário: Erro simulado", resultado);
    }
}
