package br.com.keiko.tdx

import androidx.room.Room


object DatabaseManager {

    // singleton
    private var dbInstance: LMSDatabase
    init {
        val appContext = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                appContext, // Contexto global
                LMSDatabase::class.java, // Referência da classe (banco)
                "lms.sqlite" // Nome do arquivo (banco)
        ).build()
    }

    fun getGrupoDAO(): GrupoDAO {
        return dbInstance.GrupoDAO()
    }
}