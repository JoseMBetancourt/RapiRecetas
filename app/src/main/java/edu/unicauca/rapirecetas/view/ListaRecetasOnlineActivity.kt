package edu.unicauca.rapirecetas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.unicauca.rapirecetas.R
import kotlinx.android.synthetic.main.activity_lista_recetas_online.*
import java.lang.StringBuilder

class ListaRecetasOnlineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_recetas_online)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Todas las recetas")

        // Descarga desde FireBase

        var database = FirebaseDatabase.getInstance().reference

        var getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for (i in snapshot.children) {
                    var nombre = i.child("nombre").getValue()
                    var desC = i.child("desC").getValue()
                    var desL = i.child("desL").getValue()
                    var porciones = i.child("porciones").getValue()
                    var ingredientes = i.child("ingredientes").getValue()
                    var pasos = i.child("pasos").getValue()

                    sb.append("${i.key} $nombre $desC $desL $porciones $ingredientes $pasos \n")

                }
                textViewFireBase.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)

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