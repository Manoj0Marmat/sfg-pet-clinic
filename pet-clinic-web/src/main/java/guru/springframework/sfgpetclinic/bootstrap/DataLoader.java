package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }


    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count==0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("jackie");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("lilly");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

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
        tusharPet.setName("jeryy");
        owner2.getPets().add(tusharPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners.....");

        Vet vet1 = new Vet();
        vet1.setFirstName("sam");
        vet1.setLastName("axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("jessie");
        vet2.setLastName("porter");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets.....");
    }
}
