package br.com.keiko.tdx

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso

// Define o construtor que recebe a lista de grupos e o evento de clique
class GrupoAdapter (
    val grupo: List<Grupo>,
    val onClick: (Grupo) -> Unit): RecyclerView.Adapter<GrupoAdapter.GrupoViewHolder>() {

    // ViewHolder com os elemetos da tela
    class GrupoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_grupo)

        }

    }

    // Quantidade de grupos na lista
    override fun getItemCount() = this.grupo.size

    // inflar layout do adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrupoViewHolder {
        // Infla view no adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_grupo, parent, false)

        // Retornar ViewHolder
        val holder = GrupoViewHolder(view)
        return holder
    }

    // Bind para atualizar Views com os dados
    override fun onBindViewHolder(holder: GrupoViewHolder, position: Int) {
        val context = holder.itemView.context

        // Recuperar objeto grupo
        val grupo = grupo[position]

        // Atualizar dados do grupo

        holder.cardNome.text = grupo.nome
        holder.cardProgress.visibility = View.VISIBLE

        // Download da imagem
        Picasso.with(context).load(grupo.foto).fit().into(holder.cardImg,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }

                    override fun onError() {
                        holder.cardProgress.visibility = View.GONE
                    }
                })

        // adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(grupo)}
    }
}