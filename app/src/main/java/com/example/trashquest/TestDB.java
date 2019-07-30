package com.example.trashquest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trashquest.db.Commune;
import com.example.trashquest.db.CommuneDAO;

public class TestDB extends AppCompatActivity implements View.OnClickListener {

    //cette activité sert à tester les insertions en db

    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        initView();

        button.setOnClickListener(this);


    }

    private void initView() {
        editText = findViewById(R.id.et_test_search);
        button = findViewById(R.id.btn_test_button);
        textView = findViewById(R.id.tv_test_view);
    }

    @Override
    public void onClick(View v) {
        CommuneDAO communeDAO = new CommuneDAO(this);
        communeDAO.open();
        Commune commune = communeDAO.getCommuneWithCP(Integer.parseInt(editText.getText().toString()));
        textView.setText(commune.toString());

    }
}
