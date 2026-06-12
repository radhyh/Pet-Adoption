# PAC-MAN — Pet Adoption Centre Management System
A GUI-based Pet Adoption Centre Management System developed using Java and JavaFX as part of the Object-Oriented Programming course project at International Islamic University Malaysia (IIUM).

# Group Members
Nuralia Maisara Binti Zambrie 2512336 Backend Developer (Pet Class, Pet List Screen)
Nurradhiyah Binti Ridzuan 2513510 Project Manager (AdoptionCentre class, Add Pet Screen, GitHub)
Nur Arfa Nisrina Binti Mohd Aizurizam 2517666File Handler (User class, Register Screen, File I/O)
Nur Auni Faqihah Binti DK Sufian 2517726GUI Designer (Cat class, Login Screen)
Nur Damia Hani Binti Ahmad Sha 2515376 Tester & Documenter (Dog class, Pet Adoption Screen)

- All members manage the main class and involve in report writing

# Project Description
PAC-MAN is a desktop application that allows users to browse and adopt pets from an adoption centre. The system supports two types of users which is admin and regular users, each with different types of access. All data is saved and loaded from text files to ensure persistence across sessions.


# Features
- User authentication — login and register
- Role-based access — admin and regular user
- Browse available pets in a TableView
- Adopt a pet through an adoption form
- Admin can add new pets
- Data persistence using text file storage
- Pink and white themed JavaFX GUI

 # Project Structure
 PAC-MAN/
├── Pet.java                  — base class for all pets
├── Cat.java                  — extends Pet, adds indoor attribute
├── Dog.java                  — extends Pet, adds trained attribute
├── User.java                 — stores user credentials and adopted pets
├── AdoptionCentre.java       — manages pets and users, handles file I/O
├── main.java                 — launches the application
├── LoginScreen.java          — login screen GUI
├── RegisterScreen.java       — register screen GUI
├── PetListScreen.java        — pet list screen GUI
├── PetAdoptionScreen.java    — pet adoption form GUI
├── AddPetScreen.java         — add new pet screen GUI (admin only)
├── pets.txt                  — saved pet data (auto-generated)
├── users.txt                 — saved user data (auto-generated)
└── .vscode/
    └── launch.json           — VS Code run configuration

# Requirements
- Java JDK 24
- JavaFX SDK 21.0.11
- Visual Studio Code with Extension Pack for Java

# How to Run
1. Clone or download this repository
2. Open the project folder in Visual Studio Code
3. Make sure JavaFX SDK 21.0.11 is downloaded and extracted
4. Check that .vscode/launch.json has the correct JavaFX path:
5. Press F5 to run the application

# Default Login
Username: Admin 
Password: 1234 
Role : Admin
New accounts can be registered through the Register screen. All registered accounts have the user role.

# OOP Concepts Implemented
Inheritance : Cat and Dog extend the Pet base class 
Encapsulation : All attributes are private with public getters and setters 
Polymorphism : PetAdoptionScreen handles both Cat and Dog through Pet parent class 
Abstraction : AdoptionCentre hides data management complexity behind simple methods 

# Data Files
The application automatically creates two text files when first run:

pets.txt — stores pet data in this format:

Dog,Oreo,Chihuahua,3,true,false
Cat,Chipsmore,Siamese,2,true,true

users.txt — stores user data in this format:

Admin,1234,admin
username,password,user
