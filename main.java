/*All Member*/
import javafx.application.Application;
import javafx.stage.Stage;
 
public class main extends Application {
 
    @Override
    public void start(Stage stage) {
 
        AdoptionCentre centre = new AdoptionCentre();
        
        centre.savePets("pets.txt");
        centre.saveUsers("users.txt");
        
        centre.loadPets("pets.txt");
        centre.loadUsers("users.txt");
 

        if (centre.getUsers().isEmpty()) {
            centre.getUsers().add(new User("Admin", "1234", "admin"));
        }
 
    
        if (centre.getAllPets().isEmpty()) {
            centre.addPet(new Cat("Chipsmore", 2, "Siamese", true));
            centre.addPet(new Dog("Oreo", 3, "Chihuahua", false));
        }
 
    
        LoginScreen loginScreen = new LoginScreen(centre, stage);
        stage.setScene(loginScreen.getScene());
        stage.setTitle("PAC-MAN - Pet Adoption Centre Management");
        stage.show();
    }
 
    public static void main(String[] args) {
        launch();
    }


}