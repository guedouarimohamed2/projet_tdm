package com.a0.projet1.master.projet

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.a0.projet1.master.projet.Decoration.ItemOffsetDecoration
import com.a0.projet1.master.projet.Model.Annonce
import com.a0.projet1.master.projet.Model.Annonces
import com.a0.projet1.master.projet.adapter.AnnonceListAdapter
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.fragment_list_annonce.*
import kotlinx.android.synthetic.main.fragment_list_annonce.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListAnnonce.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListAnnonce.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListAnnonce : Fragment() {

    internal lateinit var recycler_view:RecyclerView
    internal  lateinit var adapter:AnnonceListAdapter

    internal  lateinit var search_adapter:AnnonceListAdapter
    internal  var last_suggest:MutableList<String> = ArrayList()

    internal  lateinit var search_bar: MaterialSearchBar

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       // btn_tri = view!!.findViewById(R.id.btn_tri_titre)

        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_list_annonce, container, false)
        recycler_view = itemView.findViewById(R.id.annonce_recyclerview) as RecyclerView
        recycler_view.setHasFixedSize(true)

        recycler_view.layoutManager = GridLayoutManager(activity,1)
        val itemDecoration = ItemOffsetDecoration(activity!!,R.dimen.spacing)
        recycler_view.addItemDecoration(itemDecoration)

        //Step Search bar
        search_bar = itemView.findViewById(R.id.search_bar) as MaterialSearchBar
        search_bar.setHint("Enter  le nom")
        search_bar.setCardViewElevation(10)

        search_bar.addTextChangeListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val suggest = ArrayList<String>()
                for (search in last_suggest)
                    if(search.toLowerCase().contains(search_bar.text.toLowerCase()))
                        suggest.add(search)
                search_bar.lastSuggestions = suggest
            }
        })
        search_bar.setOnSearchActionListener(object :MaterialSearchBar.OnSearchActionListener{
            override fun onButtonClicked(buttonCode: Int) {

            }

            override fun onSearchStateChanged(enabled: Boolean) {
                if(!enabled)
                    annonce_recyclerview.adapter=adapter
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                startSearch(text.toString())
            }
        })

        adapter = AnnonceListAdapter(activity!!,Annonces.ans)
        recycler_view.adapter = adapter

        last_suggest.clear()
        if(Annonces.type_recherche==1){
            for (a in Annonces.ans)
                last_suggest.add(a.nom!!)
        }

        if(Annonces.type_recherche==2){
            for (a in Annonces.ans)
                last_suggest.add(a.type!!)
        }

        if(Annonces.type_recherche==3){
            for (a in Annonces.ans)
                last_suggest.add(a.wilaya!!)
        }

        search_bar.visibility = View.VISIBLE
        search_bar.lastSuggestions = last_suggest

        return itemView
    }

    private fun startSearch(text: String) {
        if(Annonces.ans.size > 0){
            val result =ArrayList<Annonce>()
            for (a in Annonces.ans)
            {
                if(Annonces.type_recherche==1){
                    if(a.nom!!.toLowerCase().contains(text.toLowerCase()))
                        result.add(a)
                }

                if(Annonces.type_recherche==2){
                    if(a.type!!.toLowerCase().contains(text.toLowerCase()))
                        result.add(a)
                }

                if(Annonces.type_recherche==3){
                    if(a.wilaya!!.toLowerCase().contains(text.toLowerCase()))
                        result.add(a)
                }
            }

            search_adapter = AnnonceListAdapter(activity!!,result)
            annonce_recyclerview.adapter = search_adapter
        }

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
