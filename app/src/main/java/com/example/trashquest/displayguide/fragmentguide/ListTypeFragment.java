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
import com.example.trashquest.db.TypeDechet;
import com.example.trashquest.displayguide.AdapterType;

import java.util.ArrayList;

public class ListTypeFragment extends Fragment implements AdapterType.CallbackItemType {

    private CallbackType callbackType;

    public interface CallbackType {

        void gotToDetailType(TypeDechet typeDechet);
    }

    public void setCallbackType(CallbackType callbackType) {
        this.callbackType = callbackType;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_type_fragment, container, false);
        Bundle bundle = getArguments();
        ArrayList<TypeDechet> list = bundle.getParcelableArrayList("list");
        RecyclerView recyclerView = v.findViewById(R.id.rv_typedechet_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterType adapterType = new AdapterType(list);
        adapterType.setCallbackItemType(this);
        recyclerView.setAdapter(adapterType);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onTypeSelected(TypeDechet typeDechet) {
        if (callbackType != null) {
            callbackType.gotToDetailType(typeDechet);
        }

    }
}
