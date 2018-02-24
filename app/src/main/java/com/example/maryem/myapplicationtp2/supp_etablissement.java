package com.example.maryem.myapplicationtp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class supp_etablissement extends AppCompatActivity {

    Data testHandler ;
    TextView lab ;
    TextView msg ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supp_etablissement);
        lab = (TextView)findViewById(R.id.ed1);
        msg = (TextView)findViewById(R.id.msg);
        testHandler = new Data(this , null ,null ,1);
    }

    public void myClik(View view) {
        String name = lab.getText().toString() ;
        testHandler.deleteEtab(name) ;
        lab.setText("");
        msg.setText("Etablissement supprimé ");

    }
}
