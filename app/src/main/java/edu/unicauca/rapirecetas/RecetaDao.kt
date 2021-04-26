package edu.unicauca.rapirecetas

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecetaDao {
    @Query(value = "Select * FROM recetas")
    fun getAll(): LiveData<List<EstructuraReceta>>

    @Query(value = "SELECT * FROM recetas WHERE idReceta = :id")
    fun get(id: Int): LiveData<EstructuraReceta>

    @Insert
    fun insertAll(vararg recetas: EstructuraReceta): List<Long>

    @Update
    fun update(recetas: EstructuraReceta)

    @Delete
    fun delete(receta: EstructuraReceta)
}