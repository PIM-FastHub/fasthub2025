package com.projetoFastHub.fasthub.autenticacao;


import com.projetoFastHub.fasthub.infra.seguranca.TokenService;
import com.projetoFastHub.fasthub.user.User;
import com.projetoFastHub.fasthub.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("autenticacao")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AutenticacaoDTO autenticacaoDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken
                (autenticacaoDTO.login(), autenticacaoDTO.password());

        var autenticacao = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.geradorToken((User) autenticacao.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody RegisterDTO registerDTO){
        if(this.userRepository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();

        String senhaEncriptografada = new BCryptPasswordEncoder().encode(registerDTO.password());
        User novoUsuario = new User(registerDTO.login(),senhaEncriptografada, registerDTO.role());

        this.userRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/listaUsuario")
    public ResponseEntity<List<User>> listaUsuarios(){
        List<User> lista = userRepository.findAll();
        return ResponseEntity.ok(lista);
    }
}
