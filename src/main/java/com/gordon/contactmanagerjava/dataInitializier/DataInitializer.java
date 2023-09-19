package com.gordon.contactmanagerjava.dataInitializier;

import com.github.javafaker.Faker;
import com.gordon.contactmanagerjava.dao.UserHibernateManager;
import com.gordon.contactmanagerjava.modal.ContactEntity;
import com.gordon.contactmanagerjava.modal.UserEntity;
import com.gordon.contactmanagerjava.repository.ContactEntityRepository;
import com.gordon.contactmanagerjava.repository.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserEntityRepository userRepository;
    private final UserHibernateManager userHibernateManager;
    private final ContactEntityRepository contactEntityRepository;

    public DataInitializer(UserEntityRepository userRepository, UserHibernateManager userHibernateManager, ContactEntityRepository contactEntityRepository) {
        this.userRepository = userRepository;
        this.userHibernateManager = userHibernateManager;
        this.contactEntityRepository = contactEntityRepository;
    }

//    @Override // hibernate testing
//    public void run(String... args) throws Exception {
//        userRepository.deleteAll();
//        UserEntity test = null;
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail("an.gordonalvarez@gmail.com");
//        userEntity.setFirstName("Antoine");
//        userEntity.setLastName("Gordon");
//        userEntity.setPassword("password");
//
//        UserEntity savedDDD = userHibernateManager.saveNewUser(userEntity);
//        System.out.println("Id: " + savedDDD.getUserId());
//
//        test = userHibernateManager.getById(savedDDD.getUserId());
//        System.out.println(test.toString());
//
//        test = userHibernateManager.findByUsersName(savedDDD.getFirstName());
//        System.out.println(test);
//
//        test.setLastName("Alvarez");
////        test = userHibernateManager.updateUser(test);
////        System.out.println(test);
//
//
//        // for the get all
//        UserEntity userEntity1 = new UserEntity();
//        userEntity1.setEmail("andrea.gordon@gmail.com");
//        userEntity1.setFirstName("Andrea");
//        userEntity1.setLastName("Gordon");
//        userEntity1.setPassword("password");
//
//        userEntity1 = userHibernateManager.saveNewUser(userEntity1);
//
//        List<UserEntity> users = userHibernateManager.listUserEntityWithLastName("gordon");
//        System.out.println(users);
//        //userHibernateManager.deleteUserEntity(test.getUserId());
////
////        test = userHibernateManager.getById(test.getUserId());
////        System.out.println("Test is after delete: " + test);
//
//        users = userHibernateManager.findAll();
//        System.out.println(users);
//
//        userEntity1 = userHibernateManager.findByName("Antoine");
//        System.out.println(userEntity1);
//
//        userEntity1 = userHibernateManager.findByNameWithCriteria("Andrea");
//        System.out.println(userEntity1);
//
//        userEntity1 = userHibernateManager.findByLastNameWithNative("Gordon");
//        System.out.println(userEntity1);
//    }

    // for repositories
//    @Override
//    public void run(String... args) throws Exception {
//        UserEntity userEntity;
//        System.out.println(userRepository.findById(129));
//
//        userEntity = userRepository.findUserEntitiesByFirstName("Andrea");
//        System.out.println(userEntity);
//
//    }

    @Override
    public void run(String... args) throws Exception {

    }
}