package com.example.trashquest.displayguide.fragmentguide;

import android.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trashquest.R;
import com.example.trashquest.db.Produit;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ProductViewHolder> {

    ArrayList<Produit> list;

    public AdapterProduct(ArrayList<Produit> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public AdapterProduct.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.produce_cell, viewGroup, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.ProductViewHolder productViewHolder, int i) {
        Produit produit = list.get(i);
        productViewHolder.display(produit);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView nom;
        Produit currentProduct;

        public ProductViewHolder(@NonNull final View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.tv_product_nom);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(itemView.getContext()).setTitle(currentProduct.getNom()).setMessage(currentProduct.getIdPoubelle()).show();

                }
            });
        }

        public void display(Produit produit) {
            currentProduct = produit;
            nom.setText(produit.getNom());
        }
    }
}
