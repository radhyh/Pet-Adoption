public class User {
    
    private String userId;
    Pet adoptedPets;

    public User(String userId){
        this.userId = userId;
        
    public void adoptPet(Pet p){
        adoptedPet.add(p);
    }
    
    public void returnPet(Pet p){
        adoptedPet = null;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }
}
