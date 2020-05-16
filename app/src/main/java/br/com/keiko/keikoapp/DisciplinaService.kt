package br.com.keiko.keikoapp

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object DisciplinaService {

    val host = "https://garicci.pythonanywhere.com"
    val TAG = "WS_KeikoApp"

    fun getDisciplinas(context: Context): List<Disciplina>{
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/vagas"
            val json = HttpHelper.get(url)
            var disciplinas = parserJson<List<Disciplina>>(json)
            for(d in disciplinas){
                saveOffline(d)
            }
            Log.d(TAG, json)
            return disciplinas
        } else {
            var dao = DatabaseManager.getDisciplinaDAO()
            return dao.findAll()
        }
    }

    fun saveOffline(disciplina: Disciplina) : Boolean{
        val dao = DatabaseManager.getDisciplinaDAO()

        if(!existeDisciplina(disciplina)){
            dao.insert(disciplina)
        }
        return true
    }

    fun existeDisciplina(disciplina: Disciplina): Boolean{
        val dao = DatabaseManager.getDisciplinaDAO()
        return dao.getById(disciplina.id)!=null
    }

    fun save(disciplina: Disciplina): Response {
        val json = HttpHelper.post("$host/vagas", disciplina.toJson())
        return parserJson(json)
    }

    fun delete(disciplina: Disciplina): Response {
        Log.d(TAG, disciplina.id.toString())
        val url = "$host/vagas/${disciplina.id}"
        val json = HttpHelper.delete(url)
        Log.d(TAG, json)
        return parserJson(json)
    }

    inline fun<reified T> parserJson(json: String) : T{
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}