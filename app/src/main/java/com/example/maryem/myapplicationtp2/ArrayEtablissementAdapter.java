package com.example.maryem.myapplicationtp2; /**
 * Created by maryem on 04/02/18.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ArrayEtablissementAdapter extends ArrayAdapter<Etablissement> {

    private LayoutInflater inflater;
    private List<Etablissement> Etablissements;

    public ArrayEtablissementAdapter(Context context, int textViewResourceId,
                                     List<Etablissement> Etablissements) {
        //mettre le layout qui contient la liste
        super(context, R.layout.activity_main, Etablissements);
        this.inflater = LayoutInflater.from(context);
        this. Etablissements= Etablissements;

    }


    @Override
// getView retournera la vue de l’item pour l’affichage.
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;


        // Réutilisation des vues
        if(convertView == null)
        {
            holder = new ViewHolder();

            //charger le fichier xml item
            convertView = inflater.inflate(R.layout.item, null);


            //label =

            // Associer les views du layout item au holder pour le retouner à la listView

            holder.label = (TextView)convertView.findViewById(R.id.label);
            holder.desc = (TextView)convertView.findViewById(R.id.desc);
            holder.img=  (ImageView) convertView.findViewById(R.id.img);
//sauvgarde la ref du holder en memoire pour la réutilisation par la suite
            convertView.setTag(holder);

        } else {
            //réutilisation du holder déja existant
            holder = (ViewHolder) convertView.getTag();
        }

        //stocker les données dans une vue via settag
        holder.label.setText(Etablissements.get(position).getLabel());
        holder.desc.setText(Etablissements.get(position).getName());
        holder.img.setImageResource(Etablissements.get(position).getImag());
        return convertView;
    }

    static class ViewHolder
    {
        public TextView label ;
        public TextView desc;
        public ImageView img;
    }

}