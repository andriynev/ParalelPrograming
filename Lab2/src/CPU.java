public class CPU extends Thread {

    public boolean busy;
    private String processorName;

    private CPUProcess workingProcess;

    CPU(String name) {
        processorName = name;
    }

    public boolean isBusy(){
        return this.busy;
    }

    public synchronized void loadProcess(CPUProcess process){
        workingProcess = process;
    }

    @Override
    public void run(){
        while(true) {
            if (workingProcess != null) {
                System.out.println("Start working: processor " + processorName + " with process #" + workingProcess.getId());
                busy = true;
                try {
                    sleep(Math.abs(workingProcess.getComplexity()) * 1000 + 1300);
                }
                catch ( InterruptedException e ) {}
                this.busy = false;
                System.out.println("Finished work: processor " + processorName + " with process #" + workingProcess.getId());
                workingProcess = null;
            }
            try {
                sleep(3000);
            } catch ( InterruptedException e ) {}
        }
    }
}