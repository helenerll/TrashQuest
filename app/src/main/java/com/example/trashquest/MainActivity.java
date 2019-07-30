package com.example.trashquest;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trashquest.db.CommuneDAO;
import com.example.trashquest.db.LieuAlternatifDAO;
import com.example.trashquest.db.PoubelleDAO;
import com.example.trashquest.db.TypeDechetDAO;
import com.example.trashquest.db.Usager;
import com.example.trashquest.db.UsagerDAO;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText login;
    private Button enter;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        enter.setOnClickListener(this);
        register.setOnClickListener(this);


        createLieuAlternatifDB();
        createTypeDechetDB();
        createPoubelleDB();
        createCommuneDAO();



    }

    private void createCommuneDAO() {
        CommuneDAO communeDAO = new CommuneDAO(this);
        communeDAO.open();
        if (communeDAO.countRow() == 0) {
            try {
                communeDAO.populateCommuneWithCSV();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        communeDAO.close();
    }

    private void createPoubelleDB() {
        PoubelleDAO poubelleDAO = new PoubelleDAO(this);
        poubelleDAO.open();
        if (poubelleDAO.countRow() == 00) {
            try {
                poubelleDAO.populatePoubelleWithCSV();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        poubelleDAO.close();
    }

    private void createTypeDechetDB() {
        TypeDechetDAO typeDechetDAO = new TypeDechetDAO(this);
        typeDechetDAO.open();
        if (typeDechetDAO.countRow() == 0) {
            try {
                typeDechetDAO.populateFromCSV();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        typeDechetDAO.close();
    }

    private void createLieuAlternatifDB() {
        LieuAlternatifDAO lieuAlternatifDAO = new LieuAlternatifDAO(this);
        lieuAlternatifDAO.open();
        if (lieuAlternatifDAO.countRow() == 0) {
            lieuAlternatifDAO.populateWithCSV();
        }
        lieuAlternatifDAO.close();
    }

    private void initView() {
        login = findViewById(R.id.et_welcome_login);
        enter = findViewById(R.id.btn_welcome_enter);
        register = findViewById(R.id.btn_welcome_register);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn_welcome_enter) :
                //TODO comparaison login avec db login
                UsagerDAO usagerDAO = new UsagerDAO(this);
                usagerDAO.open();
                Usager usager = usagerDAO.getUsagerWithLogin(login.getText().toString().trim());
                if (usager == null) {
                    Toast.makeText(this, "Veuillez créer un login pour accéder à l'application", Toast.LENGTH_LONG).show();
                    Intent registerIntent = new Intent(this, Register.class);
                    startActivity(registerIntent);
                } else {
                    //else
                    Intent menuIntent = new Intent(this, Menu.class);
                    Toast.makeText(this, "Bienvenue " + usager.getLogin() + usager.getCodePostal(), Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("currentUsager", usager);
                    menuIntent.putExtras(bundle);
                    startActivity(menuIntent);
                }
                usagerDAO.close();
                break;

            case (R.id.btn_welcome_register) :
                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
        }
        finish();
    }
}
