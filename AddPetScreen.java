import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddPetScreen {

    private Scene scene;

    public AddPetScreen(AdoptionCentre centre, User currentUser, Stage stage) {

        // ── header ──────────────────────────────────────────
        Label appTitle = new Label("PAC-MAN");
        appTitle.setFont(Font.font("Times New Roman", 30));
        appTitle.setStyle("-fx-font-weight: bold;");

        Label appSubtitle = new Label("Pet Adoption Centre Management");
        appSubtitle.setFont(Font.font("Times New Roman", 14));

        VBox header = new VBox(5, appTitle, appSubtitle);
        header.setAlignment(Pos.CENTER);

        // ── form card ───────────────────────────────────────
        Label formTitle = new Label("ADD NEW PET");
        formTitle.setFont(Font.font("Times New Roman", 18));
        formTitle.setStyle("-fx-font-weight: bold;");

        // pet type selector
        Label lblType = new Label("Pet Type:");
        lblType.setFont(Font.font("Times New Roman", 14));

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Cat", "Dog");
        typeBox.setPromptText("Select Type");
        typeBox.setMaxWidth(300);

        // name
        Label lblName = new Label("Name:");
        lblName.setFont(Font.font("Times New Roman", 14));
        TextField nameField = new TextField();
        nameField.setPromptText("Enter pet name");
        nameField.setMaxWidth(300);

        // breed
        Label lblBreed = new Label("Breed:");
        lblBreed.setFont(Font.font("Times New Roman", 14));
        TextField breedField = new TextField();
        breedField.setPromptText("Enter breed");
        breedField.setMaxWidth(300);

        // age
        Label lblAge = new Label("Age:");
        lblAge.setFont(Font.font("Times New Roman", 14));
        TextField ageField = new TextField();
        ageField.setPromptText("Enter age");
        ageField.setMaxWidth(300);

        // extra field — changes based on type selected
        Label lblExtra = new Label("Indoor:");
        lblExtra.setFont(Font.font("Times New Roman", 14));

        RadioButton yesBtn = new RadioButton("Yes");
        RadioButton noBtn  = new RadioButton("No");
        yesBtn.setFont(Font.font("Times New Roman", 14));
        noBtn.setFont(Font.font("Times New Roman", 14));

        ToggleGroup extraGroup = new ToggleGroup();
        yesBtn.setToggleGroup(extraGroup);
        noBtn.setToggleGroup(extraGroup);
        yesBtn.setSelected(true);

        HBox extraBox = new HBox(15, yesBtn, noBtn);

        // change label when type changes
        typeBox.setOnAction(e -> {
            if (typeBox.getValue() != null) {
                if (typeBox.getValue().equals("Cat")) {
                    lblExtra.setText("Indoor:");
                } else {
                    lblExtra.setText("Trained:");
                }
            }
        });

        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.add(lblType,  0, 0); formGrid.add(typeBox,   1, 0);
        formGrid.add(lblName,  0, 1); formGrid.add(nameField, 1, 1);
        formGrid.add(lblBreed, 0, 2); formGrid.add(breedField,1, 2);
        formGrid.add(lblAge,   0, 3); formGrid.add(ageField,  1, 3);
        formGrid.add(lblExtra, 0, 4); formGrid.add(extraBox,  1, 4);

        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Times New Roman", 13));

        VBox formCard = new VBox(15, formTitle, formGrid, statusLabel);
        formCard.setPadding(new Insets(20));
        formCard.setStyle(CARD_STYLE);

        // ── buttons ─────────────────────────────────────────
        Button backBtn = new Button("BACK");
        backBtn.setFont(Font.font("Times New Roman", 14));
        backBtn.setStyle(BTN_DARK_PINK);

        Button addBtn = new Button("ADD PET");
        addBtn.setFont(Font.font("Times New Roman", 14));
        addBtn.setStyle(BTN_PINK);

        backBtn.setOnAction(e -> {
            PetListScreen petList = new PetListScreen(centre, currentUser, stage);
            stage.setScene(petList.getScene());
        });

        addBtn.setOnAction(e -> {
            String type  = typeBox.getValue();
            String name  = nameField.getText().trim();
            String breed = breedField.getText().trim();
            String ageText = ageField.getText().trim();
            boolean extraValue = yesBtn.isSelected();

            // validation
            if (type == null || name.isEmpty() || breed.isEmpty() || ageText.isEmpty()) {
                statusLabel.setText("Please fill in all fields.");
                statusLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // check age is a number
            int age;
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException ex) {
                statusLabel.setText("Age must be a number.");
                statusLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // create the right pet type
            if (type.equals("Cat")) {
                centre.addPet(new Cat(name, age, breed, extraValue));
            } else {
                centre.addPet(new Dog(name, age, breed, extraValue));
            }

            // save to file
            centre.savePets("pets.txt");

            statusLabel.setText(name + " added successfully!");
            statusLabel.setStyle("-fx-text-fill: green;");

            // clear form
            nameField.clear();
            breedField.clear();
            ageField.clear();
            typeBox.setValue(null);
            yesBtn.setSelected(true);
        });

        HBox buttonBox = new HBox(20, backBtn, addBtn);
        buttonBox.setAlignment(Pos.CENTER);

        // ── root layout ─────────────────────────────────────
        VBox root = new VBox(20, header, formCard, buttonBox);
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
