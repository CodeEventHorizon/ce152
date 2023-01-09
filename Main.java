package assignment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Start: Exercise 1 & 2 */

        System.out.println("Please enter either 1 for percentage 2 for altitude");
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNextLine()) {
            String choose = userInput.nextLine();
            if (choose.equals("1")) {
                /* Start: Exercise 1 Part C */
                System.out.println("Please enter an altitude:");
                String scanned = userInput.nextLine();
                if (scanned.getClass().getSimpleName().equals("String") && scanned.equals("quit")) {
                    System.out.println("Bye!");
                    break;
                } else if (scanned.matches("(\\-)?(\\d+)(\\.\\d+)?")) { // this regular expression checks if it starts with a minus sign, then checks 0-9 digits (which must occur), then checks if there is a dot and then followed by any number.
                    double convert = Double.parseDouble(scanned); // 2000. won't work (it must be followed by a number)
                    Earth ea = new Earth();
                    ea.readDataArray("earth.xyz");
                    System.out.println("Proportion of coordinates above " + scanned + " meters:");
                    ea.percentageAbove(convert);
                    System.out.println("please enter either 1 or 2");
                    System.out.println("Please enter either 1 for percentage 2 for altitude");
                } else if (scanned.getClass().getSimpleName().equals("String")) {
                    System.out.println("Invalid altitude. Please enter an altitude or \"quit\" to end program.\n");
                    System.out.println("Please enter either 1 or 2");
                    System.out.println("Please enter either 1 for percentage 2 for altitude");
                }
                /* End: Exercise 1 Part C */
            }
            else if (choose.equals("2")) {
                /* Start: Exercise 2 Part D */
                System.out.println("Please enter a longitude (0-360) and latitude (-90-90): ");
                String scanned = userInput.nextLine();
                if (scanned.getClass().getSimpleName().equals("String") && scanned.equals("quit")) {
                    System.out.println("Bye!");
                    break;
                } else if (scanned.matches("^(\\d{1,3})(\\.\\d+)?(\\s)(\\-)?(\\d{1,2})(\\.\\d+)?(\\s+)?$")) {
                    /* explanation of regular expression: ^ must start at the beginning of the line (\\d{1,3}) must be numbers 0-9 with a length of 1-3 (\\.\\d+)? checks for the decimal place .1213
                        * (\\s) there must be a whitespace (\\-)? checks for a sign of a number (\\d{1,2}) must be numbers 0-9 with a length of 1-2 (\\.\\d+)? checks for the decimal place .1213
                        * and (\\s+)? checks for additional whitespace after typing everything correctly $ checks if a line end follows */
                    Earth ea = new Earth();
                    ea.readDataMap("earth.xyz");
                    String[] scannedArray = scanned.split("(\\s)");
                    double longitude = Double.parseDouble(scannedArray[0]);
                    double latitude = Double.parseDouble(scannedArray[1]);
                    if (ea.getAltitude(longitude, latitude) == 0) {
                        System.out.println("either longitude or latitude is out of range\nPlease enter longitude and latitude within provided range\n");
                        System.out.println("please enter either 1 or 2");
                        System.out.println("Please enter either 1 for percentage 2 for altitude");
                    } else {
                        System.out.println("The altitude at longitude " + longitude + " and latitude " + latitude + " is " + ea.getAltitude(longitude, latitude) + " meters.\n");
                        System.out.println("please enter either 1 or 2");
                        System.out.println("Please enter either 1 for percentage 2 for altitude");
                    }
                } else if (scanned.getClass().getSimpleName().equals("String")) {
                    System.out.println("Please enter valid longitude/latitude or \"quit\" to end program.\n");
                    System.out.println("please enter either 1 or 2");
                    System.out.println("Please enter either 1 for percentage 2 for altitude");
                }
                /* End: Exercise 2 Part D */
            } else if (choose.getClass().getSimpleName().equals("String") && choose.equals("quit")) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Invalid Input!");
            }
        }
    }
}
