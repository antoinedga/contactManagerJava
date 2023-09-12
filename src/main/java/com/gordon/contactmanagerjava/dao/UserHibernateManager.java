package com.gordon.contactmanagerjava.dao;

import com.gordon.contactmanagerjava.modal.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;


@Component
public class UserHibernateManager {

    private final EntityManagerFactory emf;

    public UserHibernateManager(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UserEntity getById(Integer id) {
        return getEntityManager().find(UserEntity.class,id);
    }

    public UserEntity findByUsersName(String firstName) {
        TypedQuery<UserEntity> query = getEntityManager().createQuery("SELECT a FROM user_entity a " + "" +
                "WHERE a.firstName = :first_name", UserEntity.class);
        query.setParameter("first_name", firstName);

        return query.getSingleResult();
    }


    public UserEntity saveNewUser(UserEntity userEntity) {
        EntityManager entityManager = this.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(userEntity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return userEntity;
    }

    public UserEntity updateUser(UserEntity userEntity) {
        EntityManager entityManager = this.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(userEntity);
        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();
    return userEntity;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
