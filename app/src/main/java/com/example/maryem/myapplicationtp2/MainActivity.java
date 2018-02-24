package com.example.maryem.myapplicationtp2;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView login ;
    TextView pass ;
    TextView message ;
    //DbHandler db ;
   // LoginBDD db ;
    Data testHandler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (TextView)findViewById(R.id.ed1);
        pass = (TextView)findViewById(R.id.ed2);
        message = (TextView)findViewById(R.id.msg);

       // db = new DbHandler(this , null ,null ,1);
        //db = new LoginBDD(this) ;

        testHandler = new Data(this , null ,null ,1);
    }

    public void myClik(View view) {

        //db.open();
        Login log = testHandler.getLoginWithlogin(login.getText().toString() , pass.getText().toString()) ;
      //  int  i = testHandler.getLoginWithlogin(login.getText().toString() , pass.getText().toString())  ;
       // Products products = new Products(login.getText().toString() , pass.getText().toString() , pass.getText().toString());
        //testHandler.addProduct(products);
        if ( log == null ) {
            login.setText("");
            pass.setText("");
            message.setText("Compte n'existe pas");
        //message.setText("produit bien ajouté");

        }
        else {
            Intent intent = new Intent(this, activity_list.class);
            //Intent intent = new Intent(this, MapsActivity.class);
            this.startActivity(intent);
            this.startActivityForResult(intent, 100);
        }

    }

   public void myClik2(View view) {
      //  db.open();
      Login log = new Login( login.getText().toString() , pass.getText().toString()) ;
      testHandler.insertlogin(log) ;
      login.setText("");
      pass.setText("");
      message.setText("Compte bien crée");
    }
}
