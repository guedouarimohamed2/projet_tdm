package com.a0.projet1.master.projet

import android.app.Activity
import android.app.FragmentManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
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
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.a0.projet1.master.projet.Model.Annonce
import com.a0.projet1.master.projet.Model.Annonces
import com.a0.projet1.master.projet.adapter.AnnonceListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_main2.*
import kotlinx.android.synthetic.main.fragment_ajouter_form.*
import java.util.ArrayList
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
    var images: MutableList<Uri>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            loadFrag2(AjouterForm())
            images!!.clear()
        }

        //////////////////////////////////////////////////////////////
        toolbar.setTitle("Annonces Immobilières")
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
                images!!.clear()
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
                ll!!.search_bar.setHint("Entrer Nom")
            }
            R.id.rech_type ->{
                Annonces.type_recherche=2
                ll!!.last_suggest.clear()
                    for (a in Annonces.ans)
                        ll!!.last_suggest.add(a.type!!)

                ll!!.search_bar.visibility = View.VISIBLE
                ll!!.search_bar.lastSuggestions = ll!!.last_suggest
                ll!!.search_bar.setHint("Entrer Type")
            }
            R.id.rech_wilaya ->{
                Annonces.type_recherche=3
                ll!!.last_suggest.clear()
                for (a in Annonces.ans)
                    ll!!.last_suggest.add(a.wilaya!!)

                ll!!.search_bar.visibility = View.VISIBLE
                ll!!.search_bar.lastSuggestions = ll!!.last_suggest
                ll!!.search_bar.setHint("Entrer Wilaya")
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
        images!!.clear()
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
    private fun pickimage(){
        images!!.clear()
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_PHOTO)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    // something is wrong
                }

                val clipData = data!!.clipData
                if (clipData != null) { // handle multiple photo
                    for (i in 0 until clipData.itemCount) {
                        val uri = clipData.getItemAt(i).uri
                        images!!.add(uri)
                        //   text_uri.text=text_uri.text.toString() + uri.toString()
                    }
                } else { // handle single photo
                    val uri = data?.data
                    images!!.add(data?.data!!)
                    //text_uri.text=text_uri.text.toString() + uri.toString()
                }
            }
        }
    }
    fun choisir_image(view: View){
        pickimage()
    }
    /*
    private fun pickimage(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)//,MediaStore.Images.Media.INTERNAL_CONTENT_URI
        //  intent.type = "image*"
        startActivityForResult(intent,IMAGE_PICK_CODE)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            images!!.add(data?.data!!)

        }
    }*/
    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val REQUEST_PICK_PHOTO = 1
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
        var a1 = Annonce(nom.text.toString(),type.text.toString(),wilaya.text.toString(),description.text.toString(),telephone.text.toString(),email.text.toString(),t,images)
        Annonces.ans.add(a1)
        loadFrag1(ll!!)
        nom.setText("")
        type.setText("")
        wilaya.setText("")

    }

}
