package com.example.actividad5m5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.actividad5m5.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener, wordFragment.OnDataPassListener{
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> dataList;
    public ActivityMainBinding mbinding;
    private Button boton;
    public int indice;

    int contador=1;

    List<String> data = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mbinding.getRoot());

        boton=mbinding.button;
        // Obtén los datos que se mostrarán en el RecyclerView

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList = getData(contador);
                contador++;
// Configura el RecyclerView
                recyclerView = findViewById(R.id.myRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter = new MyAdapter(dataList);
                adapter.setOnItemClickListener(MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    private List<String> getData(int numero) {
        // Simplemente devuelve una lista de datos para mostrar en el RecyclerView

        data.add("Elemento "+numero);
        // Agrega más elementos según sea necesario
        return data;
    }

    public void llamarFragment(String texto){

        wordFragment fragment=wordFragment.newInstance(texto);
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Inicia una transacción de fragmento
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // Reemplaza el contenido del contenedor con el fragmento BlankFragment
        transaction.replace(mbinding.contenedor.getId(), fragment);
        // Opcional: añade la transacción a la pila de retroceso
        transaction.addToBackStack(null);
        // Realiza la transacción
        transaction.commit();

    }

    @Override
    public void onItemClick(int position) {
        String selectedItem = dataList.get(position);
        indice=position;
        llamarFragment(selectedItem);


    }

    @Override
    public void onDataPass(String data) {
        dataList.set(indice,"Clicked! "+data);
        recyclerView.setAdapter(adapter);
    }
}