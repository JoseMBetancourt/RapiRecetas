package edu.unicauca.rapirecetas.view

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import edu.unicauca.rapirecetas.AppDatabase
import edu.unicauca.rapirecetas.EstructuraReceta
import edu.unicauca.rapirecetas.ImageControler
import edu.unicauca.rapirecetas.R
import kotlinx.android.synthetic.main.activity_receta.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecetaActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private lateinit var receta: EstructuraReceta
    private lateinit var  recetaLiveData: LiveData<EstructuraReceta>
    private val edit_activity =49
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receta)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Gestionar Receta")

        database = AppDatabase.getDatabase(this)

        val idReceta = intent.getIntExtra("id", 0)

        val imageUri = ImageControler.getImageUri(this,idReceta.toLong())
        imagenreceta.setImageURI(imageUri)

        recetaLiveData = database.recetas().get(idReceta)

        recetaLiveData.observe(this, Observer {
            receta = it

            nombreReceta.text = receta.nombre
            Descripcion.text = receta.Descripcion
            DescripcionCorta.text = receta.DescripcionCorta
            ingrediente.text = receta.Ingredientes
            Pasos.text = receta.pasos
            Valor.text = receta.Porciones

        })



    }


    fun openEdit(view: View) {
        val intent = Intent(this, AddRecetaActivity::class.java)
        intent.putExtra("receta", receta)
        startActivityForResult(intent,edit_activity)

    }

    fun DeleteReceta(view: View){
        MaterialAlertDialogBuilder(this)
            .setTitle("Eliminar una receta")
            .setMessage("¿Está seguro que desea eliminar la receta? Está acción es irreversible")
            .setNegativeButton("No"){dialog, which ->
                Toast.makeText(this, "Acción declinada", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Si"){dialog, which ->
                recetaLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.recetas().delete(receta)
                    ImageControler.deleteImage(this@RecetaActivity, receta.idReceta.toLong())
                    this@RecetaActivity.finish()
                }

            }
            .show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when{
            requestCode == edit_activity && resultCode == Activity.RESULT_OK ->{
                imagenreceta.setImageURI(data!!.data)
            }
        }
    }

    fun corazonToggle(view: View) {Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT)

    }
    fun openMainActivity(view: View) {
        startActivity( Intent(this, MainActivity::class.java))
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