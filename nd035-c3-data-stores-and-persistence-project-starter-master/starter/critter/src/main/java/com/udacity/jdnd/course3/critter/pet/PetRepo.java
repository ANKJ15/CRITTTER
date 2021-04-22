package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Entities.Customer;
import com.udacity.jdnd.course3.critter.Entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Transactional
@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {

    Pet findPetById(Long id);

    List<Pet> findById(List<Long> petIds);
    List<Pet> findPetsByCustomer(Customer customer);
    @Query("select p.id from Pet p where p.customer = :customer")
    List<Long> findIdsByCustomer(Customer customer);
}
