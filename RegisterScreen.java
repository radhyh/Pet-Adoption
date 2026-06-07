/*Name: NUR ARFA NISRINA BINTI MOHD AIZURIZAM  
  Matric Number: 2517666 */
  
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegisterScreen {

    private Scene scene;

    public RegisterScreen(AdoptionCentre centre, Stage stage) {

        Label appTitle = new Label("PAC-MAN");
        appTitle.setFont(Font.font("Times New Roman", 40));
        appTitle.setStyle("-fx-font-weight: bold;");

        Label appSubtitle = new Label("Pet Adoption Centre Management");
        appSubtitle.setFont(Font.font("Times New Roman", 14));

        Label registerHeader = new Label("Create a new account to get started.");
        registerHeader.setFont(Font.font("Times New Roman", 13));
        registerHeader.setStyle("-fx-text-fill: grey;");

        VBox header = new VBox(8, appTitle, appSubtitle, registerHeader);
        header.setAlignment(Pos.CENTER);


        Label registerTitle = new Label("REGISTER");
        registerTitle.setFont(Font.font("Times New Roman", 20));
        registerTitle.setStyle("-fx-font-weight: bold;");

        Label lblName = new Label("Userame:");
        lblName.setFont(Font.font("Times New Roman", 14));
        TextField nameField = new TextField();
        nameField.setPromptText("Create a username");
        nameField.setMaxWidth(300);

        Label lblPassword = new Label("Password:");
        lblPassword.setFont(Font.font("Times New Roman", 14));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Create a password");
        passwordField.setMaxWidth(300);

        Label lblConfirm = new Label("Confirm Password:");
        lblConfirm.setFont(Font.font("Times New Roman", 14));
        PasswordField confirmField = new PasswordField();
        confirmField.setPromptText("Re-enter your password");
        confirmField.setMaxWidth(300);

        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Times New Roman", 13));

        Button registerBtn = new Button("CREATE ACCOUNT");
        registerBtn.setFont(Font.font("Times New Roman", 14));
        registerBtn.setStyle(BTN_PINK);
        registerBtn.setMaxWidth(300);
        
        registerBtn.setOnAction(e -> {
             String name    = nameField.getText().trim();
             String pwd     = passwordField.getText().trim();
             String confirm = confirmField.getText().trim();
             
             if (name.isEmpty() || pwd.isEmpty() || confirm.isEmpty()) {
                statusLabel.setText("Please fill in all fields.");
                statusLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            
            if (!pwd.equals(confirm)) {
                statusLabel.setText("Passwords do not match.");
                statusLabel.setStyle("-fx-text-fill: red;");
                confirmField.clear();
                return;
            }
            
            for (User u : centre.getUsers()) {
                if (u.getUsername().equals(name)) {
                    statusLabel.setText("Username already taken. Please choose another.");
                    statusLabel.setStyle("-fx-text-fill: red;");
                    return;
                }
            }
            
            User newUser = new User(name, pwd, "user");
            centre.getUsers().add(newUser);
            centre.saveUsers("users.txt");
            
            statusLabel.setText("Account created! Redirecting to login...");
            statusLabel.setStyle("-fx-text-fill: green;");
            
            registerBtn.setDisable(true);
            new Thread(() -> {
                try { Thread.sleep(1500); } catch (InterruptedException ex) {}
                javafx.application.Platform.runLater(() -> {
                    LoginScreen login = new LoginScreen(centre, stage);
                    stage.setScene(login.getScene());
                });
            }).start();
        });
        
        Separator separator = new Separator();
        separator.setMaxWidth(300);

        Label alreadyLabel = new Label("Already have an account?");
        alreadyLabel.setFont(Font.font("Times New Roman", 13));
        alreadyLabel.setStyle("-fx-text-fill: grey;");

        Button backLoginBtn = new Button("BACK TO LOGIN");
        backLoginBtn.setFont(Font.font("Times New Roman", 13));
        backLoginBtn.setStyle(BTN_OUTLINE);

        backLoginBtn.setOnAction(e -> {
            LoginScreen login = new LoginScreen(centre, stage);
            stage.setScene(login.getScene());
        });

        VBox registerCard = new VBox(15,
                registerTitle,
                lblName,     nameField,
                lblPassword, passwordField,
                lblConfirm,  confirmField,
                registerBtn,
                statusLabel,
                separator,
                alreadyLabel,
                backLoginBtn
        );

        registerCard.setAlignment(Pos.CENTER_LEFT);
        registerCard.setPadding(new Insets(30));
        registerCard.setMaxWidth(400);
        registerCard.setStyle(CARD_STYLE);


        VBox root = new VBox(30, header, registerCard);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #F5F5F5;");

        scene = new Scene(root, 700, 700);
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
 
    private static final String BTN_OUTLINE =
            "-fx-background-color: transparent;" +
            "-fx-border-color: #E91E8C;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 5;" +
            "-fx-text-fill: #E91E8C;" +
            "-fx-font-weight: bold;";
}

