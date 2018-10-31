package CW;

import java.util.concurrent.TimeUnit;

public class ParallelTestClass implements Runnable{

    private char testChar = ' ';

    public void setTestChar(char c) {
        testChar = c;
    }

    @Override //for parallel Generator
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(testChar);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
