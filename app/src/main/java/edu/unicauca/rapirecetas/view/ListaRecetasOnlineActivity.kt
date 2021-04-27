package edu.unicauca.rapirecetas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import edu.unicauca.rapirecetas.Adaptador
import edu.unicauca.rapirecetas.R
import edu.unicauca.rapirecetas.RecetaOnline
import kotlinx.android.synthetic.main.activity_lista_recetas_online.*
import java.lang.StringBuilder

class ListaRecetasOnlineActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var recetaRecyclerview : RecyclerView
    private lateinit var recetaArrayList : ArrayList<RecetaOnline>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_recetas_online)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Todas las recetas")

        recetaRecyclerview = findViewById(R.id.recyclerViewFirebase)
        recetaRecyclerview.layoutManager = LinearLayoutManager(this)
        recetaRecyclerview.setHasFixedSize(true)
        recetaArrayList = arrayListOf<RecetaOnline>()

        getRecetaData()

    }

    private fun getRecetaData() {

        dbref = FirebaseDatabase.getInstance().reference
        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val receta = userSnapshot.getValue(RecetaOnline::class.java)
                        recetaArrayList.add(receta!!)
                    }
                    recetaRecyclerview.adapter = Adaptador(recetaArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}