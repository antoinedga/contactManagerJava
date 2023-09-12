package com.gordon.contactmanagerjava.repository;

import com.gordon.contactmanagerjava.modal.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

}
