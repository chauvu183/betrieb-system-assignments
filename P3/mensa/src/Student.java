import java.util.Random;

public class Student extends Thread{
    public static final int MAX_SLEEP = 10000;
    private final String name;

    Student(String name){this.name = name;}

    @Override
    //student is looking for foods for a random time and then pay for that food .
    public void run(){
        Random random = new Random();
        // it determines if a thread is still running and exit the process
        while(this.isAlive()){
            try{
                //System.out.println(this.name + " is now standing at the cash register.");
                Thread.sleep(random.nextInt(MAX_SLEEP));
                System.out.println(this.name + " want to pay for his food.");
                this.pay();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(this.name + " leaves the cash register");
    }


    public void pay(){
        try{
            CashRegister cashRegister = MensaSimulation.getShortestCasher();

            cashRegister.pay(this.name);
        } catch (Exception e){
            System.out.println("Cant be payed");
     }
    }
}
