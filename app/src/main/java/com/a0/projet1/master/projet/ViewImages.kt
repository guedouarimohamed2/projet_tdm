package com.a0.projet1.master.projet


import android.content.Context
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a0.projet1.master.projet.Model.Annonce
import kotlinx.android.synthetic.main.fragment_view_images.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 *
 */

class ViewImages : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ARG_SECTION_NUMBER = "section_number"
        private lateinit var a:Annonce
        private var images: MutableList<Int>? = ArrayList()
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int,annonce : Annonce): ViewImages {
            val fragment = ViewImages()
            val args = Bundle()
            a=annonce
          //  args.putIntegerArrayList("tab", annonce.images as java.util.ArrayList<Int>?)
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_view_images, container, false)

        var i:Int=1
        if(!a.images!!.isEmpty()){
            for(key in a.images!!)
            {
                if(arguments!!.getInt(ARG_SECTION_NUMBER)==i){
                    rootView.imafe_iv.setImageResource(a.images!![i-1])
                }
                i++
            }
        }else{

        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

}
