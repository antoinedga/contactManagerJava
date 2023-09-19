package com.gordon.contactmanagerjava.repository;

import com.gordon.contactmanagerjava.modal.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactEntityRepository extends JpaRepository<ContactEntity, Integer> {

    public List<ContactEntity> findContactEntitiesByUserId(Integer id);
//    Page<ContactEntity> findContactEntities(Pageable pageable);
}
