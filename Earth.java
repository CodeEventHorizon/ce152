package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Earth {
    /* Start: Exercise 1 */

    private double[][] arrayOfEarth;
    private Map<String, Double> mapOfEarth;
    /* Start: Part A */
    public void readDataArray(String fileName) {
        try {
            File myFile = new File(fileName);
            Scanner input = new Scanner(myFile);
            Scanner rows = new Scanner(myFile);

            int numRows = 0;

            while (rows.hasNextLine()) {
                rows.nextLine();
                numRows++;
            }

            arrayOfEarth = new double[numRows][3];

            for (int i = 0; i < numRows; i++) {
                String fileString = input.nextLine();
                String[] fileStringArray = fileString.split("\t");
                for (int j = 0; j < fileStringArray.length; j++) {
                    arrayOfEarth[i][j] = Double.parseDouble(fileStringArray[j]);
                }
            }
            input.close(); // close scanner
            rows.close(); // close scanner
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

    }
    /* End: Part A */
    /* Start: Part B */

    public List<Integer> coordinatesAbove(double altitude) {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> realList1 = new ArrayList<>();
        for (int i = 0; i < arrayOfEarth.length; i++) {
            list1.add((int) arrayOfEarth[i][2]);
        }
        for (int k = 0; k < list1.size(); k++) {
            if (list1.get(k) > altitude) {
                realList1.add(list1.get(k));
            }
        }
        return realList1;
    }

    public List<Integer> coordinatesBelow(double altitude) {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> realList1 = new ArrayList<>();
        for (int i = 0; i < arrayOfEarth.length; i++) {
            list1.add((int) arrayOfEarth[i][2]);
        }
        for (int k = 0; k < list1.size(); k++) {
            if (list1.get(k) < altitude) {
                realList1.add(list1.get(k));
            }
        }
        return realList1;
    }

    public void percentageAbove(double altitude) {
        double value =  ((double) coordinatesAbove(altitude).size() / (double) arrayOfEarth.length) * 100.0;
        System.out.printf( "%.1f", value );
        System.out.println("%");
    }

    public void percentageBelow(double altitude) {
        double value =  ((double) coordinatesBelow(altitude).size() / (double) arrayOfEarth.length) * 100.0;
        System.out.printf( "%.1f", value );
        System.out.println("%");
    }
    /* End: Part B */
    /* End: Exercise 1*/

    /* Start: Exercise 2*/

    /* Start: Part A */
    public void readDataMap(String fileName) {

        try {
            File myFile = new File(fileName);
            Scanner input = new Scanner(myFile);

            mapOfEarth = new HashMap<String, Double>();


            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] lineArray = line.split("\\s");

                double longitude = Double.parseDouble(lineArray[0]);
                double latitude = Double.parseDouble(lineArray[1]);
                double altitude = Double.parseDouble(lineArray[2]);
                String coordinate = longitude + " " + latitude; // concat longitude and latitude to form a unique coordinate, because Maps don't allow duplicate keys
                mapOfEarth.put(coordinate, altitude);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /* End: Part A*/

    /* Start: Part C */
    public double getAltitude(double longitude, double latitude) {
        String coordinate = longitude + " " + latitude;
        if (mapOfEarth.get(coordinate) == null) {
            return 0;
        } else {
            return mapOfEarth.get(coordinate);
        }
    }
    /* End: Part C */

    /* Start: Part B */
    public void generateMap(double resolution) {

    }
    /* End: Part B */

    /* End: Exercise 2*/

}
