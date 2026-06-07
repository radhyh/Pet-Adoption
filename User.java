/*Name: NUR ARFA NISRINA BINTI MOHD AIZURIZAM  
  Matric Number: 2517666 */

import java.util.ArrayList;

public class User {
    
    private String userId;
    private String name;
    private String password;
    private ArrayList<Pet> adoptedPets;

    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.adoptedPets = new ArrayList<>();
    }

    public void adoptPet(Pet p) {
        adoptedPets.add(p);
        System.out.println("Congratulations! You have adopted " + p.getName());
    }

    public void returnPet(Pet p) {
        adoptedPets.remove(p);
        p.setAvailable(true);
    }

    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }

    public String getUserId()  { return userId; }
    public String getName()    { return name; }
    public String getPassword(){ return password; }
    public ArrayList<Pet> getAdoptedPets() { return adoptedPets; }
}