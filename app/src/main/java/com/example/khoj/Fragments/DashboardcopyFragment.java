package com.example.khoj.Fragments;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.khoj.Fragments.Hotel.DescriptionHotelFragment;
import com.example.khoj.R;
import com.example.khoj.Services.HotelDto;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.os.Looper.getMainLooper;

public class  DashboardcopyFragment extends Fragment implements PermissionsListener {
    private static final long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private static final long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;
    private  MapboxMap mapboxMap1;
    private MapView mapView;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;
    private LocationChangeListeningActivityLocationCallback callback =
            new LocationChangeListeningActivityLocationCallback(this);
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ref = db.collection("hotels");

    String minPrice ;
    String maxPrice ;
    HotelDto hotelDto;

    String locationLong;
    String locationLat;
    private Marker destinationMarker;
    String DtominPrice = "";
    String DtomaxPrice = "";
    String setlongitude= "";
    String setlatitude = "";
    String name= "";
    String lDescription ="";
    String rating = "";
    String openingTime ="";
    String closingTime ="";
    String image ="";
    LatLng latLng = new LatLng();
    ArrayList<Double> latArray = new ArrayList<Double>();
    ArrayList<Double> lngArray = new ArrayList<Double>();
    ArrayList<String> nameArray = new ArrayList<String>();
    ArrayList<String> descriptionArray = new ArrayList<String>();
    ArrayList<String> ratingArray = new ArrayList<String>();
    ArrayList<String> openingTimeArray = new ArrayList<String>();
    ArrayList<String> closingTimeArray = new ArrayList<String>();
    ArrayList<String> MaxPriceArray = new ArrayList<String>();
    ArrayList<String> imageArray = new ArrayList<String>();
    ArrayList<Marker> destinationMarkerarray = new ArrayList<Marker>();
    private Map<Marker, String[]> markerMap = new HashMap<Marker,String[]>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_fragment, container, false);
        mapView = (MapView) view.findViewById(R.id.mapbox);
        Mapbox.getInstance(getActivity(), getString(R.string.mapbox_access_token));
        mapView.onCreate(savedInstanceState);
        minPrice = getArguments().getString("minPrice").trim();
        maxPrice = getArguments().getString("maxPrice").trim();
        final Integer dminprice = Integer.valueOf(minPrice);
        final Integer dmaxprice = Integer.valueOf(maxPrice);

        mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull final MapboxMap mapboxMap) {

                    mapboxMap1 = mapboxMap;

                        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                int ArrayIndex = 0;
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                                    hotelDto = documentSnapshot.toObject(HotelDto.class);

                                    DtominPrice = hotelDto.getMinPrice();
                                    DtomaxPrice = hotelDto.getPriceRange();
                                    setlongitude = hotelDto.getLongitude();
                                    setlatitude = hotelDto.getLatitude();
                                    name = hotelDto.getName();
                                    lDescription = hotelDto.getLargeDescription();
                                    rating = hotelDto.getRating();
                                    openingTime = hotelDto.getOpeningTime();
                                    closingTime = hotelDto.getClosingTime();
                                    image = hotelDto.getImage();

                                    if(!setlatitude.isEmpty() && !setlongitude.isEmpty()) {
                                        if (Integer.parseInt(DtominPrice) >= dminprice && Integer.parseInt(DtomaxPrice) <= dmaxprice) {


                                             Double point1 = Double.valueOf(setlatitude.toString());
                                             Double point2 = Double.valueOf(setlongitude.toString());
                                            if(point1 != null && point2!=null) {

                                                latLng.setLatitude(point1);
                                                latLng.setLongitude(point2);
                                                name = hotelDto.getName();
                                                lDescription = hotelDto.getLargeDescription();
                                                rating = hotelDto.getRating();
                                                openingTime = hotelDto.getOpeningTime();
                                                closingTime = hotelDto.getClosingTime();
                                                DtomaxPrice = hotelDto.getPriceRange();
                                                image = hotelDto.getImage();
                                                nameArray.add(ArrayIndex,name);
                                                descriptionArray.add(ArrayIndex,lDescription);
                                                ratingArray.add(ArrayIndex,rating);
                                                openingTimeArray.add(ArrayIndex,openingTime);
                                                closingTimeArray.add(ArrayIndex,closingTime);
                                                MaxPriceArray.add(ArrayIndex,DtomaxPrice);
                                                imageArray.add(ArrayIndex,image);
                                                latArray.add(ArrayIndex,point1);
                                                lngArray.add(ArrayIndex,point2);
                                                ArrayIndex = ArrayIndex+1;


                                            }

                                        }
                                    }

                                }



                                for (int i = 0; i < latArray.size(); i++) {

                                            LatLng latLng3 = new LatLng();
                                            latLng3.setLatitude(latArray.get(i));
                                            latLng3.setLongitude(lngArray.get(i));
                                            destinationMarker= mapboxMap.addMarker(new MarkerOptions().position(latLng3));
                                            destinationMarkerarray.add(i,destinationMarker);

                                    String[] hotelinfo = new String[]{nameArray.get(i), descriptionArray.get(i), ratingArray.get(i),openingTimeArray.get(i),
                                            closingTimeArray.get(i),MaxPriceArray.get(i),imageArray.get(i), String.valueOf(latArray.get(i)), String.valueOf(lngArray.get(i))};

                                            markerMap.put(destinationMarker,hotelinfo);


                                }

                                for (int i = 0; i < latArray.size(); i++) {
                                    final int finalI = i;
                                    if (destinationMarkerarray.get(i).getPosition().getLatitude() == latArray.get(finalI)) {
                                        System.out.println("markers"+destinationMarkerarray.get(i).getPosition().getLatitude());
                                        mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                                            @Override
                                            public boolean onMarkerClick(@NonNull @NotNull Marker marker) {
                                                String name1 = markerMap.get(marker)[0];
                                                String description1=markerMap.get(marker)[1];
                                                String rating1=markerMap.get(marker)[2];
                                                String openingTime1=markerMap.get(marker)[3];
                                                String closingTime1=markerMap.get(marker)[4];
                                                String maxPrice1=markerMap.get(marker)[5];
                                                String image1=markerMap.get(marker)[6];
                                                String lat1 = markerMap.get(marker)[7];
                                                String lng1 = markerMap.get(marker)[8];
                                                AppCompatActivity activity = (AppCompatActivity) getContext();
                                                Fragment fragment = new DescriptionHotelFragment();
                                                Bundle args = new Bundle();
                                                args.putString("title", name1);
                                                args.putString("ldes", description1);
                                                args.putString("rate", rating1);
                                                args.putString("open", openingTime1);
                                                args.putString("close", closingTime1);
                                                args.putString("range", maxPrice1);
                                                args.putString("img", image1);
                                                args.putString("lat", lat1);
                                                args.putString("long",lng1);
                                                fragment.setArguments(args);
                                                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();


                                                return true;
                                            }
                                        });

                                    }
                                }
                            }

                        });

                    mapboxMap.setStyle(Style.MAPBOX_STREETS,new Style.OnStyleLoaded(){
                        @Override public void onStyleLoaded(@NonNull Style style) {
                            enableLocationComponent(style);

                        }
                    });

                    mapboxMap.setMinZoomPreference(13);


                }
            });



        Button mapbutton = (Button) view.findViewById(R.id.mapbutton);

        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) container.getContext();
                Fragment fragment = new RequirementFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });

        return view;
    }



    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(getActivity())) {

            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap1.getLocationComponent();

            // Set the LocationComponent activation options
            LocationComponentActivationOptions locationComponentActivationOptions =
                    LocationComponentActivationOptions.builder(getActivity(), loadedMapStyle)
                            .useDefaultLocationEngine(false)
                            .build();

            // Activate with the LocationComponentActivationOptions object
            locationComponent.activateLocationComponent(locationComponentActivationOptions);

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

            initLocationEngine();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(getActivity());
        }
    }
    @SuppressLint("MissingPermission")
    private void initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(getActivity());

        LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build();

        locationEngine.requestLocationUpdates(request, callback, getMainLooper());
        locationEngine.getLastLocation(callback);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(getActivity(), R.string.user_location_permission_explanation,
                Toast.LENGTH_LONG).show();
    }
    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap1.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(getActivity(), R.string.user_location_permission_not_granted0, Toast.LENGTH_LONG).show();
        }
    }
    private static class LocationChangeListeningActivityLocationCallback
            implements LocationEngineCallback<LocationEngineResult> {

        private final WeakReference<DashboardcopyFragment> activityWeakReference;

        LocationChangeListeningActivityLocationCallback(DashboardcopyFragment activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        /**
         * The LocationEngineCallback interface's method which fires when the device's location has changed.
         *
         * @param result the LocationEngineResult object which has the last known location within it.
         */
        @Override
        public void onSuccess(LocationEngineResult result) {
            DashboardcopyFragment activity = activityWeakReference.get();

            if (activity != null) {
                Location location = result.getLastLocation();

                if (location == null) {
                    return;
                }

                // Create a Toast which displays the new location's coordinates


                // Pass the new location to the Maps SDK's LocationComponent
                if (activity.mapboxMap1 != null && result.getLastLocation() != null) {
                    activity.mapboxMap1.getLocationComponent().forceLocationUpdate(result.getLastLocation());
                }


            }
        }

        @Override
        public void onFailure(@NonNull Exception exception) {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();


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
    public void onStop() {
        super.onStop();
        mapView.onStop();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Prevent leaks
        if (locationEngine != null) {
            locationEngine.removeLocationUpdates(callback);
        }
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }



}

