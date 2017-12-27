package manhnd.com.mapdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.vn.ezlearn.network.MyApi
import manhnd.com.mapdemo.directions.Directionresult
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var apiService: ApiService

    private var originMarkers: MutableList<Marker>? = ArrayList()
    private var destinationMarkers: MutableList<Marker>? = ArrayList()
    private var polylinePaths: MutableList<Polyline>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        callDirection()
    }

    private fun callDirection() {
        if (originMarkers != null) {
            for (marker in originMarkers!!) {
                marker.remove()
            }
        }

        if (destinationMarkers != null) {
            for (marker in destinationMarkers!!) {
                marker.remove()
            }
        }

        if (polylinePaths != null) {
            for (polyline in polylinePaths!!) {
                polyline.remove()
            }
        }


        apiService = MyApplication.with(this).getApiService()
        val myApi = MyApi(apiService.direction("KeangNam", "O Quan Chuong", getString(R.string.google_maps_key)))
        myApi.call(object : MyApi.RequestCallBack<Directionresult> {
            override fun onSuccess(result: Directionresult?) {
                val routes = result!!.routes
                polylinePaths = ArrayList()
                originMarkers = ArrayList()
                destinationMarkers = ArrayList()

                if (routes != null) {
                    for (route in routes) {
                        for (j in 0 until route?.legs!!.size) {
                            val startLocation = LatLng(route.legs[j]?.startLocation?.lat!!, route.legs[j]?.startLocation?.lng!!)
                            val endLocation = LatLng(route.legs[j]?.endLocation?.lat!!, route.legs[j]?.endLocation?.lng!!)
                            val startAddress = route.legs[j]?.startAddress
                            val endAddress = route.legs[j]?.endAddress
                            val points = decodePolyLine(route.overviewPolyline?.points!!)
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 16f))
//                        (findViewById<View>(R.id.tvDuration) as TextView).setText(route.duration.text)
//                        (findViewById<View>(R.id.tvDistance) as TextView).setText(route.distance.text)

                            originMarkers!!.add(mMap.addMarker(MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                                    .title(startAddress)
                                    .position(startLocation)))
                            destinationMarkers!!.add(mMap.addMarker(MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                                    .title(endAddress)
                                    .position(endLocation)))

                            val polylineOptions = PolylineOptions().geodesic(true)
                                    .color(Color.parseColor("#3887be")).width(20f)

                            for (i in 0 until points.size)
                                polylineOptions.add(points[i])

                            polylinePaths!!.add(mMap.addPolyline(polylineOptions))
                        }

                    }
                }
            }

            override fun onError(e: Throwable) {

            }
        })
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13f))
    }

    private fun decodePolyLine(poly: String): List<LatLng> {
        val len = poly.length
        var index = 0
        val decoded = ArrayList<LatLng>()
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = poly[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = poly[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            decoded.add(LatLng(
                    lat / 100000.0, lng / 100000.0
            ))
        }

        return decoded
    }
}
