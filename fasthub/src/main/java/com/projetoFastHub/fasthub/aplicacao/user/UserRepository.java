package com.projetoFastHub.fasthub.aplicacao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
   UserDetails findByLogin(String login);
   User findByEmail(String email);
}
