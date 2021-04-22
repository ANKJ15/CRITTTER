package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entities.Customer;
import com.udacity.jdnd.course3.critter.Entities.Pet;
import com.udacity.jdnd.course3.critter.Entities.User;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepo petRepo;
    @Autowired
    UserRepo userRepo;
    public List<Pet>getAllPets()
    {
        return petRepo.findAll();
    }

    public Pet getPetByID(Long x)
    {
        return petRepo.findPetById(x);
    }


    public List<Pet> getPetsById(List<Long> x )
    {
        return petRepo.findById(x);
    }


    public List<Pet> getPetsByCustomer(Customer x)
{
    return petRepo.findPetsByCustomer(x);
}

    public List<Pet> getPetsByOwnerId(Long ownerId){
        Optional<User> optionalUser = userRepo.findById(ownerId);
        Customer customer = (Customer) optionalUser.orElse(null);

        if (customer != null) {
            return customer.getPets();
        } else {
            return new ArrayList<Pet>();
        }
    }

    public Pet savePet(Pet x)
    {
        Pet savedPet =  petRepo.save(x);
        Customer customer = (Customer) savedPet.getOwner();
        List<Pet> customerPets = customer.getPets();
        if(customerPets == null){
            customerPets = new ArrayList<>();
        }
        customerPets.add(savedPet);
        customer.setPets(customerPets);
        userRepo.save(customer);

        return savedPet;
    }


}
