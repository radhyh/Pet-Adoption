/*Name: NUR DAMIA HANI BINTI AHMAD SHA
  Matric Number: 2515376 */
public class Dog extends Pet {
    
   private boolean trained;

    
    public Dog(String name,int age,String breed, boolean trained ){
    
       super(name, breed, age);
       this.trained= trained;
    
}


@Override

public void sound(){
    System.out.println("woof!");
}

public boolean isTrained(){
    return trained;
}

public void setTrained(boolean trained){
    this.trained=trained;
}
}
