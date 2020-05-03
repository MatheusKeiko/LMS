package br.com.keiko.keikoapp

import android.content.Context

object DisciplinaService {

    fun getDisciplinas(contex: Context): List<Disciplina>{
        val disciplinas = mutableListOf<Disciplina>()

        for(i in 1..10){
            val d = Disciplina()
            d.nome="Disciplina $i"
            d.ementa="Ementa $i"
            d.professor="Professor $i"
            d.foto = "https://media-exp1.licdn.com/dms/image/C4E0BAQHpWUcp1nE-Jw/company-logo_200_200/0?e=2159024400&v=beta&t=NS-UK8TYJnDh2fb7C232oJsBRX7RBn9fearOTg8BNBQ"
            disciplinas.add(d)
        }
        return disciplinas
    }
}