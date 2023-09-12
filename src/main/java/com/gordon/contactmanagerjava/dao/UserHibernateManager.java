package com.gordon.contactmanagerjava.dao;

import com.gordon.contactmanagerjava.modal.UserEntity;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;


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

    public void deleteUserEntity(int id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        UserEntity user = em.find(UserEntity.class, id);
        em.remove(user);
        em.flush();
        em.getTransaction().commit();
        System.out.println("finished");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<UserEntity> listUserEntityWithLastName(String lastName) {
        EntityManager em = getEntityManager();
        List<UserEntity> users = null;
        try {
            Query query = em.createQuery("SELECT a FROM user_entity a where a.lastName like :last_name");
            query.setParameter("last_name", lastName + "%");
            users = query.getResultList();
        } catch (Exception ex) {

        } finally {
            em.close();
        }
        return users;
    }

    public List<UserEntity> findAll() {
        EntityManager em = this.getEntityManager();
        try {
            TypedQuery<UserEntity> query = em.createNamedQuery("user_find_all", UserEntity.class);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }

    public UserEntity findByName(String firstName) {
        EntityManager em = this.getEntityManager();
        try {
            TypedQuery<UserEntity> query = em.createNamedQuery("find_by_name", UserEntity.class);
            query.setParameter("first_name", firstName);
            return query.getSingleResult();
        }
        finally {
            em.close();
        }
    }

    public UserEntity findByNameWithCriteria(String firstName) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
            // from statement
            Root<UserEntity> root = query.from(UserEntity.class);
            // where clause parameter
            ParameterExpression<String> firstNameParameter = cb.parameter(String.class);
            Predicate firstNamePred = cb.equal(root.get("firstName"), firstNameParameter);
            // combining and doing the query select * from  User_entity where
            query.select(root).where(firstNamePred);

            TypedQuery<UserEntity> typedQuery = em.createQuery(query);
            typedQuery.setParameter(firstNameParameter, firstName);
            return typedQuery.getSingleResult();

        } finally {
            em.close();
        }
    }

    public UserEntity findByLastNameWithNative(String lastName) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery("Select * from user_entity u WHERE u.last_name = ?", UserEntity.class);
            query.setParameter(1, lastName);
            return (UserEntity) query.getSingleResult();
        } finally {
            em.close();
        }
    }
}
