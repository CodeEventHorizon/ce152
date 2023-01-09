package assignment;


public class MapCoordinate implements Comparable<MapCoordinate>{

    public static double LATITUDE;
    public static double LONGITUDE;
    public static double ALTITUDE;


    private final int earthRadius = 6371; //kilometers
    private double long1;
    private double long2;
    private double lat1;
    private double lat2;

    public MapCoordinate(double LONGITUDE, double LATITUDE, double ALTITUDE) { /*done */
        LONGITUDE = this.LONGITUDE;
        LATITUDE = this.LATITUDE;
        ALTITUDE = this.ALTITUDE;
    }

    private double toRad(double degree) {
        return (Math.PI / 180) * degree;
    }

    public double distanceTo(MapCoordinate mc) {
        double latDistance = toRad(lat2 - lat1);
        double lonDistance = toRad(long2 - long1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;
        return dist;
    }

    public int compareTo(MapCoordinate mc) {
        if (ALTITUDE == mc.ALTITUDE || LATITUDE == mc.LATITUDE || LONGITUDE == mc.LONGITUDE) {
            return 0;
        } else if (ALTITUDE > mc.ALTITUDE || LATITUDE > mc.LATITUDE || LONGITUDE > mc.LONGITUDE) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return toString().equals(obj);
    }
    @Override
    public String toString() {
        String str = "";
        str = LONGITUDE + "\t" + LATITUDE + "\t" + ALTITUDE;
        return str;
    }
}
