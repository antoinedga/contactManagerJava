package com.gordon.contactmanagerjava;


import com.github.javafaker.Faker;
import com.gordon.contactmanagerjava.dao.ContactHibernateManager;
import com.gordon.contactmanagerjava.modal.ContactEntity;
import com.gordon.contactmanagerjava.modal.UserEntity;
import com.gordon.contactmanagerjava.repository.ContactEntityRepository;
import com.gordon.contactmanagerjava.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

@SpringBootTest
public class ContactManagerJpaPagingTests {

    @Autowired
    private ContactEntityRepository contactEntityRepository;

    @Test
    public void pagingWithJpa() {
        contactEntityRepository.deleteAll();
        this.setUpContactRepository();

        List<ContactEntity> contactEntityList = contactEntityRepository.findAll(PageRequest.of(0, 10)).getContent();
        assertThat(contactEntityList.size(), is(equalTo(10)));
    }

    @Test
    public void pagingWithJpaSort() {
        contactEntityRepository.deleteAll();
        this.setUpContactRepository();

        List<ContactEntity> contactEntityList = contactEntityRepository.findAll(PageRequest.of(0, 10, Sort.by("firstName"))).getContent();
        assertThat(contactEntityList.size(), is(equalTo(10)));
    }

    @Test
    public void pagingWithJpaCustomMethod() {
        contactEntityRepository.deleteAll();
        this.setUpContactRepository();

      //  List<ContactEntity> contactEntityList = contactEntityRepository.findContactEntities(PageRequest.of(0, 10, Sort.by("firstName"))).getContent();
       // assertThat(contactEntityList.size(), is(equalTo(10)));
    }

    public void setUpContactRepository() {
        Faker faker = new Faker();

        for (int i = 0; i < 25; i++) {
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setFirstName(faker.name().firstName());
            contactEntity.setLastName(faker.name().lastName());
            contactEntity.setPhoneNumber(faker.phoneNumber().phoneNumber());
            contactEntity.setEmail(faker.internet().emailAddress());
            contactEntityRepository.save(contactEntity);
        }
    }
}
