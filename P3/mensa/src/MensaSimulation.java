import java.util.ArrayList;
import java.util.List;


public class MensaSimulation {
    private final static int number_cashReg = 3;
    private final static int number_students = 10;
    private final static int run_time = 10000;

    private static List<Student> students = new ArrayList<>();
    private static List<CashRegister> cashRegister = new ArrayList<>();

    public static void main(String[] args) {
        //---------INIT---------------------
        for(int i = 1; i<= number_cashReg; i++){
            cashRegister.add(new CashRegister("Cash Register " + i));
        }

        for(int j=1; j<= number_students;j++){
                Student student = new Student("Student " + j);
                student.start();
                students.add(student);
        }

        //-------START----------------------
        System.out.println("Open Stimulation with number of cashRegister: " + number_cashReg + " and number of students: " + number_students);

        try{
            // Process run in a certain time
            Thread.sleep(run_time);
        }catch (InterruptedException e){
            //Logger.getLogger(MensaSimulation.class.getName());
            Thread.currentThread().interrupt();
        }

        //---------CLOSE----------------
        System.out.println("Close the cashRegister");

        //students have to go out of the mensa
        for(Student student: students){
            student.interrupt();
        }
        //Waiting for all the students to leave
        for(Student student: students){
            try {
               // Waits for this thread to die
                student.join();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Mensa is closed.");
    }

    public static CashRegister getShortestCasher() throws Exception{
        if(cashRegister.isEmpty()){
            throw new Exception("no casher is available at the moment.");
        }

        //get the first index of the list cashRegister
        CashRegister shortesCR = cashRegister.get(0);
        // get the shortest waiting csh register
        for(CashRegister cR : cashRegister){
            if(cR.getQueueLength() < shortesCR.getQueueLength())
                shortesCR = cR;
        }
        return shortesCR;
    }
}
