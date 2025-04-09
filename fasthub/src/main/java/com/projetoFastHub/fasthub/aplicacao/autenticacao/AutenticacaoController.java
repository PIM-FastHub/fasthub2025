package com.projetoFastHub.fasthub.aplicacao.autenticacao;


import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
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

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("autenticacao")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CategoriaDAO categoriaDAO;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;


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
    public ResponseEntity register (@RequestBody RegisterDTO registerDTO){
        if(this.userRepository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();

        String senhaEncriptografada = new BCryptPasswordEncoder().encode(registerDTO.password());
        User novoUsuario = new User(registerDTO.login(),senhaEncriptografada, registerDTO.role());

       novoUsuario.setEmail(registerDTO.email());

       CategoriaModel categoriaModel = categoriaDAO.buscaCategoriaPorId(registerDTO.categoriaId());
       novoUsuario.setCategoriaModel(categoriaModel);

        this.userRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/listaUsuario")
    public ResponseEntity<List<User>> listaUsuarios(){
        List<User> lista = userRepository.findAll();
        return ResponseEntity.ok(lista);
    }
}
