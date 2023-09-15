package com.example.proyectomoviles2023a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = FirebaseAuth.getInstance()
        auth.signOut()

        val correo = findViewById<EditText>(R.id.pt_ma_correo).text
        val password = findViewById<EditText>(R.id.p_ma_passw).text

        val botonIngresar = findViewById<Button>(R.id.btn_ma_ingresar)
        botonIngresar.setOnClickListener { 
            iniciarSesion(correo.toString(), password.toString())
        }
        

        val botonRegistrar = findViewById<Button>(R.id.btn_ma_regist)
        botonRegistrar.setOnClickListener {
            irAActividad(RegistrarseActivity::class.java)
        }

    }

    override fun onResume() {
        auth.signOut()
        super.onResume()
    }

    private fun iniciarSesion(correo: String, password: String) {
        auth.signInWithEmailAndPassword(correo, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    irAActividad(InicioActivity::class.java)
                }else{
                    showAlert()
                    findViewById<EditText>(R.id.tv_ma_correo).setText("")
                    findViewById<EditText>(R.id.tv_ma_passw).setText("")
                }

            }
    }

    private fun irAActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un erro al iniciar sesión")
        builder.setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}