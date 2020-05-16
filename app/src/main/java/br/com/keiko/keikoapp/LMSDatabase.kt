package br.com.keiko.keikoapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities= arrayOf(Disciplina::class), version = 1)
abstract class LMSDatabase: RoomDatabase() {
    abstract fun disciplinaDAO():DisciplinaDAO
}