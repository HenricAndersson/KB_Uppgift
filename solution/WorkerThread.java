package com.company.solution;

import java.io.*;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class WorkerThread implements Runnable {

    private final String inputLocation;
    private final int inputNumber;
    private final CountDownLatch latch;
    private Set<String> result;

    public WorkerThread(Set<String> result, String inputLocation, int inputNumber, CountDownLatch latch) {
        this.result = result;
        this.inputLocation = inputLocation;
        this.inputNumber = inputNumber;
        this.latch = latch;
    }

    @Override
    public void run() {
        File file = this.getFile();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == 'r') {
                    this.result.add(line);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // Signals to the latch that this thread is done executing.
            this.latch.countDown();
        }
    }

    private File getFile() {
        return new File(this.inputLocation).listFiles()[this.inputNumber];
    }
}
