package edu.unicauca.rapirecetas

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecetaDao{
    @Query(value = "Select * FROM recetas")
    fun getAll(): LiveData<List<Receta1>>

    @Query( value= "SELECT * FROM recetas WHERE idReceta = :id")
    fun get(id: Int): LiveData<Receta1>

    @Insert
    fun insertAll(vararg  recetas:Receta1): List<Long>

    @Update
    fun update(recetas: Receta1)

    @Delete
    fun delete(receta: Receta1)
}