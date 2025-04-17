package com.projetoFastHub.fasthub.aplicacao.user;

import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {


    @PersistenceContext
    private EntityManager manager;

    public UserDAO(EntityManager manager) {
        this.manager = manager;
    }


    public User findByEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email =: email ";
        TypedQuery<User> query = this.manager.createQuery(jpql, User.class);
        query.setParameter("email", email);
        User resultado = (User) query.getSingleResult();
        if (resultado != null) {
            return resultado;
        }
        return null;
        }


    public User findByLogin(String login){
        String jpql = "SELECT u FROM User u WHERE u.login =:login ";
        TypedQuery<User> query = this.manager.createQuery(jpql, User.class);
        query.setParameter("login", login);
        User resultado = (User) query.getSingleResult();
        if (resultado != null) {
            return resultado;
        }
        return null;
    }




}
