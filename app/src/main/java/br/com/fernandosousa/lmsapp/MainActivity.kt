package br.com.fernandosousa.lmsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // encontra objeto pelo id
        campo_imagem.setImageResource(R.drawable.imagem_login)

        texto_login.text = getString(R.string.mensagem_login)

        // evento no botao de login forma 1
//        botaoLogin.setOnClickListener {
//            val valorUsuario = campo_usuario.text.toString()
//            val valorSenha = campo_senha.text.toString()
//            Toast.makeText(this, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()
//        }

        // segunda forma: delegar para método
        botao_login.setOnClickListener {onClickLogin() }

    }

    fun onClickLogin(){
        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()
        //Toast.makeText(context, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()

        if(valorUsuario == "aluno" && valorSenha == "impacta") {
            // Direcionar para a proxima tela

            // criar intent
            val intent = Intent(context, TelaInicialActivity::class.java)
            // colocar parâmetros (opcional)
            val params = Bundle()
            params.putString("login", valorUsuario)
            intent.putExtras(params)

            // fazer a chamada esperando resultado
            startActivityForResult(intent, 1)

        }
        else {
            // Mostrar mensagem de usuário ou senha inválidos
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
