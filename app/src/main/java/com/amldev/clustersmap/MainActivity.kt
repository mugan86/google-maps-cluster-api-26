package com.amldev.clustersmap

import android.content.Context
import android.os.Bundle
import android.widget.Toast

import com.amldev.clustersmap.model.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer




class MainActivity : BaseGoogleMapsActivity() , ClusterManager.OnClusterClickListener<Place>,
ClusterManager.OnClusterInfoWindowClickListener<Place>,
ClusterManager.OnClusterItemClickListener<Place>,
ClusterManager.OnClusterItemInfoWindowClickListener<Place> {
    override fun onClusterItemInfoWindowClick(p0: Place?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClusterClick(p0: Cluster<Place>?): Boolean {
        Toast.makeText(this, "Click", Toast.LENGTH_LONG).show()
        return true
    }

    override fun onClusterInfoWindowClick(p0: Cluster<Place>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClusterItemClick(p0: Place?): Boolean {
        Toast.makeText(this, "${p0!!.lat}, ${p0.lng}", Toast.LENGTH_LONG).show()
        return true
    }

    private var mClusterManager: ClusterManager<Place>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        setupMap(googleMap)
        //Sidney location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-33.869444, 151.208333), 1f))

        mClusterManager = ClusterManager(this, googleMap)

        googleMap.setOnCameraIdleListener(mClusterManager)
        googleMap.setOnMarkerClickListener(mClusterManager)
        googleMap.setOnInfoWindowClickListener(mClusterManager)
        mClusterManager!!.setOnClusterClickListener(this)
        mClusterManager!!.setOnClusterInfoWindowClickListener(this)
        mClusterManager!!.setOnClusterItemClickListener(this)
        mClusterManager!!.setOnClusterItemInfoWindowClickListener(this)
        addPlaceItems()
        mClusterManager!!.cluster()

    }

    private fun addPlaceItems() {
        // mClusterManager!!.addItem(Place(43.181706, -2.475803, "Ipurua Footbal Stadium", "https://es.m.wikipedia.org/wiki/Estadio_Municipal_de_Ipurua"))
        // mClusterManager!!.addItem(Place(43.182316646663544, -2.478768825531006, "Ipurua Sport Centre", "https://es.m.wikipedia.org/wiki/Polideportivo_de_Ipurúa"))
        // mClusterManager!!.addItem(Place(43.18255818906789, -2.480659782886505, "Eroski Ipurua Supermarket", "https://www.eroski.es/localizador-de-tiendas/hipermercado/gipuzkoa/eibar/eroski-eibar/"))
        mClusterManager!!.addItem(Place(-36.828611,  175.790222))
        mClusterManager!!.addItem(Place(-37.750000,  145.116667))
        mClusterManager!!.addItem(Place(-37.759859,  145.128708))
        mClusterManager!!.addItem(Place(-37.765015,  145.133858))
        mClusterManager!!.addItem(Place(-37.770104,  145.143299))
        mClusterManager!!.addItem(Place(-37.773700,  145.145187))
        mClusterManager!!.addItem(Place(-37.774785,  145.137978))
        mClusterManager!!.addItem(Place(-37.819616,  144.968119))
        mClusterManager!!.addItem(Place(-38.330766,  144.695692))
        mClusterManager!!.addItem(Place(-39.927193,  175.053218))
        mClusterManager!!.addItem(Place(-41.330162,  174.865694))
        mClusterManager!!.addItem(Place(-42.734358,  147.439506))
        mClusterManager!!.addItem(Place(-42.734358,  147.501315))
        mClusterManager!!.addItem(Place(-42.735258,  147.438000))
        mClusterManager!!.addItem(Place(-43.999792,  170.463352))
        mClusterManager!!.addItem(Place(-31.563910, 147.154312))
        mClusterManager!!.addItem(Place(-33.718234, 150.363181))
        mClusterManager!!.addItem(Place(-33.727111, 150.371124))
        mClusterManager!!.addItem(Place(-33.848588, 151.209834))
        mClusterManager!!.addItem(Place(-33.851702, 151.216968))
        mClusterManager!!.addItem(Place(-34.671264, 150.863657))
        mClusterManager!!.addItem(Place(-34.671264, 150.863657))
        mClusterManager!!.addItem(Place(-35.304724, 148.662905))
        mClusterManager!!.addItem(Place(-36.817685, 175.699196))
        mClusterManager!!.addItem(Place(-36.828611, 175.790222))



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
