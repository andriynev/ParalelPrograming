import java.util.Random;


public class Program {

    private static Random rand = new Random();

    private CPU cpu1;
    private CPU cpu2;

    private CPUProcess newProcess;

    public static int getRandomNumber(int min, int max) {
        int tmp = min + (rand.nextInt() % (max - min + 1));
        System.out.println("Random number is: " + tmp);
        return tmp;
    }

    Program(){
        cpu1 = new CPU("Processor 1");
        cpu2 = new CPU("Processor 2");

        start();
    }

    public void start(){
        cpu1.start();
        cpu2.start();

        for (int i = 0; i < 5; ++i) {
            newProcess = new CPUProcess(getRandomNumber(0, 10));

            if (!cpu1.isBusy()) {
                cpu1.loadProcess(newProcess);
            }
            else if (!cpu2.isBusy()) {
                cpu2.loadProcess(newProcess);
            }
            else {
                System.out.println("Processes #1 and #2 are busy. Process #" + newProcess.getId() + " has been deleted");
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {}

        System.exit(0);
    }

}