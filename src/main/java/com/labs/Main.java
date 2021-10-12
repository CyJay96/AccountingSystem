package com.labs;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        while (true) {
            ArrayList<House> houses = new ArrayList<>(); // houses

            System.out.print("Do you want to continue? (yes/no) ");
            String answer = in.next();

            String yes = new String("yes");
            String no = new String("no");

            while (!answer.equals(yes) && !answer.equals(no)) {
                System.out.println("Please, try again");
                answer = in.next();
            }

            if (answer.equals(no)) {
                break;
            }

            // work with data
            try {
                String fileName = "DataBase.txt";
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }

                if (file.length() == 0) { // if the file is empty
                    System.out.print("How many houses do you want to build? ");
                    int countHouses = in.nextInt();
                    buildHouse(houses, countHouses, file);
                } else { // if the file is not empty
                    houses.clear();
                    readDataBase(file, houses);

                    System.out.println("Please, select an action: (1-3)");

                    System.out.println("1) Add elements");
                    System.out.println("2) Viewing elements");
                    System.out.println("3) Comparison of elements");
                    System.out.println("4) Exit");

                    int choice = in.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("How many houses do you want to build? ");
                            int countHouses = in.nextInt();
                            buildHouse(houses, countHouses, file);
                            break;
                        case 2:
                            System.out.println("All houses built:");
                            for (int i = 0; i < houses.size(); ++i) {
                                System.out.println("House number " + (i + 1) + ": " + houses.get(i));
                            }
                            break;
                        case 3:
                            ///
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Please, try again");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public static void buildHouse(ArrayList<House> houses, int countHouses, File file) {
        Scanner in = new Scanner((System.in));

        for (int i = 0; i < countHouses; ++i) {
            System.out.print("How many floors will there be in house number " + (i + 1) + "? ");
            int countFloors = in.nextInt();
            System.out.print("How many flats will there be on one floor? ");
            int countFlatsOnOneFloor = in.nextInt();
            System.out.print("What will be the area of a flat? ");
            int flatArea = in.nextInt();
            System.out.println();

            houses.add(new House(countFloors, countFlatsOnOneFloor, flatArea));
            fillDataBase(file, houses.get(houses.size() - 1));
        }

        System.out.println("You have built " + countHouses + " houses");
    }

    public static void fillDataBase(File file, House house) {
        BufferedWriter bufferedWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            bufferedWriter = new BufferedWriter(new FileWriter(file, true));

            bufferedWriter.write(String.valueOf(house.getCountFloors()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(house.getCountFlatsOnOneFloor()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(house.getFlatArea()));
            bufferedWriter.newLine();
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public static void readDataBase(File file, ArrayList<House> houses) {
        BufferedReader bufferedReader = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }

                int countFloors = Integer.parseInt(line);
                line = bufferedReader.readLine();
                int countFlatsOnOneFloor = Integer.parseInt(line);
                line = bufferedReader.readLine();
                double flatArea = Double.parseDouble(line);

                houses.add(new House(countFloors, countFlatsOnOneFloor, flatArea));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
