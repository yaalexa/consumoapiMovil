package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView perfilesRecyclerView;
    private PerfilesAdapter profileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciar el RecyclerView en el layout
        perfilesRecyclerView = findViewById(R.id.recyclePerfiles);

        // Configurar el RecyclerView con un LayoutManager
        perfilesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar la lista de perfiles y el adaptador
        List<Perfiles> profileList = new ArrayList<>();
        profileAdapter = new PerfilesAdapter(profileList);

        // Establecer el adaptador en el RecyclerView
        perfilesRecyclerView.setAdapter(profileAdapter);

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lexa2334.pythonanywhere.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de ApiService
        ApiServicios apiService = retrofit.create(ApiServicios.class);

        // Realizar la solicitud para obtener la lista de perfiles
        Call<List<Perfiles>> call = apiService.obtenerPerfiles();
        call.enqueue(new Callback<List<Perfiles>>() {
            @Override
            public void onResponse(Call<List<Perfiles>> call, Response<List<Perfiles>> response) {
                if (response.isSuccessful()) {
                    List<Perfiles> profiles = response.body();
                    if (profiles != null) {
                        // Agregar los perfiles a la lista y notificar al adaptador
                        profileList.addAll(profiles);
                        profileAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Perfiles>> call, Throwable t) {
                // Manejar errores de la solicitud
                t.printStackTrace();
            }
        });
    }
}
