package com.accounting.view;

import com.accounting.model.House;
import com.accounting.service.DataBaseService;
import com.accounting.service.HouseService;
import com.accounting.validation.InputValidation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {

    public void start() {
        HouseService houseService = new HouseService();
        InputValidation inputValidation = new InputValidation();
        DataBaseService dataBaseService = new DataBaseService();

        List<House> houses = new ArrayList<>();

        String dataBaseName = "src\\main\\resources\\database.txt";

        boolean work = true;

        while (work) {

            if (houses.isEmpty() && new File(dataBaseName).length() != 0) {
                dataBaseService.readDataBase(houses, dataBaseName);
            }

            int minOption = 1;
            int maxOption = 6;

            System.out.println("Select an option (" + minOption + "-" + maxOption + "):");
            System.out.println("1 - Add the house");
            System.out.println("2 - Remove the house");
            System.out.println("3 - Viewing existing houses");
            System.out.println("4 - Detailed view of the house");
            System.out.println("5 - Compare houses");
            System.out.println("6 - Exit");

            int userChoice = inputValidation.inputIntValue(minOption, maxOption);
            System.out.println();

            int minCountHouses = 1;
            int maxCountHouses = houses.size();

            switch (userChoice) {
                case 1: // add the house
                    int minCountFloorsOrFlats = 1;

                    System.out.print("Enter the number of floors: ");
                    int countFloors = inputValidation.inputIntValue(minCountFloorsOrFlats);

                    System.out.print("Enter the number of apartments on a floor: ");
                    int countFlatsOnFloor = inputValidation.inputIntValue(minCountFloorsOrFlats);

                    houseService.addHouse(houses, countFloors, countFlatsOnFloor);
                    dataBaseService.writeDataBase(houses, dataBaseName);
                    break;
                case 2: // remove the house
                    if (houses.isEmpty()) {
                        System.out.println("No houses to remove");
                        break;
                    }

                    System.out.print("Select the house to remove (" + minCountHouses + "-" + maxCountHouses + "): ");
                    int removeHouseChoice = inputValidation.inputIntValue(minCountHouses, maxCountHouses);

                    houseService.removeHouse(houses, removeHouseChoice - 1);
                    dataBaseService.writeDataBase(houses, dataBaseName);
                    break;
                case 3: // viewing existing houses
                    if (houses.isEmpty()) {
                        System.out.println("No houses to view");
                    } else {
                        System.out.println("Existing houses:");
                        System.out.println(houseService.viewAllHouses(houses));
                    }
                    break;
                case 4: // detailed view of the house
                    if (houses.isEmpty()) {
                        System.out.println("No houses to view");
                        break;
                    }

                    System.out.print("Select the house to detailed view (" + minCountHouses + "-" + maxCountHouses + "): ");
                    int viewHouseChoice = inputValidation.inputIntValue(minCountHouses, maxCountHouses);

                    int minDetailedOption = 1;
                    int maxDetailedOption = 4;

                    boolean detailedViewWork = true;

                    while (detailedViewWork) {

                        System.out.println("Select an option to detailed view (" + minDetailedOption + "-" + maxDetailedOption + "):");
                        System.out.println("1 - The total area of the house");
                        System.out.println("2 - The number of floors of the house");
                        System.out.println("3 - The number of people living in the house");
                        System.out.println("4 - Exit");

                        int detailedChoice = inputValidation.inputIntValue(minDetailedOption, maxDetailedOption);
                        System.out.println();

                        switch (detailedChoice) {
                            case 1: // the total area of the house
                                System.out.println("The total area of the house: " +
                                        houseService.getHouseArea(houses, viewHouseChoice - 1));
                                break;
                            case 2: // the number of floors of the house
                                System.out.println("The number of floors of the house: " +
                                        houseService.getCountFloors(houses, viewHouseChoice - 1));
                                break;
                            case 3: // the number of people living in the house
                                System.out.println("The number of people living in the house: " +
                                        houseService.getCountPeople(houses, viewHouseChoice - 1));
                                break;
                            case 4: // exit
                                detailedViewWork = false;
                                break;
                            default:
                                System.out.println("Please, try again");
                        }
                        System.out.println();
                    }
                    break;
                case 5: // compare houses
                    System.out.println("Enter the number of the first house for comparison (" +
                            minCountHouses + "-" + maxCountHouses + "): ");
                    int firstHouseNumber = inputValidation.inputIntValue(minCountHouses, maxCountHouses);
                    System.out.println("Enter the number of the second house for comparison (" +
                            minCountHouses + "-" + maxCountHouses + "): ");
                    int secondHouseNumber = inputValidation.inputIntValue(minCountHouses, maxCountHouses);

                    if (houseService.compare(houses, firstHouseNumber - 1, secondHouseNumber - 1)) {
                        System.out.println("These are identical houses");
                    } else {
                        System.out.println("These are different houses");
                    }
                    break;
                case 6: // exit
                    work = false;
                    break;
                default:
                    System.out.println("Please, try again");
            }

            System.out.println();

        }
    }

}
