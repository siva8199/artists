package com.example.artist;

public class AllUserMmber {

    String name, prof, location, email, gender, uid, url, sco;

    public AllUserMmber(String name, String prof, String location, String email, String gender, String uid, String url, String sco) {
        this.name = name;
        this.prof = prof;
        this.location = location;
        this.email = email;
        this.gender = gender;
        this.uid = uid;
        this.url = url;
        this.sco = sco;
    }

    public AllUserMmber() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSco() {
        return sco;
    }

    public void setSco(String sco) {
        this.sco = sco;
    }
}
