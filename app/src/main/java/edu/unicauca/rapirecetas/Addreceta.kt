package edu.unicauca.rapirecetas;

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity;
import kotlinx.android.synthetic.main.activity_addreceta.*
import android.os.Bundle;
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Addreceta : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private val Select_Activity = 50
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addreceta)

        var idReceta: Int? = null

        if(intent.hasExtra("receta")){
            val receta = intent.extras?.getSerializable("receta") as Receta1

            editTextNombre.setText(receta.nombre)
            editTextDescripcion.setText(receta.Descripcion)
            editTextDescripcionCorta.setText(receta.DescripcionCorta)
            editTextIngredientes.setText(receta.Ingredientes)
            editTextPasos.setText(receta.pasos)
            editTextPorciones.setText(receta.Porciones)
            idReceta = receta.idReceta

            val imagenUri = ImageControler.getImageUri(this, idReceta.toLong())
            imageSelect.setImageURI(imagenUri)
        }

        val database = AppDatabase.getDatabase(this )

        save_btn.setOnClickListener {

            if(editTextNombre.text.isEmpty() || editTextPorciones.text.isEmpty() ||editTextDescripcionCorta.text.isEmpty() || editTextDescripcion.text.isEmpty() || editTextIngredientes.text.isEmpty() || editTextPasos.text.isEmpty()){
                Toast.makeText(this, "llene todos los campos", Toast.LENGTH_SHORT).show()
            }
            else {
                val nombre = editTextNombre.text.toString()
                val Descripcion = editTextDescripcion.text.toString()
                val Ingredientes = editTextIngredientes.text.toString()
                val Pasos = editTextPasos.text.toString()
                val DescripcionCorta = editTextDescripcionCorta.text.toString()
                val Porciones = editTextPorciones.text.toString()

                val receta = Receta1(nombre,Ingredientes,Pasos,Descripcion,DescripcionCorta,Porciones,R.drawable.desa1)

                if (idReceta != null){
                    CoroutineScope(Dispatchers.IO).launch {
                        receta.idReceta = idReceta

                        database.recetas().update(receta)

                        imageUri?.let {
                            val intent = Intent()
                            intent.data = it
                            setResult(Activity.RESULT_OK, intent)
                            ImageControler.saveImage(this@Addreceta,idReceta.toLong(), it)
                        }

                        this@Addreceta.finish()
                    }
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val id= database.recetas().insertAll(receta)[0]

                        imageUri?.let {
                            ImageControler.saveImage(this@Addreceta,id, it)
                        }

                        this@Addreceta.finish()
                    }
                }
            }
        }


        imageSelect.setOnClickListener {
            ImageControler.selectPhotoFromGallery(this, Select_Activity)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when{
            requestCode == Select_Activity && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data

                imageSelect.setImageURI(imageUri)
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        TODO("Not yet implemented")
    }

    fun openMainActivity(view: View) {
        startActivity( Intent(this, MainActivity::class.java))
    }
}