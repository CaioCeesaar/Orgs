package com.example.aluraorgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aluraorgs.R
import com.example.aluraorgs.adapter.ListaProdutosAdapter
import com.example.aluraorgs.dao.ProdutosDao
import com.example.aluraorgs.model.Produto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dao = ProdutosDao()
        recyclerView.adapter = ListaProdutosAdapter(this, dao.buscaTodos())
        Log.i("MainActivity", "onResume: ${dao.buscaTodos()}")
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }

}