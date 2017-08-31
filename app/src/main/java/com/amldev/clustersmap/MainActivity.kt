package com.amldev.clustersmap

import android.content.Context
import android.os.Bundle

import com.amldev.clustersmap.model.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer


class MainActivity : BaseGoogleMapsActivity() {

    private var mClusterManager: ClusterManager<Place>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        setupMap(googleMap)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(43.182316646663544, -2.478768825531006), 10f))

        mClusterManager = ClusterManager(this, googleMap)

        googleMap.setOnCameraIdleListener(mClusterManager)
        googleMap.setOnMarkerClickListener(mClusterManager)
        googleMap.setOnInfoWindowClickListener(mClusterManager)
        addPersonItems()
        mClusterManager!!.cluster()
    }

    private fun addPersonItems() {
        for (i in 0..2) {
            mClusterManager!!.addItem(Place(43.181706, -2.475803, "Ipurua Footbal Stadium", "https://es.m.wikipedia.org/wiki/Estadio_Municipal_de_Ipurua"))
            mClusterManager!!.addItem(Place(43.182316646663544, -2.478768825531006, "Ipurua Sport Centre", "https://es.m.wikipedia.org/wiki/Polideportivo_de_Ipurúa"))
            mClusterManager!!.addItem(Place(43.18255818906789, -2.480659782886505, "Eroski Ipurua Supermarket", "https://www.eroski.es/localizador-de-tiendas/hipermercado/gipuzkoa/eibar/eroski-eibar/"))
        }
    }

    private inner class RenderClusterInfoWindow internal constructor(context: Context, map: GoogleMap, clusterManager: ClusterManager<Place>) : DefaultClusterRenderer<Place>(context, map, clusterManager) {

        override fun onClusterRendered(cluster: Cluster<Place>?, marker: Marker?) {
            super.onClusterRendered(cluster, marker)
        }

        override fun onBeforeClusterItemRendered(item: Place?, markerOptions: MarkerOptions?) {
            markerOptions!!.title(item!!.name)

            super.onBeforeClusterItemRendered(item, markerOptions)
        }
    }
}
