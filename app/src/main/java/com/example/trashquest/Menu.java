package com.example.trashquest;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.trashquest.calendar.CalendarFrag;
import com.example.trashquest.credit.CreditFragment;
import com.example.trashquest.db.LieuAlternatif;
import com.example.trashquest.db.LieuAlternatifDAO;
import com.example.trashquest.db.Usager;
import com.example.trashquest.displayguide.fragmentguide.LieuAlterListFragment;
import com.example.trashquest.displayguide.fragmentguide.ListProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.trashquest.db.Poubelle;
import com.example.trashquest.db.PoubelleDAO;
import com.example.trashquest.db.TypeDechet;
import com.example.trashquest.db.TypeDechetDAO;
import com.example.trashquest.displayguide.fragmentguide.GuideFragment;
import com.example.trashquest.displayguide.fragmentguide.ListTrashbinFragment;
import com.example.trashquest.displayguide.fragmentguide.ListTypeFragment;
import com.example.trashquest.displayguide.fragmentguide.PoubelleDetailFragment;
import com.example.trashquest.displayguide.fragmentguide.TypeDetailFragment;

import java.util.ArrayList;

public class Menu extends AppCompatActivity
        implements GuideFragment.CallbackGuide, ListTrashbinFragment.CallbackPoubelle, ListTypeFragment.CallbackType, CreditFragment.OnFragmentInteractionListener {

    BottomNavigationView bottomNavigationView;
    String currentCodePostal;
    Usager currentUsager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle bundleCodePostal = getIntent().getExtras();
        currentUsager = bundleCodePostal.getParcelable("currentUsager");
        currentCodePostal = String.valueOf(currentUsager.getCodePostal());
        initView();
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        loadFragment(new GuideFragment());
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bnv_menu_menu);
    }

    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            switch (menuItem.getItemId()) {
                case (R.id.action_guide):
                    fragment = new GuideFragment();
                    break;
                case (R.id.action_calendar):
                    fragment = new CalendarFrag();
                    break;
                case (R.id.action_credit):
                    fragment = new CreditFragment();
                    break;
            }
            loadFragment(fragment);
            return true;
        }
    };

    @Override
    public void goToListDechet() {
        ListTypeFragment fragment = new ListTypeFragment();
        ArrayList<TypeDechet> list = getListTypeDechet();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", list);
        fragment.setArguments(bundle);
        fragment.setCallbackType(this);
        loadFragment(fragment);
    }

    @Override
    public void getToListPoubelle() {
        ListTrashbinFragment fragment = new ListTrashbinFragment();
        ArrayList<Poubelle> listTrashbin = getListPoubelle();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listTrashbin", listTrashbin);
        fragment.setArguments(bundle);
        fragment.setCallbackPoubelle(this);
        loadFragment(fragment);
    }

    @Override
    public void goToListProduct() {
        ListProductFragment listProductFragment = new ListProductFragment();
        loadFragment(listProductFragment);
    }

    @Override
    public void goToDetailPouelle(Poubelle poubelle) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("poubelle", poubelle);
        TypeDechetDAO typeDechetDAO = new TypeDechetDAO(this);
        typeDechetDAO.open();
        TypeDechet typeDechet = typeDechetDAO.getTypeDechetWithId(poubelle.getIdTypeDechet());
        typeDechetDAO.close();
        bundle.putParcelable("typeDechet", typeDechet);
        PoubelleDetailFragment poubelleDetailFragment = new PoubelleDetailFragment();
        poubelleDetailFragment.setArguments(bundle);
        loadFragment(poubelleDetailFragment);
    }

    @Override
    public void gotToDetailType(TypeDechet typeDechet) {
        ArrayList<LieuAlternatif> list;
        String titrefrag;
        Integer idTitre;
        Integer idTexte;
        Integer idLogo;
        String type = typeDechet.getNom();
        Integer idType = typeDechet.getId();
        switch (idType) {
            case 7 :
                list = getListOliobox(currentCodePostal);
                idTitre = R.string.oliobox_title;
                idTexte = R.string.oliobox_comment;
                idLogo = R.mipmap.oil_orange;
                loadDetailLieuAlter(list, idTitre, idTexte, idLogo);
                break;
            case 8 :
                 list = getListRecupTextile(currentCodePostal);
                idTitre = R.string.recuptextile_title;
                idTexte = R.string.recuptextile_comment;
                idLogo = R.mipmap.textile_logo;
                 loadDetailLieuAlter(list, idTitre, idTexte, idLogo);
                break;
            case 11 :
                list = getListRecupElec();
                idTitre = R.string.parc_title;
                idTexte = R.string.electro_comment;
                idLogo = R.mipmap.blender_logo_yellow;
                loadDetailLieuAlter(list, idTitre, idTexte, idLogo);
                break;
            case 12 :
                list = getListRecupElec();
                idTitre = R.string.parc_title;
                idTexte = R.string.electro_comment;
                idLogo = R.mipmap.laptop_logo_blue;
                loadDetailLieuAlter(list, idTitre, idTexte, idLogo);
                break;
            case 13 :
                list = getListRecupLight(currentCodePostal);
                idTitre = R.string.lightbulb_title;
                idTexte = R.string.lightbubl_comment;
                idLogo = R.mipmap.lightbulb_logo_orange;
                loadDetailLieuAlter(list, idTitre, idTexte, idLogo);
                break;
            case 16 :
                list = getListRecupPiles(currentCodePostal);
                idTitre = R.string.battery_title;
                idTexte = R.string.battery_comment;
                idLogo = R.mipmap.battery_logo_orange;
                loadDetailLieuAlter(list, idTitre, idTexte, idLogo);
                break;
            case 17 :
                list = getListDeadAnimals();
                idTitre = R.string.deadanimals_title;
                idTexte = R.string.deadanimals_comment;
                idLogo = R.mipmap.paws_yellow_logo;
                loadDetailLieuAlter(list, idTitre, idTexte, idLogo);
                break;
            default:
                Bundle bundle = new Bundle();
                bundle = putTypeDechetIntoBundle(typeDechet);
                TypeDetailFragment typeDetailFragment = new TypeDetailFragment();
                typeDetailFragment.setArguments(bundle);
                loadFragment(typeDetailFragment);
                break;
        }

    }

    private ArrayList<LieuAlternatif> getListDeadAnimals() {
        LieuAlternatifDAO lieuAlternatifDAO = new LieuAlternatifDAO(this);
        lieuAlternatifDAO.open();
        ArrayList<LieuAlternatif> list = lieuAlternatifDAO.getAllDeadAnimals();
        lieuAlternatifDAO.close();
        return list;
    }

    private ArrayList<LieuAlternatif> getListRecupPiles(String currentCodePostal) {
        LieuAlternatifDAO lieuAlternatifDAO = new LieuAlternatifDAO(this);
        lieuAlternatifDAO.open();
        ArrayList<LieuAlternatif> list = lieuAlternatifDAO.getRecupPilles(currentCodePostal);
        lieuAlternatifDAO.close();
        return list;
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fl_menu_display, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public ArrayList<TypeDechet> getListTypeDechet() {
        TypeDechetDAO typeDechetDAO = new TypeDechetDAO(this);
        typeDechetDAO.open();
        ArrayList<TypeDechet> list = typeDechetDAO.getAllTypeDechet();
        return list;
    }

    public ArrayList<Poubelle> getListPoubelle() {
        PoubelleDAO poubelleDAO = new PoubelleDAO(this);
        poubelleDAO.open();
        ArrayList<Poubelle> list = poubelleDAO.getAllPoubelle();
        return list;
    }

    public ArrayList<LieuAlternatif> getListOliobox(String codePostal) {
        LieuAlternatifDAO lieuAlternatifDAO = new LieuAlternatifDAO(this);
        lieuAlternatifDAO.open();
        ArrayList<LieuAlternatif> list = lieuAlternatifDAO.getOliobox(codePostal);
        lieuAlternatifDAO.close();
        return list;
    }

    private ArrayList<LieuAlternatif> getListRecupLight(String codePostal) {
        LieuAlternatifDAO lieuAlternatifDAO = new LieuAlternatifDAO(this);
        lieuAlternatifDAO.open();
        ArrayList<LieuAlternatif> list = lieuAlternatifDAO.getRecupAmpoules(codePostal);
        lieuAlternatifDAO.close();
        return list;
    }

    private ArrayList<LieuAlternatif> getListRecupElec() {
        LieuAlternatifDAO lieuAlternatifDAO = new LieuAlternatifDAO(this);
        lieuAlternatifDAO.open();
        ArrayList<LieuAlternatif> list = lieuAlternatifDAO.getAllParcConteneur();
        lieuAlternatifDAO.close();
        return list;
    }

    private ArrayList<LieuAlternatif> getListRecupTextile(String codePostal) {
        LieuAlternatifDAO lieuAlternatifDAO = new LieuAlternatifDAO(this);
        lieuAlternatifDAO.open();
        ArrayList<LieuAlternatif> list = lieuAlternatifDAO.getRecupTextile(codePostal);
        lieuAlternatifDAO.close();
        return list;
    }

    private void loadDetailLieuAlter(ArrayList<LieuAlternatif> list, Integer idTitre, Integer idComment, Integer idLogo) {
        Bundle bundle = new Bundle();
        LieuAlterListFragment lieuAlterListFragment;
        if (list != null) {bundle.putParcelableArrayList("list", list);
            lieuAlterListFragment = new LieuAlterListFragment("list", idTitre, idComment, idLogo);
            lieuAlterListFragment.setArguments(bundle);
            loadFragment(lieuAlterListFragment);}
        else {
            Toast.makeText(this, getString(idTitre) + "\nMalheureusement il n'y a aucun point de collecte dans votre commune", Toast.LENGTH_SHORT).show();
        }

    }

    private Bundle putTypeDechetIntoBundle(TypeDechet typeDechet) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("typeDechet", typeDechet);
        return bundle;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
