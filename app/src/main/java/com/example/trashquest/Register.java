package com.example.trashquest;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trashquest.db.Commune;
import com.example.trashquest.db.CommuneDAO;
import com.example.trashquest.db.Usager;
import com.example.trashquest.db.UsagerDAO;

public class Register extends AppCompatActivity  implements View.OnClickListener {

    private EditText login;
    private EditText codePostal;
    private Button enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

           login = findViewById(R.id.et_register_login);
           codePostal = findViewById(R.id.et_register_cp);
           enter = findViewById(R.id.btn_register_enter);

           enter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        UsagerDAO usagerDAO = new UsagerDAO(this);
        usagerDAO.open();

        Usager usager = new Usager(login.getText().toString().trim(), Integer.parseInt(codePostal.getText().toString().trim()));

        if (checkUsagerLogin(usager.getLogin()) && checkCodePostal(usager.getCodePostal())) {
            usagerDAO.insertUsager(usager);
            usagerDAO.close();
            Bundle bundle = new Bundle();
            bundle.putParcelable("currentUsager", usager);
            Intent intent = new Intent(this, Explain.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Votre code postal ne se situe pas à Bruxelles ou votre login est déjà utilisé, merci de ressayer", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkUsagerLogin(String login) {
        UsagerDAO usagerDAO = new UsagerDAO(this);
        usagerDAO.open();
        Usager searchUsager = usagerDAO.getUsagerWithLogin(login);
        usagerDAO.close();
        if (searchUsager == null) return true;
        else return true;
    }

    private boolean checkCodePostal(Integer codePostal) {
        CommuneDAO communeDAO = new CommuneDAO(this);
        communeDAO.open();
        Commune searchCommune = communeDAO.getCommuneWithCP(codePostal);
        communeDAO.close();
        if (searchCommune == null) return false;
        else return true;
    }
}
