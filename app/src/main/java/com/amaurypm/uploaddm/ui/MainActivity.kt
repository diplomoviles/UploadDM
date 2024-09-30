package com.amaurypm.uploaddm.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amaurypm.uploaddm.R
import com.amaurypm.uploaddm.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Instanciamos el viewmodel con una fución de extensión
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainViewModel.message.observe(this){ message ->

            binding.button.isEnabled = false

            AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage(message)
                .setPositiveButton("Aceptar"){ dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()


        }

        binding.button.setOnClickListener {

            binding.button.isEnabled = false

            val fileName = "wall1.jpg"

            val fileExt = fileName.substringAfterLast(".", "")

            //Creando una copia del archivo en caché
            val file = File(cacheDir, "AmauryPerea.$fileExt")
            file.createNewFile()

            //Copiamos el contenido de la imagen a ese archivo en caché
            file.outputStream().use { fos ->
                assets.open(fileName).copyTo(fos)
            }

            mainViewModel.uploadImage(file)


        }
    }
}