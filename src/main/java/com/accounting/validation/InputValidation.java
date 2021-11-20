package com.accounting.validation;

import java.util.Scanner;

public class InputValidation {

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
