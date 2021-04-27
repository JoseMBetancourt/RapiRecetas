package edu.unicauca.rapirecetas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import edu.unicauca.rapirecetas.AppDatabase
import edu.unicauca.rapirecetas.EstructuraReceta
import edu.unicauca.rapirecetas.R
import edu.unicauca.rapirecetas.RecetaAdapter
import kotlinx.android.synthetic.main.activity_mis_recetas.*

class MisRecetasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_recetas)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Mis Recetas")

        var listarecetas = emptyList<EstructuraReceta>()

        val database = AppDatabase.getDatabase(this)

        database.recetas().getAll().observe(this, {
            listarecetas = it

            val adapter = RecetaAdapter(this, listarecetas)

            listarrecetas.adapter = adapter

        })

        listarrecetas.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, RecetaActivity::class.java)
            intent.putExtra("id", listarecetas[position].idReceta)
            startActivity(intent)
        }
        floatingActionButtonAdd.setOnClickListener {
            val intent = Intent(this, AddRecetaActivity::class.java)
            startActivity(intent)
        }


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