package com.uth.apputh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.uth.apputh.dao.ClienteDAO;
import com.uth.apputh.models.Cliente;

public class MainActivity extends AppCompatActivity {

    TextInputEditText nombre, apellido, edad, correo;
    MaterialButton btnAgregar, btnVerClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        edad = findViewById(R.id.edad);
        correo = findViewById(R.id.correo);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnVerClientes = findViewById(R.id.btnVerClientes);

        btnAgregar.setOnClickListener(v -> {

            Cliente cliente = new Cliente();

            cliente.setNombre(nombre.getText().toString());
            cliente.setApellido(apellido.getText().toString());
            cliente.setEdad(
                    Integer.parseInt(
                            edad.getText().toString()
                    )
            );
            cliente.setCorreo(correo.getText().toString());

            ClienteDAO dao = new ClienteDAO(this);

            long resultado = dao.insertar(cliente);

            if(resultado > 0){

                Toast.makeText(
                        this,
                        "Cliente guardado correctamente",
                        Toast.LENGTH_SHORT
                ).show();

                nombre.setText("");
                apellido.setText("");
                edad.setText("");
                correo.setText("");

            }else{

                Toast.makeText(
                        this,
                        "Error al guardar",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        btnVerClientes.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            ClientesActivity.class
                    );

            startActivity(intent);
        });

    }
}