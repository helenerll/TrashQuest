package com.example.trashquest.displayguide.fragmentguide;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trashquest.R;
import com.example.trashquest.db.TypeDechet;

public class TypeDetailFragment extends Fragment {

    TextView titre;
    TextView comment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.type_detail_fragment, container, false);
        titre = v.findViewById(R.id.tv_detailtype_title);
        comment = v.findViewById(R.id.tv_detailtype_body);
        Bundle bundle = getArguments();
        if (bundle != null) {
            TypeDechet typeDechet = bundle.getParcelable("typeDechet");
            titre.setText("Type de déchet : " + typeDechet.getNom() + "\n");
            comment.setText("Les consignes : " + typeDechet.getCommentaire() + "\n\nType de poubelle : " + typeDechet.getIdPoubelle());
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
