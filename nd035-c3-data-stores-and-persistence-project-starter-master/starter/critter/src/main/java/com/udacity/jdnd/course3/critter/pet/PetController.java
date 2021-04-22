package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Entities.Pet;
import com.udacity.jdnd.course3.critter.Entities.User;
import com.udacity.jdnd.course3.critter.Service.PetService;
import com.udacity.jdnd.course3.critter.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;
    @Autowired
    UserService userService;
    @Autowired
    PetRepo petRepo;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet x = getPetFromPetDTO(petDTO);
        Pet savedPet = petService.savePet(x);
        return getPetDTOFromPet(savedPet);

    }

    @GetMapping("/{petId}")
    public Pet getPet(@PathVariable long petId) {
        Optional<Pet> optionalPet = petRepo.findById(petId);
        return optionalPet.orElse(null);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAllPets();
        List<PetDTO> petDTOs = new ArrayList<>();
        for(Pet pet : pets){
            petDTOs.add(getPetDTOFromPet(pet));
        }
        return petDTOs;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByOwnerId(ownerId);
        List<PetDTO> petDTOs = new ArrayList<>();
        for(Pet pet : pets){
            petDTOs.add(getPetDTOFromPet(pet));
        }
        return petDTOs;
    }


    private Pet getPetFromPetDTO(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet, "ownerId");
        Long userId = petDTO.getOwnerId();
        User user = userService.findCustomerByID(userId);
        pet.setOwner(user);
        return pet;
    }

    private PetDTO getPetDTOFromPet(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO, "owner");
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }
}
