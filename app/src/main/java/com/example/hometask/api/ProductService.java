package com.example.hometask.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("api/home_page.php")
    Call<ProductResponse> getProductResponse();
}
