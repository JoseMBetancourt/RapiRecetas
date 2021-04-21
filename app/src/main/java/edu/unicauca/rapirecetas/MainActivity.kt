package edu.unicauca.rapirecetas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun corazonToggle(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun corazonToggle2(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun corazonToggle3(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun corazonToggle4(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun corazonToggle5(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun corazonToggle6(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}

    fun openAccount(view: View) {
        startActivity(Intent(this, Cuenta::class.java))
    }
    fun openMenu(view: View) {
        startActivity( Intent(this, Categorias::class.java))
    }
}