package com.a0.projet1.master.projet.Model

import com.a0.projet1.master.projet.R
import java.util.*

object Annonces {
    var tests = ArrayList<Annonce>()
    var ans:MutableList<Annonce> = ArrayList()
    val t = System.currentTimeMillis()
    //Log.i("TAG", "SERIAL: " + Build.SERIAL);
    var a1 = Annonce("yahyaoui","f2","alger","Après réflexion, notre choix s’est porté sur l’améliorator l’améliorator l’améliorator l’améliorato",
            "0589632574","yahyaoui@gmail.com",t)
    var a2 = Annonce("mestoui","f3","alger","Système interactif, Cognition, Ergonomie, Application pour apprentissage, Refonte, Répétition espacée ",
            "0589632574","mestoui@gmail.com",t)
    var a3 = Annonce("guedouari","f5","alger","même la difficulté de navigation avant et lors des révisions, font qu’elle n’est pas assez bien exploitée",
            "0662859874","guedouari@gmail.com",t)
    var a4 = Annonce("batata","f5","alger","Après réflexion, notre choix s’est porté sur l’amélioration d’une application mobile qui touche une large ",
            "0662859874","batata@gmail.com",t)
    var a5 = Annonce("saadoun","f2","oran","Après réflexion, notre choix s’est porté sur l’amélioration d’une application mobile qui touche une large ",
            "0589632574","saadoun@gmail.com",t)
    var a6 = Annonce("mesaoudi","f3","oran","même la difficulté de navigation avant et lors des révisions, font qu’elle n’est pas assez bien exploitée",
            "0772589681","mesaoudi@gmail.com",t)
    var a7 = Annonce("khiroune","f5","bejaya","Système interactif, Cognition, Ergonomie, Application pour apprentissage, Refonte, Répétition espacée ",
            "0589632574","khiroune@gmail.com",t)
    var a8 = Annonce("mahmoudi","f5","setif","Après réflexion, notre choix s’est porté sur l’amélioration d’une application mobile qui touche une large ",
            "0662859874","mahmoudi@gmail.com",t)
    var a9 = Annonce("mohammadi","studio","setif","Après réflexion, notre choix s’est porté sur l’amélioration d’une application mobile qui touche une large ",
            "0772589681","mohammadi@gmail.com",t)
    var a10 = Annonce("benyoucef","f3","alger","Après réflexion, notre choix s’est porté sur l’amélioration d’une application mobile qui touche une large ",
            "0589632574","benyoucef@gmail.com",t)
    var a11= Annonce("benomar","f4","alger","Après réflexion, notre choix s’est porté sur l’amélioration d’une application mobile qui touche une large ",
            "0589632574","benomar@gmail.com",t)
    var a12 = Annonce("aitaljat","f4","jijel","même la difficulté de navigation avant et lors des révisions, font qu’elle n’est pas assez bien exploitée",
            "0589632574","aitaljat@gmail.com",t)
    var a13 = Annonce("hirrech","f4","bejaya","Après réflexion, notre choix s’est porté sur l’amélioration d’une application mobile qui touche une large ",
            "0772589681","hirrech@gmail.com",t)
    var a14 = Annonce("boulala","f4","oran","même la difficulté de navigation avant et lors des révisions, font qu’elle n’est pas assez bien exploitée",
            "0772589681","boulala@gmail.com",t)
    var a15 = Annonce("zemmiche","f5","alger","Système interactif, Cognition, Ergonomie, Application pour apprentissage, Refonte, Répétition espacée ",
            "0589632574","zemmiche@gmail.com",t)
    val KEY_ENABLE_HOME = "position"
    var type_recherche = 1
    fun initial(){
 /*     tests.add(a1);tests.add(a2);tests.add(a3);tests.add(a4);tests.add(a5);tests.add(a6);tests.add(a7);
        tests.add(a8);tests.add(a9);tests.add(a10);tests.add(a11);tests.add(a12);tests.add(a13);tests.add(a14);tests.add(a15)
*/
        a1.images?.add(R.drawable.balloons.toString().toInt())
        a1.images?.add(R.drawable.aa.toString().toInt())
        a1.images?.add(R.drawable.bb.toString().toInt())
        a1.images?.add(R.drawable.cc.toString().toInt())
        a1.images?.add(R.drawable.dd.toString().toInt())


        a2.images?.add(R.drawable.aa.toString().toInt())
        a2.images?.add(R.drawable.bb.toString().toInt())
        a2.images?.add(R.drawable.cc.toString().toInt())
        ans.add(a1)
        ans.add(a2)
        ans.add(a3)
        ans.add(a4)
        ans.add(a5)
        ans.add(a6)
        ans.add(a7)
        ans.add(a8)
        ans.add(a9)
        ans.add(a10)
        ans.add(a11)
        ans.add(a12)
        ans.add(a13)
        ans.add(a14)
        ans.add(a15)

    }
    fun add_annonce(annonce: Annonce){
        ans.add(annonce)
    }

    fun findAnnonceByTitre(titre: String?): Annonce? {
        for (a in Annonces.ans)
            if (a.nom.equals(titre))
                return a
        return null
    }


}