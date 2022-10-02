/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.entity;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author SHIVALI
 */
public class Locations {
    
    
    public int locid;
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message = "Name must be less than 50 characters.")
    private String locname;
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 500, message = "Description must be less than 500 characters.")
    private String locdescription;
    
    @NotBlank(message = "Street must not be empty.")
    @Size(max = 100, message = "Street must be less than 100 characters.")
    private String locstreet;
    @NotBlank(message = "City must not be empty.")
    @Size(max = 50, message = "City must be less than 50 characters.")
    private String loccity;
    @NotBlank(message = "State must not be empty.")
    @Size(max = 2, message = "State must be less than 2 characters.")
    private String locstate;
    @NotBlank(message = "Zip must not be empty.")
    @Size(max = 6, message = "Zip must be less than 6 characters.")
    private String loczip;
    @NotBlank(message = "Latitude must not be empty.")
    @Size(max = 12, message = "Latitude must be less than 12 characters.")
    private String latitude;
    @NotBlank(message = "Longitude must not be empty.")
    @Size(max = 12, message = "Longitude must be less than 12 characters.")
    private String longitude;
    
    
    
     public Locations() {
    }

    public Locations(int id, String name, String description, String street, String city, String state, String zip, String latitude, String longitude) {
        this.locid = id;
        this.locname = name;
        this.locdescription = description;
        this.locstreet = street;
        this.loccity = city;
        this.locstate = state;
        this.loczip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLocid() {
        return locid;
    }

    public void setLocid(int locid) {
        this.locid = locid;
    }

    public String getLocname() {
        return locname;
    }

    public void setLocname(String locname) {
        this.locname = locname;
    }

    public String getLocdescription() {
        return locdescription;
    }

    public void setLocdescription(String locdescription) {
        this.locdescription = locdescription;
    }

    public String getLocstreet() {
        return locstreet;
    }

    public void setLocstreet(String locstreet) {
        this.locstreet = locstreet;
    }

    public String getLoccity() {
        return loccity;
    }

    public void setLoccity(String loccity) {
        this.loccity = loccity;
    }

    public String getLocstate() {
        return locstate;
    }

    public void setLocstate(String locstate) {
        this.locstate = locstate;
    }

    public String getLoczip() {
        return loczip;
    }

    public void setLoczip(String loczip) {
        this.loczip = loczip;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.locid;
        hash = 29 * hash + Objects.hashCode(this.locname);
        hash = 29 * hash + Objects.hashCode(this.locdescription);
        hash = 29 * hash + Objects.hashCode(this.locstreet);
        hash = 29 * hash + Objects.hashCode(this.loccity);
        hash = 29 * hash + Objects.hashCode(this.locstate);
        hash = 29 * hash + Objects.hashCode(this.loczip);
        hash = 29 * hash + Objects.hashCode(this.latitude);
        hash = 29 * hash + Objects.hashCode(this.longitude);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Locations other = (Locations) obj;
        if (this.locid != other.locid) {
            return false;
        }
        if (!Objects.equals(this.locname, other.locname)) {
            return false;
        }
        if (!Objects.equals(this.locdescription, other.locdescription)) {
            return false;
        }
        if (!Objects.equals(this.locstreet, other.locstreet)) {
            return false;
        }
        if (!Objects.equals(this.loccity, other.loccity)) {
            return false;
        }
        if (!Objects.equals(this.locstate, other.locstate)) {
            return false;
        }
        if (!Objects.equals(this.loczip, other.loczip)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }
@Override
    public String toString() {
        return "Locations{" + "id=" + locid + ", name=" + locname + ", description=" + locdescription + ", street=" + locstreet + ", city=" + loccity + ", state=" + locstate + ", zip=" + loczip + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }
    
}
