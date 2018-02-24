package com.example.maryem.myapplicationtp2;

/**
 * Created by maryem on 30/01/18.
 */

public class Etablissement {

    private String name ;
    private  String label ;
    private double lat ;
    private double lng ;
    private  int imag ;

    public double getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public Etablissement(String label, String name, int img){
        this.label = label;
        this.name = name;

        this.imag =img ;
    }

    public Etablissement(String label, String name, int img ,double lat , double lng){
        this.label = label;
        this.name = name;
        this.imag =img ;
        this.lat = lat ;
        this.lng = lng ;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public int getImag() {
        return imag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setImag(int imag) {
        this.imag = imag;
    }
}
