package com.accounting.view;

import com.accounting.model.House;

import java.util.List;
import java.util.Scanner;

public class ViewService {

    public String getViewAllHouses(List<House> houses) {
        String housesInfo = "";
        for (House house : houses) {
            housesInfo += house + "\n";
        }

        return housesInfo;
    }

    public String getViewHouseById(List<House> houses, int id) {
        return houses.get(id).toString();
    }

    public String getIdExistingHouses(List<House> houses) {
        String idExistingHouses = "";
        for (House house : houses) {
            idExistingHouses += house.getId() + " ";
        }

        return idExistingHouses;
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

    public boolean inputYesNo() {
        Scanner in = new Scanner(System.in);

        String answer = null;

        try {
            answer = in.next();
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }

        String yes = new String("yes");
        String no = new String("no");

        while (true) {
            assert answer != null;
            if (answer.equals(yes) || answer.equals(no)) {
                break;
            }
            System.out.println("Please, try again");
            try {
                answer = in.next();
            } catch (Exception exception) {
                System.out.println("Something went wrong: " + exception.getMessage());
            }
        }

        return answer.equals(yes);
    }

}
