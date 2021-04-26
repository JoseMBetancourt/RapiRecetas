package edu.unicauca.rapirecetas

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recetas")
class Receta1 (
        val nombre:String,
        val Ingredientes: String,
        val pasos: String,
        val Descripcion: String,
        val DescripcionCorta: String,
        val Porciones: String,
        val Imagen: Int,
        @PrimaryKey(autoGenerate = true )
        var idReceta: Int = 0
): Serializable