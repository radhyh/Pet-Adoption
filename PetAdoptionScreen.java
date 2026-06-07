import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PetAdoptionScreen {

    private Scene scene;

    public PetAdoptionScreen(Pet pet, AdoptionCentre centre, User currentUser, Stage stage) {

        Label appTitle = new Label("PAC-MAN");
        appTitle.setFont(Font.font("Times New Roman", 30));
        appTitle.setStyle("-fx-font-weight: bold;");

        Label appSubtitle = new Label("Pet Adoption Centre Management");
        appSubtitle.setFont(Font.font("Times New Roman", 14));

        VBox header = new VBox(5, appTitle, appSubtitle);
        header.setAlignment(Pos.CENTER);

        Label profileTitle = new Label("PET PROFILE");
        profileTitle.setFont(Font.font("Times New Roman", 18));
        profileTitle.setStyle("-fx-font-weight: bold;");

        Label lblName  = new Label("Name:");
        Label lblAge   = new Label("Age:");
        Label lblBreed = new Label("Breed:");

        lblName.setFont(Font.font("Times New Roman", 14));
        lblAge.setFont(Font.font("Times New Roman", 14));
        lblBreed.setFont(Font.font("Times New Roman", 14));

        Label valueName  = new Label(pet.getName());
        Label valueAge   = new Label(pet.getAge() + " Years");
        Label valueBreed = new Label(pet.getBreed());

        valueName.setFont(Font.font("Times New Roman", 14));
        valueAge.setFont(Font.font("Times New Roman", 14));
        valueBreed.setFont(Font.font("Times New Roman", 14));

        GridPane profileGrid = new GridPane();
        profileGrid.setHgap(15);
        profileGrid.setVgap(15);
        profileGrid.add(lblName,    0, 0); profileGrid.add(valueName,  1, 0);
        profileGrid.add(lblAge,     0, 1); profileGrid.add(valueAge,   1, 1);
        profileGrid.add(lblBreed,   0, 2); profileGrid.add(valueBreed, 1, 2);

        if (pet instanceof Cat) {
            Cat c = (Cat) pet;

            Label lblIndoor = new Label("Type:");
            lblIndoor.setFont(Font.font("Times New Roman", 14));

            RadioButton indoorBtn  = new RadioButton("Indoor");
            RadioButton outdoorBtn = new RadioButton("Outdoor");
            indoorBtn.setFont(Font.font("Times New Roman", 14));
            outdoorBtn.setFont(Font.font("Times New Roman", 14));

            ToggleGroup typeGroup = new ToggleGroup();
            indoorBtn.setToggleGroup(typeGroup);
            outdoorBtn.setToggleGroup(typeGroup);

            if (c.isIndoor()) {
                indoorBtn.setSelected(true);
            } else {
                outdoorBtn.setSelected(true);
            }

            indoorBtn.setDisable(true);
            outdoorBtn.setDisable(true);

            HBox typeBox = new HBox(15, indoorBtn, outdoorBtn);
            profileGrid.add(lblIndoor, 0, 3);
            profileGrid.add(typeBox,   1, 3);

        } else if (pet instanceof Dog) {
            Dog d = (Dog) pet;

            Label lblTrained = new Label("Trained:");
            lblTrained.setFont(Font.font("Times New Roman", 14));

            Label valueTrained = new Label(d.isTrained() ? "Yes" : "No");
            valueTrained.setFont(Font.font("Times New Roman", 14));

            profileGrid.add(lblTrained,  0, 3);
            profileGrid.add(valueTrained, 1, 3);
        }

        VBox profileCard = new VBox(15, profileTitle, profileGrid);
        profileCard.setPadding(new Insets(20));
        profileCard.setStyle(CARD_STYLE);

        Label formTitle = new Label("ADOPTION FORM");
        formTitle.setFont(Font.font("Times New Roman", 18));
        formTitle.setStyle("-fx-font-weight: bold;");

        Label nameLabel  = new Label("Full Name:");
        Label phoneLabel = new Label("Phone Number:");
        Label emailLabel = new Label("Email:");
        Label expLabel   = new Label("Experience:");

        nameLabel.setFont(Font.font("Times New Roman", 14));
        phoneLabel.setFont(Font.font("Times New Roman", 14));
        emailLabel.setFont(Font.font("Times New Roman", 14));
        expLabel.setFont(Font.font("Times New Roman", 14));

        TextField nameField  = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();

        ComboBox<String> experienceBox = new ComboBox<>();
        experienceBox.getItems().addAll(
                "No Experience",
                "Less than 1 Year",
                "1 - 3 Years",
                "More than 3 Years"
        );
        experienceBox.setPromptText("Select Experience");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.add(nameLabel,     0, 0); formGrid.add(nameField,     1, 0);
        formGrid.add(phoneLabel,    0, 1); formGrid.add(phoneField,    1, 1);
        formGrid.add(emailLabel,    0, 2); formGrid.add(emailField,    1, 2);
        formGrid.add(expLabel,      0, 3); formGrid.add(experienceBox, 1, 3);

        VBox formCard = new VBox(15, formTitle, formGrid);
        formCard.setPadding(new Insets(20));
        formCard.setStyle(CARD_STYLE);

        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Times New Roman", 14));

        Button backBtn   = new Button("BACK");
        Button submitBtn = new Button("SUBMIT");

        backBtn.setFont(Font.font("Times New Roman", 14));
        submitBtn.setFont(Font.font("Times New Roman", 14));

        backBtn.setStyle(BTN_RED);
        submitBtn.setStyle(BTN_GREEN);

        backBtn.setOnAction(e -> {
            PetListScreen petList = new PetListScreen(centre, currentUser, stage);
            stage.setScene(petList.getScene());
        });

        submitBtn.setOnAction(e -> {
            if (nameField.getText().isEmpty()
                    || phoneField.getText().isEmpty()
                    || emailField.getText().isEmpty()
                    || experienceBox.getValue() == null) {

                statusLabel.setText("Please complete all fields.");
                statusLabel.setStyle("-fx-text-fill: red;");

            } else if (!pet.isAvailable()) {
                statusLabel.setText(pet.getName() + " is no longer available.");
                statusLabel.setStyle("-fx-text-fill: red;");

            } else {
                centre.adoptPet(currentUser, pet);

                statusLabel.setText("Adoption request submitted successfully!");
                statusLabel.setStyle("-fx-text-fill: green;");

                submitBtn.setDisable(true);
            }
        });

        HBox buttonBox = new HBox(20, backBtn, submitBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(20,
                header,
                profileCard,
                formCard,
                buttonBox,
                statusLabel
        );

        root.setPadding(new Insets(25));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #F5F5F5;");

        scene = new Scene(root, 700, 700);
    }

    public Scene getScene() {
        return scene;
    }

    private static final String CARD_STYLE =
            "-fx-background-color: white;" +
            "-fx-border-color: black;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 15;" +
            "-fx-background-radius: 15;";

    private static final String BTN_RED =
            "-fx-background-color: #D32F2F;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;";

    private static final String BTN_GREEN =
            "-fx-background-color: #388E3C;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;";
}