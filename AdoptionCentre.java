import java.util.ArrayList;

public class AdoptionCentre {
    
    private String centreName = "PAC-MAN";
    private ArrayList<Pet> pets;
    
    public AdoptionCentre (String centreName, ArrayList<Pet> pets){
        this.pets = pets;
        this.centreName = centreName;
    }
    public void addPet(Pet p){
        pets.add(p);
        System.out.println("Welcome " + p.getName() + " to " + centreName + "!");

    }
    public void showAvailablePets(){
        System.out.println("Current available pets: ");
        for (Pet p : pets){

            if (p.isAvailable()){
    
                p.displayInfo();
            }
            else{
                System.out.println(p.getName() + " is not available.");
            }
        }

    }
    public void adoptPet(User u, Pet p){
        if(p.isAvailable()){
            p.setAvailable(false);
            System.out.println("User ID: " + u.getUserId() + " has adopted " + p.getName());
            System.out.println("Adoption Sucessful!");
        }
    }
}
