package com.amldev.clustersmap

import android.content.Context
import com.amldev.clustersmap.model.Place
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

/**
 * Created by anartzmugika on 22/12/17.
 */
class RenderClusterInfoWindow internal constructor(context: Context, map: GoogleMap, clusterManager: ClusterManager<Place>) : DefaultClusterRenderer<Place>(context, map, clusterManager) {

    override fun onClusterRendered(cluster: Cluster<Place>?, marker: Marker?) {
        super.onClusterRendered(cluster, marker)
    }

    override fun onBeforeClusterItemRendered(item: Place?, markerOptions: MarkerOptions?) {
        markerOptions!!.title("${item!!.lat}, ${item.lng}")

        super.onBeforeClusterItemRendered(item, markerOptions)
        val markerDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);

        markerOptions.icon(markerDescriptor).snippet(item.title);
    }
}