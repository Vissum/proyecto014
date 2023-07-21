package com.example.proyecto014

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : Activity() {
    private lateinit var et1: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et1 = findViewById(R.id.et1)
        val archivos = fileList()

        if (existe(archivos, "notas.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("notas.txt"))
                val br = BufferedReader(archivo)
                var linea: String?
                var todo = ""
                while (br.readLine().also { linea = it } != null) {
                    todo += "$linea\n"
                }
                br.close()
                archivo.close()
                et1.setText(todo)
            } catch (e: IOException) {
            }
        }
    }

    private fun existe(archivos: Array<String>, archbusca: String): Boolean {
        for (f in archivos.indices) {
            if (archbusca == archivos[f]) {
                return true
            }
        }
        return false
    }

    fun grabar(v: View) {
        try {
            val archivo = OutputStreamWriter(openFileOutput("notas.txt", Activity.MODE_PRIVATE))
            archivo.write(et1.text.toString())
            archivo.flush()
            archivo.close()
        } catch (e: IOException) {
        }
        val t = Toast.makeText(this, "Los datos fueron grabados", Toast.LENGTH_SHORT)
        t.show()
    }
}

