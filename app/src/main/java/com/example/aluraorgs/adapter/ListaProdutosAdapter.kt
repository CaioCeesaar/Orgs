package com.example.aluraorgs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aluraorgs.databinding.ProdutoItemBinding
import com.example.aluraorgs.model.Produto

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    // não utilizamos diretamente do construtor para manter a referência de uma lista imutável para quem envia para o adapter
    // produtos será um parâmetro que vem do construtor que podemos manipular livremente
    private val dataSet = produtos.toMutableList()

    // foi passado como generics pois é necessário criar uma classe interna para armazenar as referências de views do item

    // isso é uma inner class, ou seja, uma classe dentro de outra classe
    // fazemos uma extensão da classe RecyclerView.ViewHolder, nessa referência genérica, o construtor dela exige uma View
    // que vai ser a view que vamos criar dentro do processo de criação do ViewHolder, então podemos receber via construtor
    class ViewHolder(private val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun vincula(produto: Produto) {
            binding.produtoItemNome.text = produto.nome
            binding.produtoItemDescricao.text = produto.descricao
            binding.produtoItemValor.text = produto.valor.toPlainString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Para criar uma view exclusiva a partir de um layout utilizamos o conceito de inflar View
        // O from cria para nós uma referência de layoutInflater, pedimos o contexto via parâmetro
//        val layoutInflater = LayoutInflater.from(context)
        /* o método inflate abaixo vai fazer o procedimento de transformar um layout em uma view exclusiva. Sobrecarga:
         res - layout que queremos criar/mostrar no recyclerView; root - viewGroup que vamos mandar nosso RecyclerView (ela vem através dos parâmetros do onCreateViewHolder)
         attach to root - vai determinar se vamos anexar essa views diretamente com o root, no adapter colocamos como false*/
//        val view = layoutInflater.inflate(R.layout.produto_item, parent, false)
        val binding = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    // Esse método deve atualizar as views do viewholder com os valores apropriados do objeto.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = dataSet[position]
        holder.vincula(produto)
    }

    // retorna o número de itens que o adapter está gerenciando. Pegamos a collection (passada via construtor) e referenciamos o seu tamanho com size
    override fun getItemCount(): Int = dataSet.size

    fun atualiza(produtos: List<Produto>) {
        // limpa a lista atual de produtos do adapter
        this.dataSet.clear()
        // adiciona todos os produtos da nova lista `produtos` à lista atual do adapter `this.produtos`
        this.dataSet.addAll(produtos)
        // notifica ao adapter de que houve uma mudança em seus dados, fazendo com que a lista de
        // exibição (RecyclerView) seja atualizada com os novos dados
        notifyDataSetChanged()
    }


}
