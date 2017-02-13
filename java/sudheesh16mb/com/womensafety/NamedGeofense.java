package sudheesh16mb.com.womensafety;


import com.google.android.gms.location.Geofence;

import java.util.UUID;

/**
 * Created by KUTTAN on 16-08-2016.
 */
public class NamedGeofense {

    public String id;
    public String name;
    public double latitude;
    public double longitude;
    public float radius;

    // end region
// region Public
    public Geofence geofence() {
        id = UUID.randomUUID().toString();
// UUID is universal unique identifer. ref for you
// : https://docs.oracle.com/javase/7/docs/api/java/util/UUID.html
        return new Geofence.Builder()
                .setRequestId(id)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
                .setCircularRegion(latitude, longitude, radius)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
    }
}
