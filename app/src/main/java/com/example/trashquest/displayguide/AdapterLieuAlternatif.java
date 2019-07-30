package com.example.trashquest.displayguide;

import android.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trashquest.R;
import com.example.trashquest.db.LieuAlternatif;

import java.util.ArrayList;
import java.util.List;

public class AdapterLieuAlternatif extends RecyclerView.Adapter<AdapterLieuAlternatif.AlterViewHolder> {

    ArrayList<LieuAlternatif> list;

    public AdapterLieuAlternatif(ArrayList<LieuAlternatif> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AlterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lieualternatif_cell, viewGroup, false);
        AlterViewHolder alterViewHolder = new AlterViewHolder(view);
        return alterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlterViewHolder alterViewHolder, int i) {
        LieuAlternatif lieuAlternatif = list.get(i);
        alterViewHolder.display(lieuAlternatif);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AlterViewHolder extends RecyclerView.ViewHolder {

        TextView nom;
        TextView adresse;
        LieuAlternatif currentAlter;

        public AlterViewHolder(@NonNull final View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.tv_lieualter_nom);
            adresse = itemView.findViewById(R.id.tv_lieualter_adresse);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String affichage = currentAlter.getAdresse()+" "+currentAlter.getCodePostal()+" "+currentAlter.getCommune()+"\n"+currentAlter.getTypeLieu();
                    new AlertDialog.Builder(itemView.getContext()).setTitle(currentAlter.getNom()).setMessage(affichage).show();
                }
            });

        }

        public void display(LieuAlternatif lieuAlternatif) {
            currentAlter = lieuAlternatif;
            nom.setText(lieuAlternatif.getNom());
            adresse.setText(lieuAlternatif.getAdresse() + ", " + lieuAlternatif.getCodePostal() + ", " + lieuAlternatif.getCommune());
        }
    }
}
