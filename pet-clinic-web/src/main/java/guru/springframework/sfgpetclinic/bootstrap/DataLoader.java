package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("jackie");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("lilly");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("manoj");
        owner1.setLastName("marmat");
        owner1.setAddress("punjagutta");
        owner1.setCity("Hyderabad");
        owner1.setTelephone("123456789");

        Pet manojPet = new Pet();
        manojPet.setPetType(savedDogPetType);
        manojPet.setOwner(owner1);
        manojPet.setBirthDate(LocalDate.now());
        manojPet.setName("simba");
        owner1.getPets().add(manojPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("tushar");
        owner2.setLastName("marmat");
        owner2.setAddress("srinivasa");
        owner2.setCity("buldhana");
        owner2.setTelephone("2345621789");

        Pet tusharPet = new Pet();
        tusharPet.setPetType(savedCatPetType);
        tusharPet.setOwner(owner2);
        tusharPet.setBirthDate(LocalDate.now());
        tusharPet.setName("jessie");
        owner2.getPets().add(tusharPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners.....");

        Vet vet1 = new Vet();
        vet1.setFirstName("sam");
        vet1.setLastName("axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("jessie");
        vet2.setLastName("porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets.....");
    }
}
