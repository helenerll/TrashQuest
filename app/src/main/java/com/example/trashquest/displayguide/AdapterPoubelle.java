package com.example.trashquest.displayguide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trashquest.R;
import com.example.trashquest.db.Poubelle;

import java.util.ArrayList;

public class AdapterPoubelle extends RecyclerView.Adapter<AdapterPoubelle.PoubelleViewHolder> {

    ArrayList<Poubelle> list;

    //region Callback
    public  interface CallbackItemPoubelle {
        void onPoubelleSelect(Poubelle poubelle);
    }

    private CallbackItemPoubelle mCallbackItemPoubelle;

    public void setCallbackItemPoubelle(CallbackItemPoubelle mCallbackItemPoubelle) {
        this.mCallbackItemPoubelle = mCallbackItemPoubelle;
    }
    //endregion


    public AdapterPoubelle(ArrayList<Poubelle> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PoubelleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.poubelle_cell, viewGroup, false);
        PoubelleViewHolder poubelleViewHolder = new PoubelleViewHolder(view);
        return poubelleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PoubelleViewHolder poubelleViewHolder, int i) {
        Poubelle poubelle = list.get(i);
        poubelleViewHolder.display(poubelle);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class PoubelleViewHolder extends RecyclerView.ViewHolder {

        TextView nom;
        Poubelle currentPoubelle;

        public PoubelleViewHolder(@NonNull final View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.tv_poubelle_nom);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mCallbackItemPoubelle != null)
                    {
                        mCallbackItemPoubelle.onPoubelleSelect(currentPoubelle);
                    }
                }
            });
        }

        public void display(Poubelle poubelle) {
            currentPoubelle = poubelle;
            nom.setText(poubelle.getId());
        }
    }
}
