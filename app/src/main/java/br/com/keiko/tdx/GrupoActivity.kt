package br.com.keiko.tdx

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_grupo.*
import kotlinx.android.synthetic.main.toolbar.*

class GrupoActivity : DebugActivity() {

    private val context: Context get() = this
    var grupo: Grupo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grupo)

        // Recuperar obj de Grupo da Intent
        if (intent.getSerializableExtra("grupo") is Grupo)
            grupo = intent.getSerializableExtra("grupo") as Grupo

        // configurar título com nome do Grupo e botão de voltar da Toobar
        // colocar toolbar
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = grupo?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Atualizar dados do Grupo
        nomeGrupo.text = grupo?.nome
        Picasso.with(this).load(grupo?.foto).fit().into(imagemGrupo,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }
    // Método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_grupo, menu)
        return true
    }
    // Opção booleana para exclusão de Grupo
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Id do item clicado
        val id = item?.itemId
        // Verificação de alvo do clique
        // remover o grupo no WS
        if  (id == R.id.action_remover) {
            // Alerta para confirmação da remoção
            // Remoção ocorre (apenas) se houver confirmação positiva
            AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage("Deseja excluir o grupo")
                    .setPositiveButton("Sim") {
                        dialog, which ->
                            dialog.dismiss()
                            taskExcluir()
                    }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                    }.create().show()
        }
        // Botão up (navigation)
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    // Exclusão de Grupo
    private fun taskExcluir() {
        if (this.grupo != null && this.grupo is Grupo) {
            // Thread para remoção do Grupo
            Thread {
                GrupoService.delete(this.grupo as Grupo)
                runOnUiThread {
                    // Pós remoção, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }

}
