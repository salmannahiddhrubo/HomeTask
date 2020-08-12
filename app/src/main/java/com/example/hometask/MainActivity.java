package com.example.hometask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.Toast;

import com.example.hometask.api.ProductResponse;
import com.example.hometask.api.ProductService;

public class MainActivity extends AppCompatActivity {
    public static  final String baseUrl ="http://unionint.net/";
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =findViewById(R.id.recyclerView);

         getApiResponse();
    }

    public void getApiResponse() {

        final ProductService productService = RetrofitClient
                .getClient(baseUrl)
                .create(ProductService.class);

        productService.getProductResponse()
                .enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        if(response.isSuccessful()){

                            ProductResponse productResponse = response.body();
                            LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(MainActivity.this);
                            productAdapter = new ProductAdapter(MainActivity.this,productResponse);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            //GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
                            recyclerView.setLayoutManager(linearLayoutManager);
                           productAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(productAdapter);
                            Toast.makeText(MainActivity.this, ""+productResponse.getReport().size(), Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(MainActivity.this, "Api key experied ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Api key experied ", Toast.LENGTH_SHORT).show();

                    }
                });

              }
    }











