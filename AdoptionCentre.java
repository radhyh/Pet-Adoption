/*Name: NURRADHIYAH BINTI RIDZUAN 
  Matric Number: 2513510 */

import java.util.ArrayList;

public class AdoptionCentre {
    
    private String centreName;
    private ArrayList<Pet> pets;
    
    public AdoptionCentre (String centreName, ArrayList<Pet> pets){
        this.centreName = centreName;
        this.pets = pets;
    }
    public void addPet(Pet p){
        pets.add(p);

    }
    public void showAvailablePets(){
        System.out.println("Current available pets:");
        for (Pet p : pets){

            if (p.isAvailable()){
    
                p.displayInfo();
                p.Sound();
                System.out.println();
            }
        }

    }
    public void adoptPet(User u, Pet p){
        if(p.isAvailable()){
            u.adoptPet(p);
            System.out.println("Adoption Sucessful! User ID: " + u.getUserId());
            p.setAvailable(false);
        }
        else{
            System.out.println(p.getName() + " is not available.");
        }
    }
}
