package com.example.aluraorgs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aluraorgs.R
import com.example.aluraorgs.model.Produto

class ListaProdutosAdapter(
    private val context: Context,
    private val produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
// foi passado como generics pois é necessário criar uma classe interna para armazenar as referências de views do item

    // isso é uma inner class, ou seja, uma classe dentro de outra classe
    // fazemos uma extensão da classe RecyclerView.ViewHolder, nessa referência genérica, o construtor dela exige uma View
    // que vai ser a view que vamos criar dentro do processo de criação do ViewHolder, então podemos receber via construtor
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(produto: Produto) {
            val nome = itemView.findViewById<TextView>(R.id.produto_nome)
            nome.text = produto.nome
            val descricao = itemView.findViewById<TextView>(R.id.produto_descricao)
            descricao.text = produto.descricao
            val valor = itemView.findViewById<TextView>(R.id.produto_valor)
            valor.text = produto.valor.toPlainString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Para criar uma view exclusiva a partir de um layout utilizamos o conceito de inflar View
        // O from cria para nós uma referência de layoutInflater, pedimos o contexto via parâmetro
        val layoutInflater = LayoutInflater.from(context)
        /* o método inflate abaixo vai fazer o procedimento de transformar um layout em uma view exclusiva. Sobrecarga:
         res - layout que queremos criar/mostrar no recyclerView; root - viewGroup que vamos mandar nosso RecyclerView (ela vem através dos parâmetros do onCreateViewHolder)
         attach to root - vai determinar se vamos anexar essa views diretamente com o root, no adapter colocamos como false*/
        val view = layoutInflater.inflate(R.layout.produto_item, parent, false)
        return ViewHolder(view)
    }

    // Esse método deve atualizar as views do viewholder com os valores apropriados do objeto.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    // retorna o número de itens que o adapter está gerenciando. Pegamos a collection (passada via construtor) e referenciamos o seu tamanho com size
    override fun getItemCount(): Int = produtos.size
    fun atualiza(produtos: List<Produto>) {
        TODO("Not yet implemented")
    }

}
