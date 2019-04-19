package com.a0.projet1.master.projet.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.a0.projet1.master.projet.Interface.IItemClickListener
import com.a0.projet1.master.projet.Model.Annonce
import com.a0.projet1.master.projet.Model.Annonces
import com.a0.projet1.master.projet.R
import kotlinx.android.synthetic.main.annonce_item.view.*

class AnnonceListAdapter(internal var context: Context,
                         internal var annonceList:MutableList<Annonce>): RecyclerView.Adapter<AnnonceListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.annonce_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return annonceList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text_nom.text= annonceList[position].nom
        holder.text_type.text=annonceList[position].type
        holder.text_wilaya.text=annonceList[position].wilaya


        holder.setItemClickListener(object :IItemClickListener{

            override fun onclick(view: View, position: Int) {
                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(Intent(Annonces.KEY_ENABLE_HOME).putExtra("nom",annonceList[position].nom))
            }
        })
    }


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        internal var text_wilaya: TextView
        internal var text_type: TextView
        internal var text_nom: TextView
        internal var itemClickListener: IItemClickListener?=null

        fun setItemClickListener(itemClickListener:IItemClickListener)
        {
            this.itemClickListener = itemClickListener
        }

        init {
            text_nom = itemView.findViewById(R.id.nom) as TextView
            text_type = itemView.findViewById(R.id.type) as TextView
            text_wilaya = itemView.findViewById(R.id.wilaya) as TextView
            itemView.setOnClickListener{view -> itemClickListener!!.onclick(view,adapterPosition)}
        }
    }
}