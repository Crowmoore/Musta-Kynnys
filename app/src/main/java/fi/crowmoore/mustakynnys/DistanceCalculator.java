package fi.crowmoore.mustakynnys;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class DistanceCalculator {

    public static final double KynnysLatitude = 62.240505;
    public static final double KynnysLongitude = 25.752150;

    Context context;

    public DistanceCalculator(Context context) {
        this.context = context;
    }

    /**
     * @return Current distance to Kynnys
     */
    public double DistanceToKynnys() {
        LocationManager lm;
        lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return 0.0;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        return distance(KynnysLatitude, latitude, KynnysLongitude, longitude);
    }


    /**
     * Calculate distance between two points of latitude and longitude
     *
     * @returns Distance in Meters
     */
    public static double distance(double firstLatitude, double secondLatitude, double firstLongitude,
                                  double secondLongitude) {

        final int RadiusOfEarth = 6371;

        double latDistance = Math.toRadians(secondLatitude - firstLatitude);
        double lonDistance = Math.toRadians(secondLongitude - firstLongitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(firstLatitude)) * Math.cos(Math.toRadians(secondLatitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanceInMeters = RadiusOfEarth * c * 1000;

        return distanceInMeters;
    }
}
