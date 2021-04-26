package edu.unicauca.rapirecetas

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_recetas.*

class Recetas2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas)

        var listarecetas = emptyList<Receta1>()

        val database = AppDatabase.getDatabase(this)

        database.recetas().getAll().observe(this, {
            listarecetas = it

            val adapter = RecetaAdapter(this, listarecetas)

            listarrecetas.adapter = adapter

        })

        listarrecetas.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Receta::class.java)
            intent.putExtra("id", listarecetas[position].idReceta)
            startActivity(intent)
        }
        floatingActionButton2.setOnClickListener {
            val intent = Intent(this, Addreceta::class.java)
            startActivity(intent)
        }


    }
    fun openMainActivity(view: View) {
        startActivity( Intent(this, MainActivity::class.java))
    }
}