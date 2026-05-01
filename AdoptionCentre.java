import java.util.ArrayList;

public class AdoptionCentre {
    
    private String centreName = "PAC-MAN";
    private ArrayList<Pet> pets;
    
    public AdoptionCentre (String centreName, ArrayList<Pet> pets){
        this.pets = pets;
        this.centreName = centreName;
    }

    public void addPet(Pet pet){
        pets.add(pet);
        System.out.println("Welcome " + pet.getName() + " to " + centreName + "!");

    }
}
