package com.company.solution;

import java.io.File;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;

public class ListsFilter {

    /*
        Accepts a folder location and returns strings in text-files in the folder that starts with 'r' without duplicates
     */
    public static Set<String> run(String fileLocation) throws InterruptedException {

        /*
            Set is a mathematical data-structure that doesn't allow duplicates.
            This specific set implementation is thread-safe which will allow for concurrent operations.
         */
        Set<String> result = new ConcurrentSkipListSet<String>();

        int numberOfInputs = getNumberOfInputFiles(fileLocation);

        // Used to wait for all the threads to be done executing.
        CountDownLatch latch = new CountDownLatch(numberOfInputs);

        // Starts a thread for each input file
        for (int i = 0; i < numberOfInputs; i++) {
            Thread t = new Thread(new WorkerThread(result, fileLocation, i, latch));
            t.start();
        }

        latch.await();
        return result;

    }

    /*
        Checks the target folder for number of input files.
    */
    private static int getNumberOfInputFiles(String fileLocation) {
        try{
            return new File(fileLocation).listFiles().length;
        } catch(Exception e){
            throw new IllegalArgumentException();
        }
    }
}
