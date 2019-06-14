package com.a0.projet1.master.projet


import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.a0.projet1.master.projet.Model.Annonce
import com.a0.projet1.master.projet.Model.Annonces
import com.a0.projet1.master.projet.adapter.SectionsPagerAdapter
import android.content.Intent
import android.net.Uri
import android.support.v4.view.ViewPager
import android.widget.Button
import com.google.android.gms.maps.MapView
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory


class AnnonceDetaille : Fragment(), OnMapReadyCallback {

    val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"

    internal  lateinit var annonce_wilaya: TextView
    internal  lateinit var annonce_type: TextView
    internal  lateinit var annonce_nom: TextView
    internal  lateinit var annonce_description: TextView
    internal  lateinit var annonce_telephone: TextView
    internal  lateinit var annonce_email: TextView

    internal  lateinit var v_p: ViewPager

    internal lateinit var mMapView: MapView

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    companion object {
        internal var instance:AnnonceDetaille?=null
        fun getInstance():AnnonceDetaille{
            if (instance == null)
                instance = AnnonceDetaille()

            return instance!!
        }
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var itemView = inflater.inflate(R.layout.fragment_annonce_detaille, container, false)
        val annonce = Annonces.findAnnonceByTitre(arguments!!.getString("nom"))

        annonce_wilaya = itemView.findViewById(R.id.wilaya) as TextView
        annonce_type = itemView.findViewById(R.id.type) as TextView
        annonce_nom = itemView.findViewById(R.id.nom) as TextView
        annonce_description = itemView.findViewById(R.id.description) as TextView
        annonce_telephone = itemView.findViewById(R.id.telephone) as TextView
        annonce_email = itemView.findViewById(R.id.email) as TextView

        v_p = itemView.findViewById(R.id.container)

        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager, annonce!!)
        v_p.adapter = mSectionsPagerAdapter
        setDetailAnnonce(annonce)


        var Appel = itemView.findViewById(R.id.Appel) as Button
        Appel.setOnClickListener {
            val phone = annonce!!.telephone
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }

        mMapView = itemView.findViewById(R.id.map)
        initGoogleMap(savedInstanceState)

        return itemView
    }

    private fun setDetailAnnonce(annonce: Annonce?) {
        annonce_nom.text = "Nom : " + annonce!!.nom
        annonce_type.text = "Type : " + annonce.type
        annonce_wilaya.text = "Wilaya : " + annonce.wilaya
        annonce_description.text = annonce.description
        annonce_telephone.text = "Telephone : " + annonce.telephone
        annonce_email.text = "E-mail : " + annonce.email
    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }

        mMapView.onCreate(mapViewBundle)

        mMapView.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mMapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView.onStop()
    }


    override fun onMapReady(map: GoogleMap) {

        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        val alger = LatLng(36.7525, 3.04197)

        map.addMarker(MarkerOptions().position(alger)
                .title("Marker in Algiers"))

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(alger, 12.0f))


        if (ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

    }

    override fun onPause() {
        mMapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mMapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }


}
