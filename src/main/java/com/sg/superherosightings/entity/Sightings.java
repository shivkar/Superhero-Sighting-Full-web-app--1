/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

/**
 *
 * @author SHIVALI
 */
public class Sightings {
    
     public int sightingId;
    private int superheroId;
    private int locationId;
    
    private Superheros superhero;
    private Locations location;
    
  
    private LocalDate sightingTime;
   
    
     public Sightings() {
    }
    
    public Sightings(int sightingId, int superheroId, int locationId) {
        this.sightingId = sightingId;
        this.superheroId = superheroId;
        this.locationId = locationId;
    }

    public Sightings(int sightingId, int superheroId, int locationId, Superheros superhero, Locations location, LocalDate sightingTime) {
        this.sightingId = sightingId;
        this.superheroId = superheroId;
        this.locationId = locationId;
        this.superhero = superhero;
        this.location = location;
        this.sightingTime = sightingTime;
    }

   

    


    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public int getSuperheroId() {
        return superheroId;
    }

    public void setSuperheroId(int superheroId) {
        this.superheroId = superheroId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Superheros getSuperhero() {
        return superhero;
    }

    public void setSuperhero(Superheros superhero) {
        this.superhero = superhero;
    }

  
    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public LocalDate getSightingTime() {
        return sightingTime;
    }

    public void setSightingTime(LocalDate sightingTime) {
        this.sightingTime = sightingTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.sightingId;
        hash = 53 * hash + this.superheroId;
        hash = 53 * hash + this.locationId;
        hash = 53 * hash + Objects.hashCode(this.superhero);
        hash = 53 * hash + Objects.hashCode(this.location);
        hash = 53 * hash + Objects.hashCode(this.sightingTime);
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
        final Sightings other = (Sightings) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (this.superheroId != other.superheroId) {
            return false;
        }
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.superhero, other.superhero)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.sightingTime, other.sightingTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sightings{" + "sightingId=" + sightingId + ", superheroId=" + superheroId + ", locationId=" + locationId + ", Superhero=" + superhero + ", location=" + location + ", sightingTime=" + sightingTime + '}';
    }
    
}
