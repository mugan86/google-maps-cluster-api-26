package com.amldev.clustersmap

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.amldev.clustersmap.adapter.PopupAdapter
import com.amldev.clustersmap.model.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager


class MainActivity : BaseGoogleMapsActivity()  {


    private var mClusterManager: ClusterManager<Place>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        setupMap(googleMap)
        //Sidney location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-33.869444, 151.208333), 1f))

        mClusterManager = ClusterManager(this, googleMap)

        val renderer = RenderClusterInfoWindow(this, googleMap, this.mClusterManager!!)

        mClusterManager!!.setRenderer(renderer)

        googleMap.setOnCameraIdleListener(mClusterManager)
        googleMap.setOnMarkerClickListener(mClusterManager)
        googleMap.setOnInfoWindowClickListener(mClusterManager)

        mClusterManager!!.setOnClusterClickListener(
            ClusterManager.OnClusterClickListener<Place> {
                Toast.makeText(this@MainActivity, "Cluster click", Toast.LENGTH_SHORT).show()
                false
        })

        mClusterManager!!.setOnClusterItemClickListener(
                ClusterManager.OnClusterItemClickListener<Place> {
                    Toast.makeText(this@MainActivity, "Cluster item click", Toast.LENGTH_SHORT).show()

                    // if true, click handling stops here and do not show info view, do not move camera
                    // you can avoid this by calling:
                    // renderer.getMarker(clusterItem).showInfoWindow();

                    false
                })

        mClusterManager!!.getMarkerCollection()
                .setOnInfoWindowAdapter(PopupAdapter(LayoutInflater.from(this)))

        mClusterManager!!.setOnClusterItemInfoWindowClickListener(
                ClusterManager.OnClusterItemInfoWindowClickListener<Place> { stringClusterItem ->
                    Toast.makeText(this@MainActivity, "Clicked info window: " + stringClusterItem.lat + ", "  + stringClusterItem.lng,
                            Toast.LENGTH_SHORT).show()
                })

        googleMap.setOnInfoWindowClickListener(mClusterManager)
        googleMap.setInfoWindowAdapter(mClusterManager!!.getMarkerManager());

        addPlaceItems()
        mClusterManager!!.cluster()

    }



    private fun addPlaceItems() {
        for (i in 0..20) {
            val latLng = LatLng((-34 + i).toDouble(), (151 + i).toDouble())
            mClusterManager!!.addItem(Place( latLng.latitude, latLng.longitude, "Marker #" + (i + 1)))
        }

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
}
