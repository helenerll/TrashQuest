package com.example.trashquest.displayguide.fragmentguide;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trashquest.R;
import com.example.trashquest.db.Poubelle;
import com.example.trashquest.displayguide.AdapterPoubelle;

import java.util.ArrayList;

public class ListTrashbinFragment extends Fragment implements AdapterPoubelle.CallbackItemPoubelle {

    private CallbackPoubelle mCallbackPoubelle;

    public interface CallbackPoubelle {

        void goToDetailPouelle(Poubelle poubelle);
    }

    public void setCallbackPoubelle(CallbackPoubelle mCallbackPoubelle) {
        this.mCallbackPoubelle = mCallbackPoubelle;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_trashbin_fragment, container, false);
        Bundle bundle = getArguments();
        ArrayList<Poubelle> listTrashbin = bundle.getParcelableArrayList("listTrashbin");
        RecyclerView recyclerView = v.findViewById(R.id.rv_poubelle_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterPoubelle adapterPoubelle = new AdapterPoubelle(listTrashbin);
        adapterPoubelle.setCallbackItemPoubelle(this);
        recyclerView.setAdapter(adapterPoubelle);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPoubelleSelect(Poubelle poubelle) {

        if(mCallbackPoubelle != null) {
            mCallbackPoubelle.goToDetailPouelle( poubelle);
        }

    }
}
