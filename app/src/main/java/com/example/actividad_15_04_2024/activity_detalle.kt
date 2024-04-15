package com.example.actividad_15_04_2024

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class activity_detalle : AppCompatActivity() {
    private lateinit var datos : TextView
    private lateinit var objProducto : Producto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        datos = findViewById(R.id.txtDatos)
        objProducto = Producto()

        val infoRecibida = intent.extras

        val also = infoRecibida?.getInt("codigo")!!.also{objProducto.codigo = it}
        infoRecibida?.getString("descripcion")!!.also{objProducto.descripcion = it}
        infoRecibida?.getString("marca")!!.also{objProducto.marca = it}
        objProducto.talla = infoRecibida?.getInt("talla")!!
        objProducto.costo = infoRecibida?.getDouble("costo")!!
        objProducto.cantidad = infoRecibida?.getInt("cantidad")!!

        var talla : String? = null
        if(objProducto.talla == 1) talla = "Talla Chica"
        if(objProducto.talla == 2) talla = "Talla Mediana"
        if(objProducto.talla == 3) talla = "Talla Grande"

        datos.text = "\nCodigo: "+ objProducto.codigo +
                "\nDescripcion: " + objProducto.descripcion +
                "\nMarca: " + objProducto.marca +
                "\nTalla: " + talla +
                "\nCosto: " + objProducto.costo +
                "\nCantidad: " + objProducto.cantidad
    }
    private  fun regresar(v: View?){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}