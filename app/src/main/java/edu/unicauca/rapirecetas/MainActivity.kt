package edu.unicauca.rapirecetas


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawarLayout = findViewById<DrawerLayout>(R.id.drawarLayout)
        val imgMenu = findViewById<ImageView>(R.id.imgMenu)

        val navView = findViewById<NavigationView>(R.id.navDawar)
        navView.itemIconTintList = null
        imgMenu.setOnClickListener {
            drawarLayout.openDrawer(GravityCompat.START)
        }
        val navController = Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupWithNavController(navView,navController)

        val textTitle = findViewById<TextView>(R.id.title)
        navController
            .addOnDestinationChangedListener { controller, destination, arguments ->
                textTitle.text = destination.label
            }


    }

    fun corazonToggle(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun corazonToggle2(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun corazonToggle3(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun corazonToggle4(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun corazonToggle5(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}
    fun corazonToggle6(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)}



}