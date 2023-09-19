package com.gordon.contactmanagerjava.dao;

import com.gordon.contactmanagerjava.modal.ContactEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ContactHibernateManager {

    private final EntityManagerFactory emf;

    public ContactHibernateManager(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<ContactEntity> findAllContacts(Pageable request) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<ContactEntity> typedQuery = em.createQuery("Select b from contact_entity b", ContactEntity.class);
            typedQuery.setFirstResult(Math.toIntExact(request.getOffset()));
            typedQuery.setMaxResults(request.getPageSize());
            return typedQuery.getResultList();
        } finally {
            em.close();
        }
    }

    public List<ContactEntity> findAllContactsSortBy(Pageable request) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<ContactEntity> typedQuery = em.createQuery("Select b from contact_entity b order by b.firstName "
                    + request.getSort().getOrderFor("firstName").getDirection().name(), ContactEntity.class);
            typedQuery.setFirstResult(Math.toIntExact(request.getOffset()));
            typedQuery.setMaxResults(request.getPageSize());
            return typedQuery.getResultList();
        } finally {
            em.close();
        }
    }


    public ContactEntity saveToDatabase(ContactEntity contactEntity) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(contactEntity);
            em.flush();
            em.getTransaction().commit();

        }
        finally {
            em.close();
        }

        return contactEntity;
    }

    public List<ContactEntity> findAllContacts(int pageSize, int offSet) {
        return null;
    }

    public EntityManager getEntityManager() {return emf.createEntityManager();}
}
