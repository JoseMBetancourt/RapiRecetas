package edu.unicauca.rapirecetas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_receta.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Receta : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private lateinit var receta: Receta1
    private lateinit var  recetaLiveData: LiveData<Receta1>
    private val edit_activity =49
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receta)

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
        val intent = Intent(this, Addreceta::class.java)
        intent.putExtra("receta", receta)
        startActivityForResult(intent,edit_activity)

    }

    fun DeleteReceta(view: View){
        recetaLiveData.removeObservers(this)

        CoroutineScope(Dispatchers.IO).launch {
            database.recetas().delete(receta)
            ImageControler.deleteImage(this@Receta, receta.idReceta.toLong())
            this@Receta.finish()
        }
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
}