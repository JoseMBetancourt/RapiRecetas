package edu.unicauca.rapirecetas.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import edu.unicauca.rapirecetas.R

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        navView.menu.findItem(R.id.nav_mis_recetas).setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.nav_mis_recetas ->{
                    val intent = Intent(this, MisRecetasActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false

            }
        }
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_favorites, R.id.nav_creditos, R.id.nav_categories
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    fun corazonToggle(view: View) {Toast.makeText(this,"Añadido", Toast.LENGTH_SHORT)}
    fun corazonToggle1(view: View) {Toast.makeText(this,"Añadido", Toast.LENGTH_SHORT)}
    fun corazonToggle2(view: View) {Toast.makeText(this,"Añadido", Toast.LENGTH_SHORT)}
    fun corazonToggle3(view: View) {Toast.makeText(this,"Añadido", Toast.LENGTH_SHORT)}
    fun corazonToggle4(view: View) {Toast.makeText(this,"Añadido", Toast.LENGTH_SHORT)}
    fun corazonToggle5(view: View) {Toast.makeText(this,"Añadido", Toast.LENGTH_SHORT)}
    fun corazonToggle6(view: View) {Toast.makeText(this,"Añadido", Toast.LENGTH_SHORT)}
    fun corazonToggle7(view: View) {Toast.makeText(this,"Añadido", Toast.LENGTH_SHORT)}
    fun corazonToggle8(view: View) {Toast.makeText(this,"Añadido", Toast.LENGTH_SHORT)}

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}