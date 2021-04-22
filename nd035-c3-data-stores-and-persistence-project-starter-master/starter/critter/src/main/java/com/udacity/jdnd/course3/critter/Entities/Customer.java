package com.udacity.jdnd.course3.critter.Entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Customer extends User {

    private String notes;
    @OneToMany(mappedBy = "customer")
    private List<Pet> pets;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void addNewPet(Pet pet) {
        pet.setCustomer(this);
        pets.add(pet);
    }

    public void removeExistingPet(Pet pet) {
        pets.remove(pet);
        pet.setCustomer(null);
    }

}

