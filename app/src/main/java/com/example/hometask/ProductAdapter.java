package com.example.hometask;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hometask.api.ProductResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    public  static final String baseUrl ="http://unionint.net/";
    private  Context context;
    private ProductResponse productResponse;

    public ProductAdapter(Context context, ProductResponse productResponse) {
        this.context = context;
        this.productResponse = productResponse;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(context)
        .inflate(R.layout.item_row,parent,false);
      return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {

        holder.titletv.setText(productResponse.getReport().get(position).getTitle());
        holder.Pricetv.setText(productResponse.getReport().get(position).getPrice());

         final String  image =  baseUrl + productResponse.getReport().get(position).getImage();


        Picasso.get().load(image).into(holder.imageView);




    }

    @Override
    public int getItemCount()
    {
        return productResponse.getReport().size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView titletv,Pricetv,tktv;
        ImageView imageView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            titletv = itemView.findViewById(R.id.row_title);
            Pricetv = itemView.findViewById(R.id.row_price);
            tktv = itemView.findViewById(R.id.row_tk);
            imageView = itemView.findViewById(R.id.row_productImage);

        }
    }

}
