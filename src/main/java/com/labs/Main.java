package com.labs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

                if (file.length() == 0) { // if file is empty
                    System.out.print("How many houses do you want to build? ");
                    int countHouses = inputIntOneParam(0);
                    buildHouse(houses, countHouses, file);
                } else { // if file is not empty
                    readDataBase(file, houses);

                    System.out.println("Please, select an action (1-4):");

                    System.out.println("1) Add elements");
                    System.out.println("2) Viewing elements");
                    System.out.println("3) Comparison of elements");
                    System.out.println("4) Exit");

                    int choice = inputIntTwoParam(1, 4);

                    switch (choice) {
                        case 1: // Add elements
                            System.out.print("How many houses do you want to build? ");
                            int countHouses = inputIntOneParam(0);
                            buildHouse(houses, countHouses, file);
                            break;
                        case 2: // Viewing elements
                            System.out.println("1) General view of elements");
                            System.out.println("2) Detailed view of a separate house");
                            System.out.println("3) Exit");

                            int inChoice = inputIntTwoParam(1, 3);
                            switch (inChoice) {
                                case 1: // General view of elements
                                    System.out.println("All houses built:");
                                    for (int i = 0; i < houses.size(); ++i) {
                                        System.out.println("House number " + (i + 1) + ": " + houses.get(i));
                                    }
                                    break;
                                case 2: // Detailed view of a separate house
                                    System.out.print("Choose a house number (1-" + houses.size() + "): ");
                                    int houseNumber = inputIntTwoParam(1, houses.size());
                                    try {
                                        System.out.println("Total area of the house: " + houses.get(houseNumber - 1).getHouseArea());
                                        System.out.println("Number of floors of the house: " + houses.get(houseNumber - 1).getCountFloors());
                                        System.out.println("Number of people living: " + houses.get(houseNumber - 1).getCountPeople());
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                    }
                                    break;
                                case 3: // Exit
                                    break;
                                default:
                                    System.out.println("Please, try again");
                            }
                            break;
                        case 3: // Comparison of elements
                            System.out.println("1) Compare houses");
                            System.out.println("2) Compare flats");
                            System.out.println("3) Exit");

                            int compareChoice = inputIntTwoParam(1, 3);
                            int houseNumber1 = 0;
                            int houseNumber2 = 0;
                            switch (compareChoice) {
                                case 1: // Compare houses
                                    System.out.println("Please, enter a number of house 1 (1-" + houses.size() + "):");
                                    houseNumber1 = inputIntTwoParam(1, houses.size());
                                    System.out.println("Please, enter a number of house 2 (1-" + houses.size() + "):");
                                    houseNumber2 = inputIntTwoParam(1, houses.size());
                                    try {
                                        houses.get(houseNumber1 - 1).equalsHouses(houses.get(houseNumber2 - 1));
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                    }
                                    break;
                                case 2: // Compare flats
                                    System.out.println("Please, enter a number of the house 1 (1-" + houses.size() + "):");
                                    houseNumber1 = inputIntTwoParam(1, houses.size());
                                    int countFlatInHouse1 = houses.get(houseNumber1 - 1).getCountAllFlats();
                                    System.out.println("Please, enter a number of the flat 1 (1-" + countFlatInHouse1 + "):");
                                    int flatNumber1 = inputIntTwoParam(1, countFlatInHouse1);

                                    System.out.println("Please, enter a number of house 2 (1-" + houses.size() + "):");
                                    houseNumber2 = inputIntTwoParam(1, houses.size());
                                    int countFlatInHouse2 = houses.get(houseNumber2 - 1).getCountAllFlats();
                                    System.out.println("Please, enter a number of the flat 2 (1-" + countFlatInHouse2 + "):");
                                    int flatNumber2 = inputIntTwoParam(1, countFlatInHouse2);

                                    try {
                                        houses.get(houseNumber1 - 1).equalsFlats(flatNumber1, houses.get(houseNumber2 - 1), flatNumber2);
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                    }
                                    System.out.println();
                                    break;
                                case 3: // Exit
                                    break;
                                default:
                                    System.out.println("Please, try again");
                            }
                            break;
                        case 4: // Exit
                            break;
                        default:
                            System.out.println("Please, try again");
                    }
                }
            } catch (IOException e) {
                System.out.println("Something went wrong");
                System.out.println("Error: " + e);
            }
        }
    }

    public static int inputIntOneParam(int lowerBound) {
        Scanner in = new Scanner(System.in);

        int data = 0;
        try {
            data = in.nextInt();
            while (data < lowerBound) {
                System.out.println("Please, try again");
                data = in.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        return data;
    }

    public static int inputIntTwoParam(int lowerBound, int upperBound) {
        Scanner in = new Scanner(System.in);

        int data = 0;
        try {
            data = in.nextInt();
            while (data < lowerBound || data > upperBound) {
                System.out.println("Please, try again");
                data = in.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        return data;
    }

    public static void buildHouse(ArrayList<House> houses, int countHouses, File file) {
        Scanner in = new Scanner((System.in));

        try {
            for (int i = 0; i < countHouses; ++i) {
                System.out.print("How many floors will there be in house number " + (i + 1) + "? ");
                int countFloors = inputIntOneParam(1);
                System.out.print("How many flats will there be on one floor? ");
                int countFlatsOnOneFloor = inputIntOneParam(1);
                System.out.print("What will be the area of a flat? ");
                double flatArea = in.nextDouble(); //////////////////////////////////////////
                System.out.println();

                houses.add(new House(countFloors, countFlatsOnOneFloor, flatArea));
                fillDataBase(file, houses.get(houses.size() - 1));

                System.out.println("You have built " + countHouses + " houses");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
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
