package br.com.keiko.tdx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_grupo.*
import kotlinx.android.synthetic.main.login.*

class GrupoCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_grupo)
        setTitle("Novo Grupo")

        salvarGrupo.setOnClickListener {
            val grupo = Grupo()
            grupo.nome = nomeGrupo.text.toString()
            grupo.ementa = ementaGrupo.text.toString()
            grupo.responsavel = responsavelGrupo.text.toString()
            grupo.foto = urlFoto.text.toString()

            taskAtualizar(grupo)
        }
    }

    private fun taskAtualizar(grupo: Grupo) {
        // Thread para salvar o Grupo
        Thread {
            GrupoService.save(grupo)
            runOnUiThread {
                // Após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
