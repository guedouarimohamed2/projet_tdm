package com.a0.projet1.master.projet

import android.app.FragmentManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.content.LocalBroadcastManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.a0.projet1.master.projet.Model.Annonce
import com.a0.projet1.master.projet.Model.Annonces
import com.a0.projet1.master.projet.adapter.AnnonceListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_main2.*

class Main2Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////
private val showDetail = object : BroadcastReceiver(){
    override fun onReceive(p0: Context?, intent: Intent?) {
        if (intent!!.action!!.toString() == Annonces.KEY_ENABLE_HOME)
        {
   //         supportActionBar!!.setDisplayHomeAsUpEnabled(true)
   //         supportActionBar!!.setDisplayShowHomeEnabled(true)
            //replace fragment
            val detailFragment = AnnonceDetaille.getInstance()
            val num = intent.getStringExtra("nom")
            val bundle = Bundle()
            bundle.putString("nom",num)
            detailFragment.arguments = bundle

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment,detailFragment)
            fragmentTransaction.addToBackStack("detail")
            fragmentTransaction.commit()
        }
    }
}
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            loadFrag2(AjouterForm())
        }
        //////////////////////////////////////////////////////////////
        toolbar.setTitle("ANNONCES")
        setSupportActionBar(toolbar)
        loadFrag1(ll!!)
        Annonces.initial()
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetail, IntentFilter(Annonces.KEY_ENABLE_HOME))

        ////////////////////////////////////////////////////////

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
    var ll:ListAnnonce? = ListAnnonce()
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                loadFrag1(ll!!)
            }
            R.id.nav_gallery -> {
                loadFrag2(AjouterForm())
            }
            R.id.tri_nom -> {
                var sortedList = Annonces.ans.sortedWith(compareBy({ it.nom }))
                Annonces.ans.clear()
                for (obj in sortedList) {
                    Annonces.ans.add(obj)
                }
                ll!!.adapter = AnnonceListAdapter(ll!!.activity!!,Annonces.ans)
                ll!!.recycler_view.adapter = ll!!.adapter
            }
            R.id.tri_type -> {
                var sortedList = Annonces.ans.sortedWith(compareBy({ it.type }))
                Annonces.ans.clear()
                for (obj in sortedList) {
                    Annonces.ans.add(obj)
                }
                ll!!.adapter = AnnonceListAdapter(ll!!.activity!!,Annonces.ans)
                ll!!.recycler_view.adapter = ll!!.adapter
            }
            R.id.tri_wilaya -> {
                var sortedList = Annonces.ans.sortedWith(compareBy({ it.wilaya }))
                Annonces.ans.clear()
                for (obj in sortedList) {
                    Annonces.ans.add(obj)
                }
                ll!!.adapter = AnnonceListAdapter(ll!!.activity!!,Annonces.ans)
                ll!!.recycler_view.adapter = ll!!.adapter
            }
            R.id.tri_date_depot -> {
                var sortedList = Annonces.ans.sortedByDescending {it.date_depot}
                Annonces.ans.clear()
                for (obj in sortedList) {
                    Annonces.ans.add(obj)
                }
                ll!!.adapter = AnnonceListAdapter(ll!!.activity!!,Annonces.ans)
                ll!!.recycler_view.adapter = ll!!.adapter
            }
            R.id.rech_nom ->{
                Annonces.type_recherche=1
                ll!!.last_suggest.clear()
                for (a in Annonces.ans)
                    ll!!.last_suggest.add(a.nom!!)

                ll!!.search_bar.visibility = View.VISIBLE
                ll!!.search_bar.lastSuggestions = ll!!.last_suggest
                ll!!.search_bar.setHint("Entrer le nom")
            }
            R.id.rech_type ->{
                Annonces.type_recherche=2
                ll!!.last_suggest.clear()
                    for (a in Annonces.ans)
                        ll!!.last_suggest.add(a.type!!)

                ll!!.search_bar.visibility = View.VISIBLE
                ll!!.search_bar.lastSuggestions = ll!!.last_suggest
                ll!!.search_bar.setHint("Entrer le type")
            }
            R.id.rech_wilaya ->{
                Annonces.type_recherche=3
                ll!!.last_suggest.clear()
                for (a in Annonces.ans)
                    ll!!.last_suggest.add(a.wilaya!!)

                ll!!.search_bar.visibility = View.VISIBLE
                ll!!.search_bar.lastSuggestions = ll!!.last_suggest
                ll!!.search_bar.setHint("Entrer la wilaya")
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun show_list_annonce(view : View){
        loadFrag1(ListAnnonce())
    }

    fun show_ajouter_form(view : View){
        loadFrag2(AjouterForm())
    }

    private fun loadFrag1(f1:ListAnnonce){

        val ft = manager.beginTransaction()
        ft.replace(R.id.fragment,f1)


        ft.addToBackStack(null)
        ft.commit()
    }

    fun show_images(view: View){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,ViewImages())
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun loadFrag2(f2:AjouterForm){

        val ft = manager.beginTransaction()
        ft.replace(R.id.fragment,f2)
        ft.addToBackStack(null)
        ft.commit()
    }
    fun ajouter(view: View){
        val nom: EditText =findViewById(R.id.input_nom)
        val type: EditText =findViewById(R.id.input_type)
        val wilaya: EditText =findViewById(R.id.input_wilaya)
        val description : EditText = findViewById(R.id.input_description)
        val telephone : EditText = findViewById(R.id.input_tel)
        val email : EditText = findViewById(R.id.input_email)

        val t =  System.currentTimeMillis();
        Log.i("TAG", "SERIAL: " + t);
        var a1 = Annonce(nom.text.toString(),type.text.toString(),wilaya.text.toString(),description.text.toString(),telephone.text.toString(),email.text.toString(),t)
        Annonces.ans.add(a1)
        loadFrag1(ll!!)
        nom.setText("")
        type.setText("")
        wilaya.setText("")

    }

 /*   override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId)
        {
            android.R.id.home -> {
                //    toolbar.title = "POKEMON LIST"

                //Clear all fragment in stack with name 'detail'
                supportFragmentManager.popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE)

                supportActionBar!!.setDisplayShowHomeEnabled(false)
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)

            }
        }
        return true
    }*/
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
