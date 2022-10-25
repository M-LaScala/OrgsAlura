package com.example.orgs.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

// Alt enter para usar as dicas
// Ctrl + alt + L = Para formatar o codigo
// para tornar uma Classe em uma Activity ela tem que Herdar a classe e ser definida no Manifest Android
// Ctrl + alt  + M = transforma tudo numa função
// Ctrl + alt + 0 = Otimiza os imports
class ListaProdutosActivity : AppCompatActivity() {

    private val dao = ProdutosDao()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())

    // Iniciando uma variavel do tipo binding
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    // Sobrescrevendo o ciclo de criação do app para conseguir executar codigo.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecycleView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecycleView() {
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter
    }

}

//recycleView.layoutManager = LinearLayoutManager(this) // Seta o Layout que ira ser mostrado na pagina.

/*
setContentView(R.layout.activity_main) // Aqui vc joga as coisas na tela principal
val nome = findViewById<TextView>(R.id.nome) // Aqui estamos pegando a view pelo id por referencia
nome.text = "Cesta de frutas"
val descricao = findViewById<TextView>(R.id.descricao)
descricao.text = "Laranja, manga e maça"
val valor = findViewById<TextView>(R.id.valor)
valor.text = "19.99"
 */

//toast - apresenta uma msg e some
//Toast.makeText( this, "Bem vido(a) ao Orgs!", Toast.LENGTH_SHORT).show()

// Criando uma vizualização generica
//val view = TextView(this)
//view.setText("Cesta de frutas")

