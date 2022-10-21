package com.example.orgs.dao

import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            // Prdouto de teste
            Produto(
                nome = "Salada de frutas",
                descricao = "Banana, moranga, melancia",
                valor = BigDecimal("19.80")
            )
        )
    }

}