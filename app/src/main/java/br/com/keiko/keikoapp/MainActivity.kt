package br.com.keiko.keikoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        campo_imagem.setImageResource(R.drawable.imagem_login)

        texto_login.text = getString(R.string.mensagem_login)

        botao_login.setOnClickListener {onClickLogin() }

        progressBar.visibility = View.INVISIBLE
        var lembrar = Prefs.getBoolean("lembrar")
        var usuario = Prefs.getString("lembrarNome")
        var senha = Prefs.getString("lembrarSenha")

        campo_usuario.setText(usuario)
        campo_senha.setText(senha)
        checkLembrar.isChecked = lembrar
    }

    fun onClickLogin(){
        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()

        Prefs.setBoolean("lembrar", checkLembrar.isChecked)

        if(checkLembrar.isChecked){
            Prefs.setString("lembrarNome", valorUsuario)
            Prefs.setString("lembrarSenha", valorSenha)
        }else{
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

        if(valorUsuario == "aluno" && valorSenha == "impacta") {
            val intent = Intent(context, TelaInicialActivity::class.java)

            val params = Bundle()
            params.putString("login", valorUsuario)

            intent.putExtras(params)

            startActivityForResult(intent, 1)

        }
        else {
            mensagemtela.text = "Usuário ou senha incorretos"
            Toast.makeText(context, "Digite o login e a senha novamente", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}