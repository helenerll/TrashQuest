package com.example.trashquest.displayguide.fragmentguide;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.trashquest.R;

public class GuideFragment extends Fragment {

    Button searchByTrashbin;
    Button searchByType;
    Button searchByProduce;

    private CallbackGuide mCallbackGuide;

    public interface CallbackGuide {
//        void loadFragment(Fragment fragment);
//        ArrayList<TypeDechet> getListTypeDechet();
//        ArrayList<Poubelle> getListPoubelle();

        void goToListDechet();
        void getToListPoubelle();
        void goToListProduct();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.guide_fragment, container, false);
        initView(v);
        initOnClickMethod();
        return v;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_guide_poubelle :
                    mCallbackGuide.getToListPoubelle();
                    break;
                case R.id.btn_guide_trashtype :
                    mCallbackGuide.goToListDechet();
                    break;
                case R.id.btn_guide_produit :
                    break;
            }
        }
    };

    private void initOnClickMethod() {
        searchByTrashbin.setOnClickListener(listener);
        searchByType.setOnClickListener(listener);
        searchByProduce.setOnClickListener(listener);
    }

    private void initView(View v) {
        searchByTrashbin = v.findViewById(R.id.btn_guide_poubelle);
        searchByType = v.findViewById(R.id.btn_guide_trashtype);
        searchByProduce = v.findViewById(R.id.btn_guide_produit);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallbackGuide) {
            mCallbackGuide = (CallbackGuide) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbackGuide = null;
    }


}
