/*Login Screen — PAC-MAN Pet Adoption Centre*/

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginScreen {

    private Scene scene;

    public LoginScreen(AdoptionCentre centre, Stage stage) {

        // ════════════════════════════════════════════════════
        //  HEADER
        // ════════════════════════════════════════════════════
        Label appTitle = new Label("PAC-MAN");
        appTitle.setFont(Font.font("Times New Roman", 40));
        appTitle.setStyle("-fx-font-weight: bold;");

        Label appSubtitle = new Label("Pet Adoption Centre Management");
        appSubtitle.setFont(Font.font("Times New Roman", 14));

        Label welcomeLabel = new Label("Welcome! Please login to continue.");
        welcomeLabel.setFont(Font.font("Times New Roman", 13));
        welcomeLabel.setStyle("-fx-text-fill: grey;");

        VBox header = new VBox(8, appTitle, appSubtitle, welcomeLabel);
        header.setAlignment(Pos.CENTER);


        // ════════════════════════════════════════════════════
        //  LOGIN CARD
        // ════════════════════════════════════════════════════
        Label loginTitle = new Label("LOGIN");
        loginTitle.setFont(Font.font("Times New Roman", 20));
        loginTitle.setStyle("-fx-font-weight: bold;");

        // user ID field
        Label lblUserId = new Label("Username:");
        lblUserId.setFont(Font.font("Times New Roman", 14));
        TextField userIdField = new TextField();
        userIdField.setPromptText("Enter your Username");
        userIdField.setMaxWidth(300);

        // password field
        Label lblPassword = new Label("Password:");
        lblPassword.setFont(Font.font("Times New Roman", 14));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setMaxWidth(300);

        // status message
        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Times New Roman", 13));

        // login button
        Button loginBtn = new Button("LOGIN");
        loginBtn.setFont(Font.font("Times New Roman", 14));
        loginBtn.setStyle(BTN_PINK);
        loginBtn.setMaxWidth(300);

loginBtn.setOnAction(e -> {
    String enteredUsername = userIdField.getText().trim();
    String enteredPwd = passwordField.getText().trim();

    if (enteredUsername.isEmpty() || enteredPwd.isEmpty()) {
        statusLabel.setText("Please fill in all fields.");
        statusLabel.setStyle("-fx-text-fill: red;");
        return;
    }

    // check against username and password
    User loggedInUser = null;
    for (User u : centre.getUsers()) {
        if (u.getUsername().equals(enteredUsername) && u.checkPassword(enteredPwd)) {
            loggedInUser = u;
            break;
        }
    }

    if (loggedInUser != null) {
        statusLabel.setText("Login successful! Welcome, " + loggedInUser.getUsername());
        statusLabel.setStyle("-fx-text-fill: green;");

        User finalUser = loggedInUser;
        PetListScreen petList = new PetListScreen(centre, finalUser, stage);
        stage.setScene(petList.getScene());

    } else {
        statusLabel.setText("Invalid username or password. Please try again.");
        statusLabel.setStyle("-fx-text-fill: red;");
        passwordField.clear();
    }
});

        // divider line between login and register
        Separator separator = new Separator();
        separator.setMaxWidth(300);

        // register section
        Label registerLabel = new Label("Don't have an account?");
        registerLabel.setFont(Font.font("Times New Roman", 13));
        registerLabel.setStyle("-fx-text-fill: grey;");

        Button registerBtn = new Button("REGISTER");
        registerBtn.setFont(Font.font("Times New Roman", 13));
        registerBtn.setStyle(BTN_OUTLINE);

        registerBtn.setOnAction(e -> {
            RegisterScreen registerScreen = new RegisterScreen(centre, stage);
            stage.setScene(registerScreen.getScene());
        });

        VBox loginCard = new VBox(15,
                loginTitle,
                lblUserId,   userIdField,
                lblPassword, passwordField,
                loginBtn,
                statusLabel,
                separator,
                registerLabel,
                registerBtn
        );

        loginCard.setAlignment(Pos.CENTER_LEFT);
        loginCard.setPadding(new Insets(30));
        loginCard.setMaxWidth(400);
        loginCard.setStyle(CARD_STYLE);


        // ════════════════════════════════════════════════════
        //  ROOT LAYOUT
        // ════════════════════════════════════════════════════
        VBox root = new VBox(30, header, loginCard);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #F5F5F5;");

        scene = new Scene(root, 700, 600);
    }

    public Scene getScene() {
        return scene;
    }


    // ── style constants ──────────────────────────────────────
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
 
    private static final String BTN_OUTLINE =
            "-fx-background-color: transparent;" +
            "-fx-border-color: #E91E8C;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 5;" +
            "-fx-text-fill: #E91E8C;" +
            "-fx-font-weight: bold;";
}