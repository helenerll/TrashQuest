package com.example.trashquest.displayguide.fragmentguide;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trashquest.R;
import com.example.trashquest.db.LieuAlternatif;
import com.example.trashquest.displayguide.AdapterLieuAlternatif;

import java.util.ArrayList;

public class LieuAlterListFragment extends Fragment {

    String bundleKey;
    Integer titre;
    Integer commentaire;
    Integer logo;

    TextView textviewTitre;
    ImageView imageviewLogo;
    TextView textviewCommentaire;

    ArrayList<LieuAlternatif> listLieux;

    public LieuAlterListFragment(String bundleKey, Integer titre, Integer commentaire, Integer logo) {
        this.bundleKey = bundleKey;
        this.titre = titre;
        this.commentaire = commentaire;
        this.logo = logo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lieualter_list, container, false);
        textviewTitre = v.findViewById(R.id.tv_lieualter_title);
        imageviewLogo = v.findViewById(R.id.iv_lieualter_logo);
        textviewCommentaire = v.findViewById(R.id.tv_lieualter_comment);
        Bundle bundle = getArguments();
        listLieux = bundle.getParcelableArrayList(bundleKey);
        textviewTitre.setText(getString(titre));
        imageviewLogo.setImageResource(logo);
        textviewCommentaire.setText(getString(commentaire));
        RecyclerView recyclerView = v.findViewById(R.id.rv_lieualter_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterLieuAlternatif adapterLieuAlternatif = new AdapterLieuAlternatif(listLieux);
        recyclerView.setAdapter(adapterLieuAlternatif);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
