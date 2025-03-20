//package com.projetoFastHub.fasthub.autenticacao;
//
//import com.projetoFastHub.fasthub.user.User;
//import com.projetoFastHub.fasthub.user.UserRepository;
//import com.projetoFastHub.fasthub.util.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("autenticacao")
//public class ResetSenhaController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private ResetSenhaRepository tokenRepository;
//
//
//    @PostMapping("/esqueciSenha")
//    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//       // User user = userRepository.findByEmail(email);
//
////        if (user == null) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
////        }
//
//        // Gerar um token único e definir a expiração
//        String token = UUID.randomUUID().toString();
//        PasswordResetToken resetToken = new PasswordResetToken(token, user, LocalDateTime.now().plusHours(1));
//        tokenRepository.save(resetToken);
//
//        // Enviar e-mail com o link para redefinição
//        String resetLink = "http://localhost:8081/autenticacao/reset-senha?token=" + token;
//        emailService.sendEmail(user.getEmail(), "Redefinição de Senha",
//                "Clique no link para redefinir sua senha: " + resetLink);
//
//        return ResponseEntity.ok("E-mail de redefinição de senha enviado.");
//    }
//
//
//
//    @PostMapping("/reset-senha")
//    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
//        String token = request.get("token");
//        String newPassword = request.get("newPassword");
//
//        PasswordResetToken resetToken = tokenRepository.findByToken(token);
//
//        if (resetToken == null || resetToken.isExpired()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido ou expirado.");
//        }
//
//        // Atualiza a senha do usuário
//        User user = resetToken.getUser();
//        user.setSenha(new BCryptPasswordEncoder().encode(newPassword));
//        userRepository.save(user);
//
//        // Remove o token usado
//        tokenRepository.delete(resetToken);
//
//        return ResponseEntity.ok("Senha redefinida com sucesso.");
//    }
//
//
//}
