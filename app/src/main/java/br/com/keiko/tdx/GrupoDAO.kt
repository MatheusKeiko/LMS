package br.com.keiko.tdx

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GrupoDAO {
    @Query("SELECT * FROM grupo where id = :id")
    fun getById(id: Long) : Grupo?

    @Query("SELECT * FROM grupo")
    fun findAll(): List<Grupo>

    @Insert
    fun insert(grupo: Grupo)

    @Delete
    fun delete(grupo: Grupo)

}