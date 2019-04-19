package com.a0.projet1.master.projet


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.a0.projet1.master.projet.Model.Annonce
import com.a0.projet1.master.projet.Model.Annonces
import com.a0.projet1.master.projet.adapter.AnnonceListAdapter
import com.a0.projet1.master.projet.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.fragment_annonce_detaille.view.*
import kotlinx.android.synthetic.main.fragment_list_annonce.view.*
import android.app.FragmentManager
import android.support.v4.view.ViewPager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var itemView = inflater.inflate(R.layout.fragment_annonce_detaille, container, false)
   /*     val ft = supportFragmentManager.beginTransaction()
        itemView.btn_show_images.setOnClickListener{view ->

            ft.replace(R.id.fragment,f2)
            ft.addToBackStack(null)
            ft.commit()
            Toast.makeText(context,"tri ok ", Toast.LENGTH_SHORT).show()
        } */
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
        return itemView
    }

    private fun setDetailAnnonce(annonce: Annonce?) {
        annonce_nom.text ="Nom : "+ annonce!!.nom
        annonce_type.text = "Type : "+ annonce!!.type
        annonce_wilaya.text ="wialya : "+ annonce!!.wilaya
        annonce_description.text = annonce!!.description
        annonce_telephone.text ="telephone : "+ annonce!!.telephone
        annonce_email.text ="email : "+ annonce!!.email

    }
}
