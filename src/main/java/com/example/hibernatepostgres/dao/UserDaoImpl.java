package com.example.hibernatepostgres.dao;

import com.example.hibernatepostgres.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<User> getUserDetails() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root contactRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(contactRoot);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public User createUser(User user) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.save(user);
        return user;
    }
}
