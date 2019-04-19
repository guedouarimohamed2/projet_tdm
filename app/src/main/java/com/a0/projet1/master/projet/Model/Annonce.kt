package com.a0.projet1.master.projet.Model

import java.util.*

class Annonce (var nom:String?=null,
               var type:String?=null,
               var wilaya:String?=null,
               var description:String?=null,
               var telephone:String?=null,
               var email:String?=null,
               var date_depot: Long? =null,
               var images: MutableList<Int>? = ArrayList()
){
    // var date:String?=null
}