package com.example.workshop.repository;

import com.example.workshop.model.AddressBookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookData, Integer> {

    @Query
    List<AddressBookData> findByEmailId(String emailId);

}
