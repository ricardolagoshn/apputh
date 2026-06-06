package com.uth.apputh.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.uth.apputh.database.DatabaseHelper;
import com.uth.apputh.models.Cliente;

import java.util.ArrayList;

public class ClienteDAO {

    private DatabaseHelper dbHelper;

    public ClienteDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // INSERTAR
    public long insertar(Cliente cliente) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nombre", cliente.getNombre());
        valores.put("apellido", cliente.getApellido());
        valores.put("edad", cliente.getEdad());
        valores.put("correo", cliente.getCorreo());

        long resultado = db.insert(
                DatabaseHelper.TABLA_CLIENTES,
                null,
                valores
        );

        db.close();

        return resultado;
    }

    // LISTAR
    public ArrayList<Cliente> listar() {

        ArrayList<Cliente> lista = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM clientes",
                null
        );

        if(cursor.moveToFirst()) {

            do {

                Cliente cliente = new Cliente();

                cliente.setId(cursor.getInt(0));
                cliente.setNombre(cursor.getString(1));
                cliente.setApellido(cursor.getString(2));
                cliente.setEdad(cursor.getInt(3));
                cliente.setCorreo(cursor.getString(4));

                lista.add(cliente);

            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lista;
    }

    // ACTUALIZAR
    public int actualizar(Cliente cliente) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nombre", cliente.getNombre());
        valores.put("apellido", cliente.getApellido());
        valores.put("edad", cliente.getEdad());
        valores.put("correo", cliente.getCorreo());

        int filas = db.update(
                DatabaseHelper.TABLA_CLIENTES,
                valores,
                "id=?",
                new String[]{
                        String.valueOf(cliente.getId())
                }
        );

        db.close();

        return filas;
    }

    // ELIMINAR
    public int eliminar(int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int filas = db.delete(
                DatabaseHelper.TABLA_CLIENTES,
                "id=?",
                new String[]{
                        String.valueOf(id)
                }
        );

        db.close();

        return filas;
    }


}