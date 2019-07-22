
import java.util.Random;
import java.util.concurrent.Semaphore;


public class CashRegister {

    // maximal time to pay in casher
    public static final int SLEEPTIME = 10000;
    //Number of students can pay at the same time in casher
    public static final int PERMIT_PER_CASHR = 1;

    private final String name;

    private Semaphore queue;

    public CashRegister(String name){
        this.name = name;
        //the counter is counting are permits that allow access to the casher .
        this.queue = new Semaphore(PERMIT_PER_CASHR);
    }

    //get the length of the queue
    public int getQueueLength(){return this.queue.getQueueLength();}

    public void pay(String student){
        try{
            //Semaphore P
            //Acquires a permit, if one is available and returns immediately,
            // reducing the number of available permits by one.
            this.queue.acquire();
            System.out.println(student + " is standing at " + this.name );

            Random random = new Random();

            Thread.sleep(random.nextInt(CashRegister.SLEEPTIME));

            System.out.println(student + " has made an payment at " + this.name );

            //Semaphore V
            //Releases a permit, increasing the number of available permits by one.
            this.queue.release();

        }catch (InterruptedException e){
            System.out.println( student + " give the food back.");
            Thread.currentThread().interrupt();
            this.queue.release();
        }
    }
}
