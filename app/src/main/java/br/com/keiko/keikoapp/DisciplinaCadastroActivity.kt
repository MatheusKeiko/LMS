package br.com.keiko.keikoapp


import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_disciplina.*

class DisciplinaCadastroActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_disciplina)
        setTitle("Nova Disciplina")

        salvarDisciplina.setOnClickListener {
            val disciplina = Disciplina()
            disciplina.nome = nomeDisciplina.text.toString()
            disciplina.ementa = ementaDisciplina.text.toString()
            disciplina.professor = professorDisciplina.text.toString()
            disciplina.foto = urlFoto.text.toString()

            taskAtualizar(disciplina)
        }
    }

    private fun taskAtualizar(disciplina: Disciplina) {
        Thread {
            DisciplinaService.save(disciplina)
            runOnUiThread {
                finish()
            }
        }.start()
    }
}

