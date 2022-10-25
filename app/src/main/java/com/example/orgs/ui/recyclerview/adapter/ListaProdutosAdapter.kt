package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orgs.R
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.model.Produto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    private val context : Context,
    produtos : List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(produto: Produto) {
            val nome = binding.produtoItemNome
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            val valorEmMoeda: String = formataParaMoedaBR(produto.valor)
            valor.text = valorEmMoeda

            val visibilidade = if(produto.imagem != null){
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageProduto.visibility = visibilidade

            binding.imageProduto.load(produto.imagem){
                fallback(R.drawable.erro)
                error(R.drawable.erro)
            }

        }

        private fun formataParaMoedaBR(valor: BigDecimal): String {
            val fomatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return fomatador.format(valor)
        }

    }

    // Criar uma referencia do tipo ViewHolder, que vai pegar as views apresentadas e vai manter essa view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Indiciar qual é o item que esta sendo colocado na view do recyclerview
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    // Determina para o adapter quantos items a gente quer apresentar dentro do adapter
    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
