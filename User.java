/*Name: NUR ARFA NISRINA BINTI MOHD AIZURIZAM  
  Matric Number: 2517666 */
public class User {
    
    private String userId;
    private ArrayList<Pet> adoptedPets;

    public User(String userId){
        this.userId = userId;
    }
  
    public void adoptPet(Pet p){
        adoptedPet.add(p);
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
