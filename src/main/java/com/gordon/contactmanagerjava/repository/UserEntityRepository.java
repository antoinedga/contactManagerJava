package com.gordon.contactmanagerjava.repository;

import com.gordon.contactmanagerjava.modal.UserEntity;
import jakarta.annotation.Nonnull;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findUserEntitiesByFirstName(String firstName);
    Optional<UserEntity> findUserEntitiesByLastName(String lastName);

    @Nullable
    UserEntity findUserEntityByFirstName(String firstName);

    Stream<UserEntity> findAllByFirstNameNotNull();

    @Query("select u from user_entity u where u.firstName = ?1")
    UserEntity findUserByQuery(String firstName);

    @Query("select u from user_entity u where u.firstName = :firstName")
    UserEntity findUserByNamedParameter(@Param("firstName")String firstName);

    @Query(value = "select * from user_entity u where u.first_name = :firstName", nativeQuery = true)
    UserEntity findUserByNativeQuery(String firstName);
    // name repo
   // UserEntity findByFirstNameNamed(String firstName);


}
