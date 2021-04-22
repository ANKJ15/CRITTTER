package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.Entities.Customer;
import com.udacity.jdnd.course3.critter.Entities.Employee;
import com.udacity.jdnd.course3.critter.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Customer findCustomerByID(Long x);
    Employee findEmployeeByID(Long x);

    @Query("select c from Customer c")
    List<Customer>findAllCustomers();



}
