package com.projetoFastHub.fasthub.aplicacao.autenticacao;


import com.projetoFastHub.fasthub.aplicacao.Endereco.Endereco;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.StatusEnum;
import com.projetoFastHub.fasthub.aplicacao.user.StatusUsuario;
import com.projetoFastHub.fasthub.aplicacao.user.UserDAO;
import com.projetoFastHub.fasthub.infra.seguranca.EmailServiceTeste;
import com.projetoFastHub.fasthub.infra.seguranca.TokenService;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CategoriaDAO categoriaDAO;


    @Autowired
    EmailServiceTeste emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    ResetTokenRepository resetSenhaTokenRepository;


    @Autowired
    UserDAO dao;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AutenticacaoDTO autenticacaoDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken
                (autenticacaoDTO.login(), autenticacaoDTO.password());

        UserDetails usuario =  userRepository.findByLogin(autenticacaoDTO.login());

        var autenticacao = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.geradorToken((User) autenticacao.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token, usuario.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDTO registerDTO){
        if(this.userRepository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();

        String senhaEncriptografada = new BCryptPasswordEncoder().encode(registerDTO.password());
        User novoUsuario = new User(registerDTO.login(),senhaEncriptografada, registerDTO.role());

       novoUsuario.setEmail(registerDTO.email());

       CategoriaModel categoriaModel = categoriaDAO.buscaCategoriaPorId(registerDTO.categoriaId());
       novoUsuario.setCategoriaModel(categoriaModel);
       novoUsuario.setNome(registerDTO.nome());
       novoUsuario.setSobrenome(registerDTO.sobrenome());
       novoUsuario.setDocumento(registerDTO.documento());
       Endereco endereco = new Endereco ();
       endereco.setCep(registerDTO.cep());
       endereco.setEstado(registerDTO.estado());
       endereco.setRua(registerDTO.endereco());
       novoUsuario.setEndereco(endereco);
       novoUsuario.setStatusEnum(StatusUsuario.ATIVO);

        this.userRepository.save(novoUsuario);
        return ResponseEntity.ok("CRIADO");
    }

    @GetMapping("/api/listaUsuario")
    public ResponseEntity<List<User>> listaUsuarios(){
        List<User> lista = userRepository.findAll();
        return ResponseEntity.ok(lista);
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPasswordRequest(@RequestParam String email) {
        User user = dao.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }

        // Gerar o token para o usuário
        String token = tokenService.geradorToken(user);

        // Salvar o token no banco de dados
        ResetToken resetSenhaToken = new ResetToken();
        resetSenhaToken.setUser(user);
        resetSenhaToken.setToken(token);
        resetSenhaToken.setDataCriacao(LocalDateTime.now());
        resetSenhaToken.setDataExpiracao(LocalDateTime.now().plusHours(1)); // Token expira em 1 hora
        resetSenhaToken.setUsado(false);

        resetSenhaTokenRepository.save(resetSenhaToken);

        // Criar o link para o reset de senha
        String resetLink = "http://seu-dominio.com/reset-password/confirm?token=" + token;

        // Corpo do e-mail
        String corpoEmail = "Clique no link abaixo para resetar sua senha:\n" + resetLink;

        // Enviar o e-mail com o token
        emailService.enviarEmail(user.getEmail(), "Reset de Senha", corpoEmail);

        return ResponseEntity.ok("Token de reset de senha enviado para o e-mail.");
    }


    // Endpoint para realizar o reset de senha
    @PostMapping("/reset-password/confirm")
    public ResponseEntity<String> confirmResetPassword(@RequestParam String token, @RequestParam String novaSenha) {
        ResetToken resetSenhaToken = resetSenhaTokenRepository.findByToken(token);

        if (resetSenhaToken == null) {
            return ResponseEntity.badRequest().body("Token inválido.");
        }

        if (resetSenhaToken.isUsado()) {
            return ResponseEntity.badRequest().body("Este token já foi usado.");
        }

        if (resetSenhaToken.getDataExpiracao().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expirado.");
        }

        User user = resetSenhaToken.getUser();
        user.setSenha(new BCryptPasswordEncoder().encode(novaSenha)); // Encriptar a nova senha
        userRepository.save(user); // Salvar a nova senha no banco de dados

        // Marcar o token como usado
        resetSenhaToken.setUsado(true);
        resetSenhaTokenRepository.save(resetSenhaToken);

        // Enviar confirmação de alteração de senha para o e-mail do usuário
        String corpoEmail = "Sua senha foi alterada com sucesso.";
        emailService.enviarEmail(user.getEmail(), "Senha Alterada", corpoEmail);

        return ResponseEntity.ok("Senha alterada com sucesso.");
    }

}
