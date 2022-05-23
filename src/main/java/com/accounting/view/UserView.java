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
                houses = HouseService.getInstance().findAllHousesSQL();
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

            int userChoice = ViewService.getInstance().inputIntValue(minOption, maxOption);
            System.out.println();

            switch (userChoice) {
                case 1: // add the house
                    int minCountFloorsOrFlats = 1;

                    System.out.print("Enter the number of floors: ");
                    int countFloors = ViewService.getInstance().inputIntValue(minCountFloorsOrFlats);

                    System.out.print("Enter the number of apartments on a floor: ");
                    int countFlatsOnFloor = ViewService.getInstance().inputIntValue(minCountFloorsOrFlats);

                    House house = HouseService.getInstance().createHouse(countFloors, countFlatsOnFloor);

                    houses.add(house); // add in list
                    HouseService.getInstance().saveHouseSQL(house); // add in database
                    break;
                case 2: // remove the house
                    if (houses.isEmpty()) {
                        System.out.println("No houses to remove");
                        break;
                    }

                    System.out.print("Select the house to remove: ");
                    ViewService.getInstance().viewIdAllHouses(houses);
                    int removeHouseId = ViewService.getInstance().inputHouseId(houses);

                    House h = HouseService.getInstance().createHouse(1, 2);
                    HouseService.getInstance().deleteHouseSQL(h);

                    HouseService.getInstance().deleteHouseSQL(
                            HouseService.getInstance().findHouseSQL(removeHouseId)); // delete in database
                    houses.remove(HouseService.getInstance().findHouse(houses, removeHouseId)); // delete in list
                    break;
                case 3: // viewing existing houses
                    if (houses.isEmpty()) {
                        System.out.println("No houses to view");
                    } else {
                        System.out.println("Existing houses:");
                        ViewService.getInstance().viewAllHouses(houses);
                    }
                    break;
                case 4: // detailed view of the house
                    if (houses.isEmpty()) {
                        System.out.println("No houses to view");
                        break;
                    }

                    System.out.print("Select the house to detailed view: ");
                    ViewService.getInstance().viewIdAllHouses(houses);
                    int viewHouseId = ViewService.getInstance().inputHouseId(houses);

                    int minDetailedOption = 1;
                    int maxDetailedOption = 4;

                    boolean detailedViewWork = true;

                    while (detailedViewWork) {

                        System.out.println("Select an option to detailed view (" + minDetailedOption + "-" + maxDetailedOption + "):");
                        System.out.println("1 - The total area of the house");
                        System.out.println("2 - The number of floors of the house");
                        System.out.println("3 - The number of people living in the house");
                        System.out.println("4 - Exit");

                        int detailedChoice = ViewService.getInstance().inputIntValue(minDetailedOption, maxDetailedOption);
                        System.out.println();

                        switch (detailedChoice) {
                            case 1: // the total area of the house
                                System.out.println("The total area of the house: " + HouseService.getInstance()
                                        .getHouseArea(HouseService.getInstance().findHouse(houses, viewHouseId)));
                                break;
                            case 2: // the number of floors of the house
                                System.out.println("The number of floors of the house: " + HouseService.getInstance()
                                        .getCountFloors(HouseService.getInstance().findHouse(houses, viewHouseId)));
                                break;
                            case 3: // the number of people living in the house
                                System.out.println("The number of people living in the house: " + HouseService.getInstance()
                                        .getCountPeople(HouseService.getInstance().findHouse(houses, viewHouseId)));
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
                    ViewService.getInstance().viewIdAllHouses(houses);
                    int firstHouseId = ViewService.getInstance().inputHouseId(houses);
                    System.out.print("Select the second house for comparison: ");
                    ViewService.getInstance().viewIdAllHouses(houses);
                    int secondHouseId = ViewService.getInstance().inputHouseId(houses);

                    if (HouseService.getInstance().compare(HouseService.getInstance().findHouse(houses, firstHouseId),
                            HouseService.getInstance().findHouse(houses, secondHouseId))) {
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
