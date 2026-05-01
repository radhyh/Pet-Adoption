/*Name: NUR AUNI FAQIHAH BINTI DK SUFIAN 
  Matric Number: 2517726 */
public class Cat extends Pet {
    
    private boolean indoor;

    public Cat(String name, int age, String breed, boolean indoor){
     super(name, breed, age);
     this.indoor = indoor;
    }
    
    @Override
    public void Sound(){
        super.Sound();
        System.out.print("Meow");
    }
    
    public boolean isIndoor(){
        return indoor;
    }
    
    public void setIndoor(boolean indoor){
        this.indoor = indoor;
    }
    
}

