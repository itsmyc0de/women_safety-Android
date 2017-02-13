package sudheesh16mb.com.womensafety;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class tab1 extends Fragment
{
    MapView mapView;
    GoogleMap googleMap;
    public View onCreateView(LayoutInflater inflater,ViewGroup group,Bundle bundle){
View view= inflater.inflate(R.layout.activity_tab1,group,false);

        mapView= (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(bundle);
        mapView.onResume();
        try
        {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        }catch (Exception e){
            e.printStackTrace();
        }
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap gMap) {
                googleMap=gMap;
                googleMap.setMyLocationEnabled(true);
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(13.2f));
                CircleOptions circleOptions=new CircleOptions();
                circleOptions.center(new LatLng(11.270742,77.605759)).fillColor(Color.GREEN).radius(50);
                googleMap.addCircle(circleOptions);
/*
                GeofenceController.getInstance().init();
                NamedGeofense geofence = new NamedGeofense();
                geofence.name ="Sudheesh";
                geofence.latitude =11.270742;   //your lati and longii  :)
                geofence.longitude =77.605759;
                geofence.radius =50;
                GeofenceController.getInstance().addGeofence(geofence);
*/
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}