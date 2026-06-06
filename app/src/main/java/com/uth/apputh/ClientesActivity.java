package com.uth.apputh;



import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.uth.apputh.dao.ClienteDAO;
import com.uth.apputh.models.Cliente;

import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity {

    ListView listaClientes;

    ClienteDAO clienteDAO;
    ArrayList<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clientes);

        listaClientes = findViewById(R.id.listaClientes);

        clienteDAO = new ClienteDAO(this);

        cargarClientes();

        listaClientes.setOnItemLongClickListener((parent, view, position, id) -> {

            Cliente cliente = clientes.get(position);

            clienteDAO.eliminar(cliente.getId());

            cargarClientes();

            Toast.makeText(
                    this,
                    "Cliente eliminado",
                    Toast.LENGTH_SHORT
            ).show();

            return true;
        });

        listaClientes.setOnItemLongClickListener((parent, view, position, id) -> {

            Cliente cliente = clientes.get(position);

            new AlertDialog.Builder(this)
                    .setTitle("Eliminar")
                    .setMessage("¿Desea eliminar este cliente?")
                    .setPositiveButton("Sí", (dialog, which) -> {

                        clienteDAO.eliminar(cliente.getId());

                        cargarClientes();

                        Toast.makeText(
                                this,
                                "Cliente eliminado",
                                Toast.LENGTH_SHORT
                        ).show();
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        });


    }

    private void cargarClientes() {

        clientes = clienteDAO.listar();

        ArrayAdapter<Cliente> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        clientes
                );

        listaClientes.setAdapter(adapter);
    }
}