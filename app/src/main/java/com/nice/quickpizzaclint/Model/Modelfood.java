package com.nice.quickpizzaclint.Model;

import java.io.Serializable;

public class Modelfood  implements Serializable {

    String  namefood,idfood,imagefood,large,medium,smalle,seefood, numcat,totsql, nummberlarge
            ,nummbermed;



    public String getNummbermed() {
        return nummbermed;
    }

    public void setNummbermed(String nummbermed) {
        this.nummbermed = nummbermed;
    }

    public Modelfood(String namefood, String idfood, String imagefood, String large,
                     String medium, String smalle, String seefood, String nummbercat) {
        this.namefood = namefood;
        this.idfood = idfood;
        this.imagefood = imagefood;
        this.large = large;
        this.medium = medium;
        this.smalle = smalle;
        this.seefood = seefood;
        this.numcat = nummbercat;
    }

    public Modelfood() {
    }

    public String getNummberlarge() {
        return nummberlarge;
    }

    public void setNummberlarge(String nummberlarge) {
        this.nummberlarge = nummberlarge;
    }

    public String getNamefood() {
        return namefood;
    }

    public void setNamefood(String namefood) {
        this.namefood = namefood;
    }

    public String getIdfood() {
        return idfood;
    }

    public void setIdfood(String idfood) {
        this.idfood = idfood;
    }

    public String getImagefood() {
        return imagefood;
    }

    public void setImagefood(String imagefood) {
        this.imagefood = imagefood;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getSmalle() {
        return smalle;
    }

    public void setSmalle(String smalle) {
        this.smalle = smalle;
    }

    public String getSeefood() {
        return seefood;
    }

    public void setSeefood(String seefood) {
        this.seefood = seefood;
    }

    public String getNumcat() {
        return numcat;
    }

    public String getTotsql() {
        return totsql;
    }

    public void setTotsql(String totsql) {
        this.totsql = totsql;
    }

    public void setNumcat(String numcat) {
        this.numcat = numcat;
    }




}
