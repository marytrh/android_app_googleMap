package com.example.maryem.myapplicationtp2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ajout_etablissement extends AppCompatActivity {

    Data testHandler ;
    TextView lab ;
    TextView name ;
    TextView msg ;
    //TextView img ;

    private ImageView imageView;
    private static final int SELECT_PICTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_etablissement);

        lab = (TextView)findViewById(R.id.ed1);
        name = (TextView)findViewById(R.id.ed2);
        msg = (TextView)findViewById(R.id.msg);

        imageView = (ImageView) findViewById(android.R.id.icon);
        //img = (TextView)findViewById(R.id.ed1);
        testHandler = new Data(this , null ,null ,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = getPath(data.getData());
            imageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap getPath(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        // cursor.close();
        // Convert file path into bitmap image using below line.
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        return bitmap;
    }

    public void selectImage(View view) {

        Intent intent;
        intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }


    public void myClik(View view) {

        //  db.open();
        Etablissement etab = new Etablissement( lab.getText().toString() ,name.getText().toString() , 0);
        testHandler.insertEtab(etab);
        lab.setText("");
        name.setText("");
        msg.setText("Etablissement bien ajouté ");

    }

}
