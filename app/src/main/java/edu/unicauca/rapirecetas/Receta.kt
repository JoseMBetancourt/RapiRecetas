package edu.unicauca.rapirecetas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class Receta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receta)
    }

    fun corazonToggle(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun openMenu(view: View) {
        startActivity( Intent(this, MainActivity::class.java))
    }
}