package com.a0.projet1.master.projet



import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

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

/**
 * A simple [Fragment] subclass.
 *
 */
class AnnonceDetaille : Fragment() {

    internal  lateinit var annonce_wilaya: TextView
    internal  lateinit var annonce_type: TextView
    internal  lateinit var annonce_nom: TextView
    internal  lateinit var annonce_description: TextView
    internal  lateinit var annonce_telephone: TextView
    internal  lateinit var annonce_email: TextView

    internal  lateinit var v_p: ViewPager

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
        // Set up the ViewPager with the sections adapter.
        //  fragment.
        setDetailAnnonce(annonce)

        var Appel = itemView.findViewById(R.id.Appel) as Button
        Appel.setOnClickListener {
            val phone = annonce!!.telephone
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }

        return itemView
    }

    private fun setDetailAnnonce(annonce: Annonce?) {
        annonce_nom.text ="Nom : "+ annonce!!.nom
        annonce_type.text = "Type : "+ annonce!!.type
        annonce_wilaya.text ="Wilaya : "+ annonce!!.wilaya
        annonce_description.text = annonce!!.description
        annonce_telephone.text ="Telephone : "+ annonce!!.telephone
        annonce_email.text ="E-mail : "+ annonce!!.email
    }
}
