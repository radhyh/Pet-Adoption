/*Pet List Screen — PAC-MAN Pet Adoption Centre*/

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PetListScreen {

    private Scene scene;

    public PetListScreen(AdoptionCentre centre, User currentUser, Stage stage) {

        
        Label appTitle = new Label("PAC-MAN");
        appTitle.setFont(Font.font("Times New Roman", 30));
        appTitle.setStyle("-fx-font-weight: bold;");

        Label welcomeLabel = new Label("Welcome, " + currentUser.getUsername() + "!");
        welcomeLabel.setFont(Font.font("Times New Roman", 14));
        welcomeLabel.setStyle("-fx-text-fill: grey;");

        VBox header = new VBox(5, appTitle, welcomeLabel);
        header.setAlignment(Pos.CENTER);


        Label listTitle = new Label("AVAILABLE PETS");
        listTitle.setFont(Font.font("Times New Roman", 18));
        listTitle.setStyle("-fx-font-weight: bold;");

       
        TableView<Pet> petTable = new TableView<>();
        petTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<Pet, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Pet, String> breedCol = new TableColumn<>("Breed");
        breedCol.setCellValueFactory(new PropertyValueFactory<>("breed"));

        TableColumn<Pet, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Pet, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data -> {
            Pet p = data.getValue();
            String type = (p instanceof Cat) ? "Cat" : "Dog";
            return new javafx.beans.property.SimpleStringProperty(type);
        });

        TableColumn<Pet, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> {
            String status = data.getValue().isAvailable() ? "Available" : "Adopted";
            return new javafx.beans.property.SimpleStringProperty(status);
        });

        petTable.getColumns().add(nameCol);
        petTable.getColumns().add(breedCol);
        petTable.getColumns().add(ageCol);
        petTable.getColumns().add(typeCol);
        petTable.getColumns().add(statusCol);

    
        petTable.getItems().addAll(centre.getAvailablePets());
        petTable.setPrefHeight(250);

        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Times New Roman", 13));

        Button adoptBtn = new Button("ADOPT SELECTED PET");
        adoptBtn.setFont(Font.font("Times New Roman", 14));
        adoptBtn.setStyle(BTN_PINK);

        adoptBtn.setOnAction(e -> {
            Pet selected = petTable.getSelectionModel().getSelectedItem();

            if (selected == null) {
                statusLabel.setText("Please select a pet from the list first.");
                statusLabel.setStyle("-fx-text-fill: red;");
            } else {
                PetAdoptionScreen adoptScreen = new PetAdoptionScreen(
                        selected, centre, currentUser, stage);
                stage.setScene(adoptScreen.getScene());
            }   
        });
        
        VBox listCard = new VBox(15, listTitle, petTable, statusLabel);
        listCard.setPadding(new Insets(20));
        listCard.setStyle(CARD_STYLE);
        
        if (currentUser.isAdmin()) {
            Button addPetBtn = new Button("ADD PET");
            addPetBtn.setFont(Font.font("Times New Roman", 14));
            addPetBtn.setStyle(BTN_PINK);
            
            addPetBtn.setOnAction(e -> {
                AddPetScreen addPet = new AddPetScreen(centre, currentUser, stage);
                stage.setScene(addPet.getScene());
            });
            
            HBox btnBox = new HBox(15, adoptBtn, addPetBtn);
            btnBox.setAlignment(Pos.CENTER);
            listCard.getChildren().add(btnBox);
        } 
        else {
            listCard.getChildren().add(adoptBtn);
        }


        Button logoutBtn = new Button("LOGOUT");
        logoutBtn.setFont(Font.font("Times New Roman", 14));
        logoutBtn.setStyle(BTN_DARK_PINK);

        logoutBtn.setOnAction(e -> {
            LoginScreen login = new LoginScreen(centre, stage);
            stage.setScene(login.getScene());
        });

        HBox bottomBox = new HBox(20, logoutBtn);
        bottomBox.setAlignment(Pos.CENTER_RIGHT);


        VBox root = new VBox(20, header, listCard, bottomBox);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #F5F5F5;");

        scene = new Scene(root, 700, 600);
    }

    public Scene getScene() {
        return scene;
    }


    private static final String CARD_STYLE =
            "-fx-background-color: white;" +
            "-fx-border-color: #E91E8C;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 15;" +
            "-fx-background-radius: 15;";
 
    private static final String BTN_PINK =
            "-fx-background-color: #E91E8C;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;";
 
    private static final String BTN_DARK_PINK =
            "-fx-background-color: #880E4F;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;";
}
