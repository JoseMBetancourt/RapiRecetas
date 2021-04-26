package edu.unicauca.rapirecetas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_receta.view.*


class RecetaAdapter(private val mContext: Context, private val listarecetas:List<EstructuraReceta>) :ArrayAdapter<EstructuraReceta>(mContext, 0 , listarecetas) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout= LayoutInflater.from(mContext).inflate(R.layout.item_receta, parent, false)

        val receta = listarecetas[position]

        layout.NombreReceta.text = receta.nombre
        layout.pDescripcion.text = receta.DescripcionCorta

        val imageUri = ImageControler.getImageUri(mContext,receta.idReceta.toLong())

        layout.imageReceta.setImageURI(imageUri)
        return layout
    }
}