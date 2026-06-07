/*Name: NURRADHIYAH BINTI RIDZUAN 
  Matric Number: 2513510 */

import java.util.ArrayList;
import java.io.*;

public class AdoptionCentre {
    
    private ArrayList<Pet> pets;
    private ArrayList<User> users;
    
    public AdoptionCentre() {
        this.pets = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addPet(Pet p){
        pets.add(p);
    }

    public void removePet(String name) {
        pets.removeIf(p -> p.getName().equals(name));
    }

    public ArrayList<Pet> getAvailablePets() {
        ArrayList<Pet> available = new ArrayList<>();
        for (Pet p : pets) {
            if (p.isAvailable()) available.add(p);
        }
        return available;
    }

    public ArrayList<Pet> getAllPets() { 
        return pets; 
    }
    public ArrayList<User> getUsers() { 
        return users; 
    }

    public void adoptPet(User u, Pet p) {
        if (p.isAvailable()) {
            u.adoptPet(p);
            p.setAvailable(false);
            savePets("pets.txt"); 
        } 
        else {
            System.out.println(p.getName() + " is not available.");
        }
    }

    public void savePets(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("adoption_data.txt"))) {
            for (Pet p : pets) {
                if (p instanceof Dog) {
                    Dog d = (Dog) p;
                    pw.println("Dog," + p.getName() + "," + p.getBreed()  + "," + p.getAge() + "," + p.isAvailable() + "," + d.isTrained());
                } 
                else if (p instanceof Cat) {
                    Cat c = (Cat) p;
                    pw.println("Cat," + p.getName() + "," + p.getBreed() + "," + p.getAge() + "," + p.isAvailable() + "," + c.isIndoor());
                }
            }    
        } 
        catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
    
    public void loadPets(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("adoption_data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("Dog")) {
                    pets.add(new Dog(parts[1], Integer.parseInt(parts[3]), 
                                     parts[2], Boolean.parseBoolean(parts[5])));
                } else if (parts[0].equals("Cat")) {
                    pets.add(new Cat(parts[1], Integer.parseInt(parts[3]), 
                                     parts[2], Boolean.parseBoolean(parts[5])));
                }
            }
        } 
        catch (IOException e) {
            System.out.println("No saved data found.");
        }
    

}
