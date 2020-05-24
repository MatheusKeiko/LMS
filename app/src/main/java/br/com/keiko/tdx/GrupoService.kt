package br.com.keiko.tdx

import android.content.Context
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.net.URL

object GrupoService {

    //URL WS
    val host = "https://garicci.pythonanywhere.com"
    val TAG = "WS_KeikoApp"

    fun getGrupo (context: Context): List<Grupo> {
        var grupo = ArrayList<Grupo>()
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/grupo"
            val json = HttpHelper.get(url)
            grupo = parserJson(json)
            // salvar offline
            for (d in grupo) {
                saveOffline(d)
            }
            return grupo
        } else {
            val dao = DatabaseManager.getGrupoDAO()
            val grupo = dao.findAll()
            return grupo
        }

    }

    fun getGrupo (context: Context, id: Long): Grupo? {

        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/grupo/${id}"
            val json = HttpHelper.get(url)
            val grupo = parserJson<Grupo>(json)

            return grupo
        } else {
            val dao = DatabaseManager.getGrupoDAO()
            val grupo = dao.getById(id)
            return grupo
        }

    }

    fun save(grupo: Grupo): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val json = HttpHelper.post("$host/grupo", grupo.toJson())
            return parserJson(json)
        }
        else {
            saveOffline(grupo)
            return Response("OK", "Grupo salvo no dispositivo")
        }
    }

    fun saveOffline(grupo: Grupo) : Boolean {
        val dao = DatabaseManager.getGrupoDAO()

        if (! existeGrupo(grupo)) {
            dao.insert(grupo)
        }

        return true

    }

    fun existeGrupo(grupo: Grupo): Boolean {
        val dao = DatabaseManager.getGrupoDAO()
        return dao.getById(grupo.id) != null
    }

    fun delete(grupo: Grupo): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/grupo/${grupo.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getGrupoDAO()
            dao.delete(grupo)
            return Response(status = "OK", msg = "Dados salvos (localmente)")
        }

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}