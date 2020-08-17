package com.company;

import com.company.solution.ListsFilter;

import java.util.Set;

public class Main {

    /*
        This program accepts a file path as input parameter
        It then starts a thread for each input file in the given folder.
        Because the files may be very large, each thread reads them line by line, as otherwise RAM may be an issue.
     */
    public static void main(String[] args) throws InterruptedException {

        try {

            // Program requires a file path as input parameter
            String fileLocation = args[0];

            if (!(fileLocation.length() > 0)) {
                throw new IllegalArgumentException();
            }


            Long startTime = System.currentTimeMillis();

            Set<String> result = ListsFilter.run(fileLocation);

            Long endTime = System.currentTimeMillis();

            result.forEach(System.out::println);
            System.out.println("Execution time: "+(endTime-startTime)+ " ms");

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {

            System.out.println("No file location specified");
            e.printStackTrace();
        }
    }
}
