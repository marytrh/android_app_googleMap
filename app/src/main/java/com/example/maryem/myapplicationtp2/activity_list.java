package com.example.maryem.myapplicationtp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class activity_list extends AppCompatActivity {

    ListView l ;
    List<Etablissement> Etablissements;
    Etablissement Etat;
    ArrayEtablissementAdapter adapter;
   // Data bdd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //bdd =  new Data(this , null ,null ,1);
       // Etablissements = bdd.databasetostring() ;

        List<Etablissement> Etablissements = new ArrayList<Etablissement>();
        Etablissements.add(new Etablissement("ENSIAS", "Ecole Nationale Supérieure d'Informatique et d'Analyse des Systèmes", R.drawable.ensias , 33.9843118 , -6.888887910742142));
       Etablissements.add(new Etablissement("FMP", "Faculté de Médecine et de Pharmacie", R.drawable.phar , 33.9829695 , -6.8559009000000515));
        Etablissements.add(new Etablissement("FMD", "Faculté de Médecine Dentaire", R.drawable.logo, 33.9806984, -6.870296000000053));
       Etablissements.add(new Etablissement("ENSET", "Ecole Normale Supérieure de l'Enseignement Technique", R.drawable.enset, 33.9688607 , -6.8773221999999805));
//liason entre la source des données (Etablissements) et les controles du layout item à affichïer
        adapter = new ArrayEtablissementAdapter(this, R.layout.item,
                Etablissements);
//L'association entre la ListView et l'Adapter
        l = (ListView) findViewById(R.id.listview);
//// Binding resources Array to ListAdapter
        l.setAdapter(adapter);

       /* l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Etablissement item = (Etablissement) l.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext() , item.getLabel() + " selected",
                        Toast.LENGTH_LONG).show();
            }
        });*/

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Etablissement item = (Etablissement) l.getAdapter().getItem(position);
                Intent intent = new Intent(activity_list.this, MapsActivity.class);
                intent.putExtra("lab",item.getLabel());
                intent.putExtra("desc",item.getName());
                intent.putExtra("lat",item.getLat());
                intent.putExtra("lng",item.getLng());
                activity_list.this.startActivity(intent);
                activity_list.this.startActivityForResult(intent, 100);
            }
        });




    }

    public boolean onCreateOptionsMenu(Menu menu) {
// TODO Auto-generated method stub
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
// Red item was selected
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                this.startActivityForResult(intent, 100);
            case R.id.item2:
                Intent intent1 = new Intent(this, ajout_etablissement.class);
                this.startActivity(intent1);
                this.startActivityForResult(intent1, 100);
// Green item was selected
            case R.id.item3:
                Intent intent2 = new Intent(this, supp_etablissement.class);
                this.startActivity(intent2);
                this.startActivityForResult(intent2, 100);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

  /*  public void myClik(View view) {

        Intent intent = new Intent(this, MapsActivity.class);
        this.startActivity(intent);
        this.startActivityForResult(intent, 100);
    }*/
}
