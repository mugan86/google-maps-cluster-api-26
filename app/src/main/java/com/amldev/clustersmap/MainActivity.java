package com.amldev.clustersmap;

import android.content.Context;
import android.os.Bundle;

import com.amldev.clustersmap.model.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;


public class MainActivity extends BaseGoogleMapsActivity {
    
    private ClusterManager<Place> mClusterManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setupMap(googleMap);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.182316646663544, -2.478768825531006), 10));

        mClusterManager = new ClusterManager<>(this, googleMap);

        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
        googleMap.setOnInfoWindowClickListener(mClusterManager);
        addPersonItems();
        mClusterManager.cluster();
    }

    private void addPersonItems() {
        for (int i = 0; i < 3; i++) {
            mClusterManager.addItem(new Place(43.181706, -2.475803, "Ipurua Footbal Stadium", "https://es.m.wikipedia.org/wiki/Estadio_Municipal_de_Ipurua"));
            mClusterManager.addItem(new Place(43.182316646663544, -2.478768825531006, "Ipurua Sport Centre", "https://es.m.wikipedia.org/wiki/Polideportivo_de_Ipurúa"));
            mClusterManager.addItem(new Place(43.18255818906789, -2.480659782886505, "Eroski Ipurua Supermarket", "https://www.eroski.es/localizador-de-tiendas/hipermercado/gipuzkoa/eibar/eroski-eibar/"));
        }
    }

    private class RenderClusterInfoWindow extends DefaultClusterRenderer<Place> {

        RenderClusterInfoWindow(Context context, GoogleMap map, ClusterManager<Place> clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        protected void onClusterRendered(Cluster<Place> cluster, Marker marker) {
            super.onClusterRendered(cluster, marker);
        }

        @Override
        protected void onBeforeClusterItemRendered(Place item, MarkerOptions markerOptions) {
            markerOptions.title(item.getName());

            super.onBeforeClusterItemRendered(item, markerOptions);
        }
    }
}
