package com.example.actividad_15_04_2024

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var codigo : EditText
    private lateinit var descripcion : EditText
    private lateinit var costo : EditText
    private lateinit var cantidad : EditText
    private lateinit var marcas : ListView
    private lateinit var tallas : RadioGroup
    private lateinit var tChica : RadioButton
    private lateinit var tMediana : RadioButton
    private lateinit var tGrande : RadioButton
    private lateinit var objProducto : Producto
    private var marcaSel : String = "Hermes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        codigo = findViewById(R.id.edtCodigo)
        descripcion = findViewById(R.id.edtDescripcion)
        costo = findViewById(R.id.edtCosto)
        cantidad = findViewById(R.id.edtCantidad)
        marcas = findViewById(R.id.ltvMarcas)
        tallas = findViewById(R.id.rapTallas)
        tChica = findViewById(R.id.rbtChica)
        tMediana = findViewById(R.id.rbtMediana)
        tGrande = findViewById(R.id.rbtGrande)
        objProducto = Producto()

        val lstMarcas = listOf("Hemres", "Dior", "Gucci", "Chanel", "Prada")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, lstMarcas)
        marcas.adapter = adaptador
        marcas.setOnItemClickListener {parent, view, position, id ->
            marcaSel = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Marca seleccionada: $marcaSel", Toast.LENGTH_LONG).show()

    }
}
    fun onClick(v: View?){
        when(v?.id){
            R.id.ibtnAgregar -> agregar()
            R.id.ibtnMostrar -> mostrar()
            R.id.ibtnLimpiar -> limpiar()
        }
    }

    private fun agregar(){
        if(codigo.text.isNotBlank() && codigo.text.isNotEmpty() && descripcion.text.isNotEmpty() && descripcion.text.isNotBlank()){
            objProducto.codigo = codigo.text.toString().toInt()
            objProducto.descripcion = descripcion.text.toString()
            objProducto.marca = marcaSel
            if(tChica.isChecked) objProducto.talla = 1
            if(tMediana.isChecked) objProducto.talla = 2
            if(tGrande.isChecked) objProducto.talla = 3
            objProducto.costo = costo.text.toString().toDouble()
            objProducto.cantidad = cantidad.text.toString().toInt()
            Toast.makeText(this, "Producto agregado", Toast.LENGTH_LONG).show()
            limpiar()
        }else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show()
        }
    }

    private fun mostrar() {
        val intent = Intent(this, activity_detalle::class.java)
        intent.putExtra("codigo", objProducto.codigo)
        intent.putExtra("descripcion", objProducto.descripcion)
        intent.putExtra("marca", objProducto.marca)
        intent.putExtra("talla", objProducto.talla)
        intent.putExtra("costo", objProducto.costo)
        intent.putExtra("cantidad", objProducto.cantidad)
        startActivity(intent)
    }

    private fun limpiar(){
        codigo.text = null
        descripcion.text = null
        costo.text = null
        cantidad.text = null
        tallas.clearCheck()
        codigo.requestFocus()
    }
}