package com.example.trashquest.displayguide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trashquest.R;
import com.example.trashquest.db.TypeDechet;

import java.util.ArrayList;

public class AdapterType extends RecyclerView.Adapter<AdapterType.TypeViewHolder> {

    ArrayList<TypeDechet> list;

//région callback
    public interface CallbackItemType {
        void onTypeSelected(TypeDechet typeDechet);
}

    private CallbackItemType callbackItemType;

    public void setCallbackItemType(CallbackItemType callbackItemType) {
        this.callbackItemType = callbackItemType;
    }
    //endregion callback


    public AdapterType(ArrayList<TypeDechet> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.typedechet_cell, viewGroup, false);
        TypeViewHolder typeViewHolder = new TypeViewHolder(view);
        return typeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder typeViewHolder, int i) {
        TypeDechet typeDechet = list.get(i);
        typeViewHolder.display(typeDechet);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder {

        TextView nom;
        TypeDechet currentType;

        public TypeViewHolder(@NonNull final View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.tv_typedechet_nom);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callbackItemType != null) {
                        callbackItemType.onTypeSelected(currentType);
                    }
                }
            });
        }

        public void display(TypeDechet typeDechet) {
            currentType = typeDechet;
            nom.setText(typeDechet.getNom());
        }
    }

}
