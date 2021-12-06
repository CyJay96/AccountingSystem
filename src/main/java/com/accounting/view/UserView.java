package com.accounting.view;

import com.accounting.model.House;
import com.accounting.service.HouseService;

import java.util.ArrayList;
import java.util.List;

public class UserView {

    public void run() {
        List<House> houses = new ArrayList<>();

        boolean work = true;

        while (work) {

            if (houses.isEmpty()) {
                houses = new HouseService().findAllHousesSQL();
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

            int userChoice = new ViewService().inputIntValue(minOption, maxOption);
            System.out.println();

            switch (userChoice) {
                case 1: // add the house
                    int minCountFloorsOrFlats = 1;

                    System.out.print("Enter the number of floors: ");
                    int countFloors = new ViewService().inputIntValue(minCountFloorsOrFlats);

                    System.out.print("Enter the number of apartments on a floor: ");
                    int countFlatsOnFloor = new ViewService().inputIntValue(minCountFloorsOrFlats);

                    House house = new HouseService().createHouse(countFloors, countFlatsOnFloor);

                    houses.add(house); // add in list
                    new HouseService().saveHouseSQL(house); // add in database
                    break;
                case 2: // remove the house
                    if (houses.isEmpty()) {
                        System.out.println("No houses to remove");
                        break;
                    }

                    System.out.print("Select the house to remove: ");
                    new ViewService().viewIdAllHouses(houses);
                    int removeHouseId = new ViewService().inputHouseId(houses);

                    House h = new HouseService().createHouse(1, 2);
                    new HouseService().deleteHouseSQL(h);

                    new HouseService().deleteHouseSQL(new HouseService().findHouseSQL(removeHouseId)); // delete in database
                    houses.remove(new HouseService().findHouse(houses, removeHouseId)); // delete in list
                    break;
                case 3: // viewing existing houses
                    if (houses.isEmpty()) {
                        System.out.println("No houses to view");
                    } else {
                        System.out.println("Existing houses:");
                        new ViewService().viewAllHouses(houses);
                    }
                    break;
                case 4: // detailed view of the house
                    if (houses.isEmpty()) {
                        System.out.println("No houses to view");
                        break;
                    }

                    System.out.print("Select the house to detailed view: ");
                    new ViewService().viewIdAllHouses(houses);
                    int viewHouseId = new ViewService().inputHouseId(houses);

                    int minDetailedOption = 1;
                    int maxDetailedOption = 4;

                    boolean detailedViewWork = true;

                    while (detailedViewWork) {

                        System.out.println("Select an option to detailed view (" + minDetailedOption + "-" + maxDetailedOption + "):");
                        System.out.println("1 - The total area of the house");
                        System.out.println("2 - The number of floors of the house");
                        System.out.println("3 - The number of people living in the house");
                        System.out.println("4 - Exit");

                        int detailedChoice = new ViewService().inputIntValue(minDetailedOption, maxDetailedOption);
                        System.out.println();

                        switch (detailedChoice) {
                            case 1: // the total area of the house
                                System.out.println("The total area of the house: " +
                                        new HouseService().getHouseArea(new HouseService().findHouse(houses, viewHouseId)));
                                break;
                            case 2: // the number of floors of the house
                                System.out.println("The number of floors of the house: " +
                                        new HouseService().getCountFloors(new HouseService().findHouse(houses, viewHouseId)));
                                break;
                            case 3: // the number of people living in the house
                                System.out.println("The number of people living in the house: " +
                                        new HouseService().getCountPeople(new HouseService().findHouse(houses, viewHouseId)));
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
                    System.out.print("Select the first house for comparison: ");
                    new ViewService().viewIdAllHouses(houses);
                    int firstHouseId = new ViewService().inputHouseId(houses);
                    System.out.print("Select the second house for comparison: ");
                    new ViewService().viewIdAllHouses(houses);
                    int secondHouseId = new ViewService().inputHouseId(houses);

                    if (new HouseService().compare(new HouseService().findHouse(houses, firstHouseId),
                            new HouseService().findHouse(houses, secondHouseId))) {
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
