package edu.unicauca.rapirecetas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(private val recetaList : ArrayList<RecetaOnline>) : RecyclerView.Adapter<Adaptador.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_receta,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = recetaList[position]

        holder.nombre.text = currentitem.nombre
        holder.desC.text = currentitem.desC
        /*
        holder.desL.text = currentitem.desL
        holder.ingredientes.text = currentitem.ingredientes
        holder.pasos.text = currentitem.pasos
        holder.porciones.text = currentitem.porciones.toString()
        */
    }

    override fun getItemCount(): Int {

        return recetaList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val nombre : TextView = itemView.findViewById(R.id.NombreReceta)
        val desC : TextView = itemView.findViewById(R.id.pDescripcion)
        /*
        val desL : TextView = itemView.findViewById(R.id.desL_fb)
        val ingredientes : TextView = itemView.findViewById(R.id.ingredientes_fb)
        val pasos : TextView = itemView.findViewById(R.id.pasos_fb)
        val porciones : TextView = itemView.findViewById(R.id.porciones_fb)
        */
    }

}