package com.gordon.contactmanagerjava;

import com.gordon.contactmanagerjava.modal.UserEntity;
import com.gordon.contactmanagerjava.repository.ContactEntityRepository;
import com.gordon.contactmanagerjava.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

@SpringBootTest
class ContactManagerJavaApplicationTests {

    @Autowired
    UserEntityRepository userEntityRepository;
    @Autowired
    ContactEntityRepository contactEntityRepository;


    @Test
    public void test() {
        assertThat(userEntityRepository.findUserEntitiesByFirstName("Andrea"), is(notNullValue()));
    }

    @Test
    public void test_toThrowException() {
        assertThrows(EntityNotFoundException.class, () -> {
            userEntityRepository.findUserEntitiesByLastName("Alvarez").orElseThrow(EntityNotFoundException::new);
        });
    }

    @Test
    public void test_toThrowException1() {
        assertThrows(EmptyResultDataAccessException.class,
                () -> userEntityRepository.findUserEntityByFirstName("Maurice"));
    }

    @Test
    public void test_shouldReturnNull() {
        assertThat(userEntityRepository.findUserEntityByFirstName("Maurice"), is(nullValue()));
    }


    @Test
    @Transactional
    public void test_streamMethod() {
        AtomicInteger counter = new AtomicInteger();
        userEntityRepository.findAllByFirstNameNotNull().forEach(user -> {
            counter.incrementAndGet();
        });

        assertThat(counter.get(), is(2));
    }

    @Test
    public void test_findByQueryJpa() {
        assertThat(userEntityRepository.findUserByQuery("Andrea"), is(notNullValue()));
    }

    @Test
    public void test_findByQueryJpaNamedParameter() {
        assertThat(userEntityRepository.findUserByNamedParameter("Andrea"), is(notNullValue()));
    }

    @Test
    public void test_findByQueryJpaNative() {
        assertThat(userEntityRepository.findUserByNativeQuery("Andrea"), is(notNullValue()));
    }


    @Test
    public void test_findByQueryJpaNamedQuery() {
        assertThat(userEntityRepository.findByFirstNameNamed("Andrea"), is(notNullValue()));
    }
}


