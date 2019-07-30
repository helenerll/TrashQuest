package com.example.trashquest;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.trashquest.db.Usager;

public class Explain extends AppCompatActivity implements View.OnClickListener {

    private Button enter;
    Usager usager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);
        enter = findViewById(R.id.btn_explain_next);
        enter.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        usager = bundle.getParcelable("currentUsager");
    }

    @Override
    public void onClick(View v) {
        Bundle bundleRegister = new Bundle();
        bundleRegister.putParcelable("currentUsager", usager);
        Intent intent = new Intent(this, Menu.class);
        intent.putExtras(bundleRegister);
        //TODO ajouter login + cp usager
        startActivity(intent);
    }
}
