package com.accounting.view;

import com.accounting.model.House;
import com.accounting.service.ApartmentService;

import java.util.List;
import java.util.Scanner;

public class ViewService {

    private static ViewService instance;

    private ViewService() {
    }

    public static ViewService getInstance() {
        ViewService localInstance = instance;
        if (instance == null) {
            synchronized (ApartmentService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ViewService();
                }
            }
        }
        return localInstance;
    }

    public void viewAllHouses(List<House> houses) {
        houses.forEach(System.out::println);
    }

    public void viewIdAllHouses(List<House> houses) {
        houses.stream().map(House::getId).forEach((id) -> System.out.print(id + " "));
        System.out.println();
    }

    public int inputHouseId(List<House> houses) {
        Scanner in = new Scanner(System.in);

        int data = 0;

        try {
            while (in.hasNext()) {
                if (in.hasNextInt()) {
                    data = in.nextInt();

                    boolean isCorrectData = false;

                    for (House house : houses) {
                        if (data == house.getId()) {
                            isCorrectData = true;
                            break;
                        }
                    }

                    if (isCorrectData) {
                        break;
                    } else {
                        System.out.println("Please, try again");
                    }
                } else {
                    System.out.println("Please, try again");
                    in.next();
                }
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }

        return data;
    }

    public int inputIntValue(int lowerBound) {
        Scanner in = new Scanner(System.in);

        int data = 0;

        try {
            while (in.hasNext()) {
                if (in.hasNextInt()) {
                    data = in.nextInt();
                    if (data < lowerBound) {
                        System.out.println("Please, try again");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Please, try again");
                    in.next();
                }
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }

        return data;
    }

    public int inputIntValue(int lowerBound, int upperBound) {
        Scanner in = new Scanner(System.in);

        int data = 0;

        try {
            while (in.hasNext()) {
                if (in.hasNextInt()) {
                    data = in.nextInt();
                    if (data < lowerBound || data > upperBound) {
                        System.out.println("Please, try again");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Please, try again");
                    in.next();
                }
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }

        return data;
    }

}
