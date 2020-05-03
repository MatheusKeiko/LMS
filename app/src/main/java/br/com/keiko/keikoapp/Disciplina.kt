package br.com.keiko.keikoapp

class Disciplina {
    var id: Long = 0
    var nome: String = ""
    var ementa: String = ""
    var foto: String = ""
    var professor: String = ""

    override fun toString(): String{
        return "Disciplina(nome ='$nome')"
    }
}