package sudheesh16mb.com.womensafety;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by KUTTAN on 16-08-2016.
 */
public class GeofenceController {
// region Properties

    private final String TAG = GeofenceController.class.getName();

    private Context context;
    private GoogleApiClient googleApiClient;
    private Gson gson;
    private SharedPreferences prefs;
    private GeofenceControllerListener listener;

    private List<NamedGeofense> namedGeofences;

    private Geofence geofenceToAdd;
    private NamedGeofense namedGeofenceToAdd;

    // endregion

    // region Shared Instance

    private static GeofenceController INSTANCE;

    public static GeofenceController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GeofenceController();
        }
        return INSTANCE;
    }

    // endregion

    // region Public

    //we call this from main activity (in main activity we mde an instance of geofence controller
    // using static method getInstance() (like this   GeofenceController.getInstance().init(this);)
    public void init() {
        this.context = context.getApplicationContext();

        gson = new Gson();
        // namedGeofences is arraylist of my defined class name (namedGeofences)
        // i will discuss namedGeofences class .. wait a moment.. :P
        namedGeofences = new ArrayList<>();

        prefs = this.context.getSharedPreferences(Constants.SharedPrefs.Geofences, Context.MODE_PRIVATE);
        // prefs is shared prefrence (shared is a class  or local storage where we store  data so that
        // our data might not lost when we move to other class or activity)
        // variable.we assign our shared prefrence name which is define in Constants class
//public static String Geofences = "SHARED_PREFS_GEOFENCES";
        loadGeofences();

        //  loadGeofences(); is my define method where we load geofences data if they are store
        // in our sharedprefrences
    }

    // region ConnectionCallbacks

    private GoogleApiClient.ConnectionCallbacks connectionAddListener = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(Bundle bundle) {
            Intent intent = new Intent(context, AreWeThereIntentService.class);
            PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingResult<Status> result = LocationServices.GeofencingApi.
                    addGeofences(googleApiClient, getAddGeofencingRequest(), pendingIntent);
            result.setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    if (status.isSuccess()) {
                        saveGeofence();
                    } else {
                        Log.e(TAG, "Registering geofence failed: " + status.getStatusMessage() + " : " + status.getStatusCode());
                        sendError();
                    }
                }
            });
        }

        @Override
        public void onConnectionSuspended(int i) {
            Log.e(TAG, "Connecting to GoogleApiClient suspended.");
            sendError();
        }
    };

    public void addGeofence(NamedGeofense namedGeofence) {
        this.namedGeofenceToAdd = namedGeofence;
        this.geofenceToAdd = namedGeofence.geofence();


        connectWithCallbacks(connectionAddListener);
    }

    // endregion

    private GeofencingRequest getAddGeofencingRequest() {
        List<Geofence> geofencesToAdd = new ArrayList<>();
        geofencesToAdd.add(geofenceToAdd);
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofencesToAdd);
        return builder.build();
    }

    private void saveGeofence() {
        namedGeofences.add(namedGeofenceToAdd);


        String json = gson.toJson(namedGeofenceToAdd);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(namedGeofenceToAdd.id, json);
        editor.apply();
    }

    public interface GeofenceControllerListener {
        void onGeofencesUpdated();
        void onError();
    }


    public List<NamedGeofense> getNamedGeofences() {
        return namedGeofences;
    }






    // endregion

    // region Private

    private void loadGeofences() {

        // Map<String, ?>   is A Map is a data structure consisting of a set of keys and values
        // in which each key is mapped to a single value.
        // we load geofences data if they are store
        // in our sharedprefrences
        Map<String, ?> keys = prefs.getAll();
        // Loop over all geofence keys in prefs and add to namedGeofences
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            String jsonString = prefs.getString(entry.getKey(), null);
            // gson is a library where we can store our whole class instance,
            // below we assign previously store NamedGeofence class , and jsonString is key of that
            // class
            NamedGeofense namedGeofence = gson.fromJson(jsonString, NamedGeofense.class);
            // than here we just add namedGeofence instance to our array list
            namedGeofences.add(namedGeofence);
        }

        // Sort namedGeofences by name


    }


    private void connectWithCallbacks(GoogleApiClient.ConnectionCallbacks callbacks) {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(callbacks)
                .addOnConnectionFailedListener(connectionFailedListener)
                .build();
        googleApiClient.connect();
    }






    private void sendError() {
        if (listener != null) {
            listener.onError();
        }
    }

    // endregion



    // region OnConnectionFailedListener

    private GoogleApiClient.OnConnectionFailedListener connectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            Log.e(TAG, "Connecting to GoogleApiClient failed.");
            sendError();
        }
    };

    // endregion

    // region Interfaces


    // end region


}
