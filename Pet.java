public class Pet {
   
    private String name;
    private String breed;
    private int age;
    private boolean available = true; 

   
    public Pet(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    public void Sound(){
        System.out.println("Pet makes a sound");
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Breed: " + breed + 
                           ", Age: " + age + ", Available: " + available);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }
}
