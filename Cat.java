public class Cat extends Pet {
    public Cat(String name, int age, String breed, boolean indoor){
     super(name, breed, age);
     this.indoor = indoor;
    }
    
    @Override
    public void Sound(){
        System.out.println("Meow");
    }
    
    public boolean isIndoor(){
        return indoor;
    }
    
    public void setIndoor(boolean indoor){
        this.indoor = indoor;
    }
    
}

}
