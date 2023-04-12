package com.nice.quickpizzaclint.Model;

import java.io.Serializable;

public class Model implements Serializable {
    String namemodle,  imagemodel,numbermodel,email,nameemail;



    String nameM, priceM,containM,imageM,idM;
    String tot;
    String quantity;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameemail() {
        return nameemail;
    }

    public void setNameemail(String nameemail) {
        this.nameemail = nameemail;
    }

    public Model(String namemodle, String imagemodel) {
        this.namemodle = namemodle;

        this.imagemodel = imagemodel;

    }

    public Model() {
    }

    public String getNamemodle() {
        return namemodle;
    }

    public void setNamemodle(String namemodle) {
        this.namemodle = namemodle;
    }

    public String getNumbermodel() {
        return numbermodel;
    }

    public void setNumbermodel(String numbermodel) {
        this.numbermodel = numbermodel;
    }

    public String getImagemodel() {
        return imagemodel;
    }

    public void setImagemodel(String imagemodel) {
        this.imagemodel = imagemodel;
    }




}
