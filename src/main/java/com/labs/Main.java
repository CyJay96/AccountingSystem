package com.labs;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        if (true) { // если файл пустой
            System.out.print("Сколько вы хотите построить домов? ");
            int countHouses = in.nextInt();
            System.out.println();
            ArrayList<House> houses = new ArrayList<>(countHouses);

            for (int i = 0; i < countHouses; ++i) {
                System.out.print("Сколько этажей будет в доме номер " + (i + 1) + "? ");
                int countFloors = in.nextInt();
                System.out.print("Сколько квартир будет на одном этаже? ");
                int countFlatsOnOneFloor = in.nextInt();
                System.out.print("Какова будет площадь одной квартиры? ");
                int flatArea = in.nextInt();
                System.out.println();

                houses.add(new House(countFloors, countFlatsOnOneFloor, flatArea)); // builder
            }
            System.out.println();

            System.out.println("Вы построили " + countHouses + " домов");
            for (int i = 0; i < countHouses; ++i) {
                System.out.println("Дом номер " + (i + 1) + ": " + houses.get(i));
            }
        } else { // если в файле есть объекты

        }
    }
}
