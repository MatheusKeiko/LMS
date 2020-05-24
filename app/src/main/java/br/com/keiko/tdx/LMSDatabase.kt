package br.com.keiko.tdx

import androidx.room.Database
import androidx.room.RoomDatabase

// anotação define a lista de entidades e a versão do banco
@Database(entities = arrayOf(Grupo::class), version = 1)
abstract class LMSDatabase: RoomDatabase() {
    abstract fun GrupoDAO(): GrupoDAO
}