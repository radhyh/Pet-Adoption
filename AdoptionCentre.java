import java.util.ArrayList;

public class AdoptionCentre {
    
    String centreName = "";
    ArrayList<Pet> pets = new ArrayList<>();
    
    public static void addPet(Pet pet, ArrayList<Pet> pets){
        pets.add(pet);
    }
}
