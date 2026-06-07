/*All Member*/
import javafx.application.Application;
import javafx.stage.Stage;
 
public class main extends Application {
 
    @Override
    public void start(Stage stage) {
 
        // create the AdoptionCentre — shared across all screens
        AdoptionCentre centre = new AdoptionCentre();
 
        // load saved data from files if they exist
        centre.loadPets("pets.txt");
        centre.loadUsers("users.txt");
 
        // if no users saved yet, add a default one for testing
        if (centre.getUsers().isEmpty()) {
            centre.getUsers().add(new User("U001", "Admin", "1234"));
        }
 
        // if no pets saved yet, add some default ones for testing
        if (centre.getAllPets().isEmpty()) {
            centre.addPet(new Cat("Chipsmore", 2, "Siamese", true));
            centre.addPet(new Dog("Oreo", 3, "Chihuahua", false));
        }
 
        // start with login screen
        LoginScreen loginScreen = new LoginScreen(centre, stage);
        stage.setScene(loginScreen.getScene());
        stage.setTitle("PAC-MAN - Pet Adoption Centre Management");
        stage.show();
    }
 
    public static void main(String[] args) {
        launch();
    }
}