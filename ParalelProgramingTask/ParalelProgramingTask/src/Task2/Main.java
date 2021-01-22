package Task2;

public class Main {
    private static final int countWriter = 3;
    private static final int countReader = 4;

    public static void main(String[] args) {
        WR wr = new WR();

        for (int i = 0; i < countWriter; i++) {
            Thread t  = new Writer(wr);
            t.setName("Thread " + i);
            t.start();
        }


        for (int i = 0; i < countReader; i++) {
            Thread t  = new Reader(wr);
            t.setName("Thread " + i);
            t.start();
        }

    }
}
