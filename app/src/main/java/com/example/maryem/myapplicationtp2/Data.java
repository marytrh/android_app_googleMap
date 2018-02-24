package com.example.maryem.myapplicationtp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by maryem on 18/02/18.
 */

public class Data extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABSE_NAME = "compte.db";

    public static final String TABLE_LOGIN = "table_login";

    public static final String COLOUMN_ID = "_id";

    public static final String COLOUMN_LOG = "_login";

    public static final String COLOUMN_PASS = "_pass";


    public static final String TABLE_ETAB = "table_etab";

    public static final String COLOUMN_LAB = "_lab";

    public static final String COLOUMN_NAME = "_name";

    public static final String COLOUMN_IMG = "_img";
    public Data(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABSE_NAME, factory, DATABASE_VERSION);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

        onCreate(db);

    }

    public void onCreate(SQLiteDatabase db) {


        String query1 = "CREATE TABLE  " + TABLE_LOGIN + "(" +

                COLOUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                COLOUMN_LOG + " TEXT , " + COLOUMN_PASS + " TEXT ) ;" ;
        db.execSQL(query1);

        String query2 = "CREATE TABLE  " + TABLE_ETAB + "(" +

                COLOUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                COLOUMN_LAB + " TEXT , " + COLOUMN_NAME + " TEXT, " +COLOUMN_IMG + " INTEGER ) ;" ;
        db.execSQL(query2);

    }

    public Login getLoginWithlogin(String log, String Pass ){
        SQLiteDatabase bdd = getWritableDatabase();
        String[] selectionArgs = {log, Pass};
        String whereClause = COLOUMN_LOG + "=?" + " and " + COLOUMN_PASS +"=?" ;
        //Récupérer dans un Cursor les valeurs correspondantes à un login)
        Cursor c = bdd.query(TABLE_LOGIN, new String[] {COLOUMN_ID, COLOUMN_LOG, COLOUMN_PASS},whereClause , selectionArgs,null, null, null);
        //bdd.close();
        return cursorToLogin(c , bdd );
    }



    public void insertlogin(Login login){

        //Création d'un objet ContentValues
         ContentValues values = new ContentValues();

        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COLOUMN_LOG, login.getLogin());
        values.put(COLOUMN_PASS, login.getPass());

        SQLiteDatabase bdd = getWritableDatabase();
        //insérer l'objet dans la BDD via le ContentValues
        bdd.insert(TABLE_LOGIN, null, values);
        bdd.close();

    }

    private Login cursorToLogin(Cursor c , SQLiteDatabase bdd ){
//si aucun élément n'a été retourné par la requéte, on renvoie null
        if (c.getCount() == 0) {
            bdd.close();
            return null;
        }
//Sinon on se place sur le premier élément
        else {
            c.moveToFirst();
//instancier un login
            Login login = new Login();
//on lui affecte toutes les infos à partir des infos contenues dans le Cursor
            login.setId(c.getInt(c.getColumnIndex("_id")));
            login.setLogin(c.getString(c.getColumnIndex("_login")));
            login.setPass(c.getString(c.getColumnIndex("_pass")));
//On ferme le cursor
            c.close();

            bdd.close();
//On retourne le login

            return login;
        }
    }

    public void insertEtab(Etablissement etab){

        //Création d'un objet ContentValues
        ContentValues values = new ContentValues();

        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COLOUMN_LAB, etab.getLabel());
        values.put(COLOUMN_NAME, etab.getName());

        SQLiteDatabase bdd = getWritableDatabase();
        //insérer l'objet dans la BDD via le ContentValues
        bdd.insert(TABLE_ETAB, null, values);
        bdd.close();

    }

    public ArrayList databasetostring() {

        //String dbString = "";

        String label ,nom   ;
        int img ;
       // ArrayList<Products> MesProduits=new ArrayList<Products>();
        ArrayList<Etablissement> MesEtab=new ArrayList<Etablissement>();

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_ETAB + " WHERE 1";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if (c.getString(c.getColumnIndex("_lab")) != null)

            {

                // dbString += c.getString(c.getColumnIndex("productname"));

                //dbString += "\n";

                label = c.getString(c.getColumnIndex("_lab")) ;
                nom= c.getString(c.getColumnIndex("_name")) ;
                img= c.getInt(c.getColumnIndex("_img")) ;

                MesEtab.add(new Etablissement(label , nom , img));


            }

            c.moveToNext();

        }

        db.close();

        return MesEtab;

    }

    public void deleteEtab(String EtabLab) {

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_ETAB+ " WHERE " + COLOUMN_LAB + "=\"" + EtabLab + "\";");

    }
}
