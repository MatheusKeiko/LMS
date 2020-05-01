package br.com.keiko.keikoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.toolbar.*

class BotoesActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.botoes)

        val args = intent.extras
        val nomeTela = args?.getString("NomeTela")


        // colocar toolbar
        setSupportActionBar(toolbar)


        // alterar título da ActionBar
        supportActionBar?.title = nomeTela



    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }



}