import java.io.FileWriter;
import java.io.IOException;

public class DogFile{
    public static void saveDog(Dog dog){

        try{
            FileWriter writer = new FileWriter("dogs.txt", true);

            writer.write(
                dog.getName()+"," + dog.getAge() +"," + dog.getBreed()+ "," +dog.isTrained() + "\n"
            );

            writer.close();

            System.out.println("Dog saved succesfully!");
        } catch (IOException e){
            System.out.println("Error saving file. ");
        }

    
    }
}