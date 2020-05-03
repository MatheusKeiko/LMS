package br.com.keiko.keikoapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this

    private var disciplinas = listOf<Disciplina>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        // acessar parametros da intent
        // intent é um atributo herdado de Activity
        val args = intent.extras
        // recuperar o parâmetro do tipo String
        val nome = args?.getString("login")

        // recuperar parâmetro simplificado
        val numero = intent.getIntExtra("nome",0)

        Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

        //mensagemInicial.text = "Bem vindo $nome"

        // botaoSair.setOnClickListener {cliqueSair()}
        //botaoContato.setOnClickListener {MostrarTelaBotao("Contato")}
        //botaoSobre.setOnClickListener {MostrarTelaBotao("Sobre nós")}
        //botaoLoja.setOnClickListener {MostrarTelaBotao("Web Service")}

        // colocar toolbar
        setSupportActionBar(toolbar)


        // alterar título da ActionBar
        supportActionBar?.title = "Disciplinas"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // configuração do menu lateral
        configuraMenuLateral()

        recyclerDisciplinas?.layoutManager = LinearLayoutManager(context)
        recyclerDisciplinas?.itemAnimator = DefaultItemAnimator()
        recyclerDisciplinas?.setHasFixedSize(true)
    }

    override fun onResume(){
        super.onResume()
        taskDisciplinas()
    }

    fun taskDisciplinas(){
        disciplinas = DisciplinaService.getDisciplinas(context)
        recyclerDisciplinas?.adapter = DisciplinaAdapter(disciplinas){ onClickDisciplina(it)}
    }
    fun onClickDisciplina(disciplina:Disciplina){
        Toast.makeText(context, "Clicou em ${disciplina.nome}", Toast.LENGTH_LONG).show()
    }

    fun MostrarTelaBotao(nomeBotao: String) {
        //Toast.makeText(context, "$nomeBotao", Toast.LENGTH_LONG).show()

        // criar intent
        val intent = Intent(context, BotoesActivity::class.java)
        // colocar parâmetros (opcional)
        val params = Bundle()
        params.putString("NomeTela", nomeBotao)
        intent.putExtras(params)

        // fazer a chamada esperando resultado
        startActivityForResult(intent, 1)

    }

    // configuração do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
         // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    // método que deve ser implementado quando a activity implementa a interface NavigationView.OnNavigationItemSelectedListener
    // para tratar os eventos de clique no menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_diciplinas -> {
                Toast.makeText(this, "Clicou Disciplinas", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_mensagens -> {
                Toast.makeText(this, "Clicou Mensagens", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_forum -> {
                Toast.makeText(this, "Clicou Forum", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_localizacao -> {
                Toast.makeText(this, "Clicou Localização", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
            }
        }

        // fecha menu depois de tratar o evento
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // id do item clicado
        val id = item.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}
