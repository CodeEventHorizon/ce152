package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Plot extends JComponent implements Runnable, MouseListener {
    public String fileName = "earth.xyz";

    private static final int WIDTH = 720, HEIGHT = 360;
    private Canvas canvas;
    private double[][] coords;
    private double firstSeaChanger = 0;
    public double lastSeaChanger = 0;
    private double currentSeaLevel = 0;

    private int scaleX(double x) {
        return (int) (x)*(WIDTH/360);
    }

    private int scaleY(double y) {
        return (int) (-y + 90) * (HEIGHT / 180);
    }

    private Plot() {
        readCoords("earth.xyz");
        JFrame f = new JFrame("Earth Map");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(canvas = new Canvas());
        canvas.addMouseListener(this);
        f.setSize(WIDTH+17, HEIGHT+39);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            render();
        }
    }

    private void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        if (firstSeaChanger != lastSeaChanger) {
            seaLevel(firstSeaChanger);
            firstSeaChanger = lastSeaChanger;
        }
        IntStream.range(0, coords.length).forEach(i -> {
            if (coords[i][2] < 200 && coords[i][2] > 0) {
                float[] hsb = Color.RGBtoHSB(2, 122, 0, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] > 200 && coords[i][2] < 500) {
                float[] hsb = Color.RGBtoHSB(24, 181, 22, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] > 500 && coords[i][2] < 800) {
                float[] hsb = Color.RGBtoHSB(206, 247, 94, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] > 800 && coords[i][2] < 1200) {
                float[] hsb = Color.RGBtoHSB(191, 185, 57, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] > 1200 && coords[i][2] < 2500) {
                float[] hsb = Color.RGBtoHSB(102, 66, 0, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] > 2500) {
                float[] hsb = Color.RGBtoHSB(204, 217, 204, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] < 0 && coords[i][2] > -1000) {
                float[] hsb = Color.RGBtoHSB(131, 183, 235, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] < -1000 && coords[i][2] > -2000) {
                float[] hsb = Color.RGBtoHSB(48, 125, 201, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] < -2000 && coords[i][2] > -3000) {
                float[] hsb = Color.RGBtoHSB(16, 90, 163, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] < -3000 && coords[i][2] > -4000) {
                float[] hsb = Color.RGBtoHSB(8, 74, 140, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] < -4000 && coords[i][2] > -5000) {
                float[] hsb = Color.RGBtoHSB(1, 55, 110, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            } else if (coords[i][2] < -5000) {
                float[] hsb = Color.RGBtoHSB(1, 34, 66, null);
                g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                g.fillOval((int) coords[i][0] - 1, (int) coords[i][1], 5, 5);
            }
        });

        g.dispose();
        bs.show();
    }

    public void readCoords(String fileName) {
        try {
            File myFile = new File(fileName);
            Scanner input = new Scanner(myFile);
            Scanner rows = new Scanner(myFile);

            int numRows = 0;
            while (rows.hasNextLine()) {
                rows.nextLine();
                numRows++;
            }
            coords = new double[numRows][3];
            for (int i = 0; i < numRows; i++) {
                String fileString = input.nextLine();
                String[] fileStringArray = fileString.split("\t");
                coords[i][0] = this.scaleX(Double.parseDouble(fileStringArray[0]));
                coords[i][1] = this.scaleY(Double.parseDouble(fileStringArray[1]));
                coords[i][2] = Double.parseDouble(fileStringArray[2]);
            }

            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public double[][] seaLevel(double altitude) {
        currentSeaLevel += altitude;
        for (int i = 0; i < coords.length; i++) {
            coords[i][2] = coords[i][2] - altitude;
        }
        return coords;
    }



    public static boolean isLeftMouseButton(MouseEvent e) {
        return SwingUtilities.isLeftMouseButton(e);
    }
    public static boolean isRightMouseButton(MouseEvent e) {
        return SwingUtilities.isRightMouseButton(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isLeftMouseButton(e)) {
            double longitude = e.getX() * (360.0/WIDTH);
            double latitude = -(e.getY() * (180.0/HEIGHT)) + 90.0;
            System.out.println("Pressed Coordinate: Longitude: "+longitude+"; Latitude: "+latitude+"; Sea Level: "+currentSeaLevel);

            Earth ea = new Earth();
            ea.readDataMap(fileName);
            double altitude = ea.getAltitude(longitude, latitude) - currentSeaLevel;
            System.out.println("Altitude: "+altitude);


            MapCoordinate.ALTITUDE = altitude;
            MapCoordinate.LATITUDE = latitude;
            MapCoordinate.LONGITUDE = longitude;
            MapCoordinate mc = new MapCoordinate(longitude, latitude, altitude);
            List<MapCoordinate> listMc = new ArrayList<>();
            listMc.add(mc);
            for (int i = 0; i < listMc.size(); i++) {
                System.out.println(listMc.get(i));
            }


        } else if (isRightMouseButton(e)) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        Plot pl = new Plot();
        System.out.println("Current Sea level: 0 meters\nPlease input the rise or fall in sea level:");
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()){
            String seaAltitude = input.nextLine();
            if (seaAltitude.equals("reset")) {
                pl.readCoords("earth.xyz");
                System.out.println("The Map has been reset!");
            } else if (seaAltitude.matches("(\\-)?(\\d+)(\\.\\d+)?")) {
                pl.seaLevel(Double.parseDouble(seaAltitude));
                System.out.println("Altitude has changed by: "+seaAltitude+"\nPlease input the rise or fall in sea level or \"reset\" to reset the map:");
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}
