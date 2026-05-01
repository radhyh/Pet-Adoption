/*All Member*/
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        System.out.println("Welcome to PAC-MAN, your Pet Adoption Centre Management!");
    
        Cat cat1 = new Cat("Chipsmore", 2, "Siamese", true);
        Dog dog1 = new Dog("Oreo", 3, "Chihuahua", false);
        
        ArrayList<Pet> petList = new ArrayList<>();
        AdoptionCentre centre = new AdoptionCentre("PAC-MAN", petList);
        User user1 = new User("P01",new ArrayList<Pet>()); 
        
        centre.addPet(cat1);
        centre.addPet(dog1);
        
        centre.showAvailablePets();

        centre.adoptPet(user1, cat1);

        
        






    }
}
