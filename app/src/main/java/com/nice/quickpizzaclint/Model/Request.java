package com.nice.quickpizzaclint.Model;

import java.util.List;

public class Request {


    String namereq,totalreq,address,timereq, statusreq,telephonereq;
    List<Modelfood> listreq;

    public Request(String namereq, String totalreq, String address, String timereq,
                   String statusreq,   String telephonereq, List<Modelfood> listreq) {
        this.namereq = namereq;
        this.totalreq = totalreq;
        this.address = address;
        this.timereq = timereq;
        this.statusreq = statusreq;
        this.telephonereq = telephonereq;
        this.listreq = listreq;
    }

    public String getNamereq() {
        return namereq;
    }

    public void setNamereq(String namereq) {
        this.namereq = namereq;
    }

    public String getTotalreq() {
        return totalreq;
    }

    public void setTotalreq(String totalreq) {
        this.totalreq = totalreq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimereq() {
        return timereq;
    }

    public void setTimereq(String timereq) {
        this.timereq = timereq;
    }

    public String getStatusreq() {
        return statusreq;
    }

    public void setStatusreq(String statusreq) {
        this.statusreq = statusreq;
    }

    public String getTelephonereq() {
        return telephonereq;
    }

    public void setTelephonereq(String telephonereq) {
        this.telephonereq = telephonereq;
    }

    public List<Modelfood> getListreq() {
        return listreq;
    }

    public void setListreq(List<Modelfood> listreq) {
        this.listreq = listreq;
    }

//    String namereq,totalreq,address,timereq, statusreq;
//    List<Modelfood> listreq;


//
//    // if i want more data in list i put it from Sqliteadapterr class in part getAllData()
//
//    // in anther app i must change adress to addressreq  :D
//
//    public Request( String address,String totalreq, List<Modelfood> list,String timereq,String statusreq) {
//
//        this.totalreq = totalreq;
//        this.address = address;
//        this.listreq = list;
//        this.timereq = timereq;
//        this.statusreq = statusreq;
//
//    }
//
//    public String getNamereq() {
//        return namereq;
//    }
//
//    public void setNamereq(String namereq) {
//        this.namereq = namereq;
//    }
//
//    public String getTotalreq() {
//        return totalreq;
//    }
//
//    public void setTotalreq(String totalreq) {
//        this.totalreq = totalreq;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public List<Modelfood> getListreq() {
//        return listreq;
//    }
//
//    public void setListreq(List<Modelfood> listreq) {
//        this.listreq = listreq;
//    }
//
//    public String getTimereq() {
//        return timereq;
//    }
//
//    public void setTimereq(String timereq) {
//        this.timereq = timereq;
//    }
//
//    public String getStatusreq() {
//        return statusreq;
//    }
//
//    public void setStatusreq(String statusreq) {
//        this.statusreq = statusreq;
//    }
//
//    public Request() {
//    }





}
