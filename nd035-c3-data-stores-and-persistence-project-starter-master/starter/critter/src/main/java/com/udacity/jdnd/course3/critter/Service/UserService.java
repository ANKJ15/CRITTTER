package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entities.Customer;
import com.udacity.jdnd.course3.critter.Entities.Employee;
import com.udacity.jdnd.course3.critter.Entities.Pet;
import com.udacity.jdnd.course3.critter.Entities.User;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.user.UserRepo;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {
    @Autowired
    PetRepo petRepository;
    @Autowired
    UserRepo userRepository;

    public User addUser(User x)
    {
        return userRepository.save(x);
    }

    public Customer findOwnerByPetID(Long x)
    {
        Optional<Pet> optionalPet = petRepository.findById(x);
        Pet pet = optionalPet.orElse(new Pet());
        Customer customer = (Customer) pet.getOwner();
        if(customer == null){
            return new Customer();
        }
        return customer;
    }

    public List<Customer> getAll()
    {
        return userRepository.findAllCustomers();
    }
    public Customer findCustomerByID(Long x)
    {
    return userRepository.findCustomerByID(x);
    }
    public Employee FindEmployeeByID(Long x)
    {
        return userRepository.findEmployeeByID(x);
    }








}
