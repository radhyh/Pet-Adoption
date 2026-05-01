/*Name: NUR ARFA NISRINA BINTI MOHD AIZURIZAM  
  Matric Number: 2517666 */

import java.util.ArrayList;

public class User {
    
    private String userId;
    private ArrayList<Pet> adoptedPets;

    public User(String userId, ArrayList<Pet> adoptedPets){
        this.userId = userId;
        this.adoptedPets = adoptedPets;
    }   
    public void adoptPet(Pet p){
        adoptedPets.add(p);
        System.out.println("Congratulations! You have adopted " + p.getName());
    }
    
    public void returnPet(Pet p){
        adoptedPets.remove(p);
        p.setAvailable(true);
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }
}
