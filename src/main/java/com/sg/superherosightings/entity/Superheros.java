/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.entity;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 *
 * @author SHIVALI
 */
public class Superheros {

        
    public int id;
    
     @NotBlank(message = "Name must not be empty.")
     @Size(max = 100, message="Name must be less than 100 characters.")
    private String name;
    
     @NotBlank(message = "Description must not be empty.")
     @Size(max = 500, message="Name must be less than 500 characters.")
    private String description;
    
       private String photos;

    
    private boolean isHero;
   @NotEmpty(message = "Power must not be empty.")
    List<Powers> powers;
   @NotEmpty(message = "Organization must not be empty.")
    List<Organizations> organizations;
    
    public Superheros() {
    }

    public Superheros(int id, String name, String description, boolean isHero) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isHero = isHero;
    }
    
    public Superheros(int id, String name, String description, boolean isHero, List<Powers> powers, List<Organizations> organizations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isHero = isHero;
        this.powers = powers;
        this.organizations = organizations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsHero() {
        return isHero;
    }

    public void setIsHero(boolean isHero) {
        this.isHero = isHero;
    }

    public List<Powers> getPowers() {
        return powers;
    }

    public void setPowers(List<Powers> powers) {
        this.powers = powers;
    }

    public List<Organizations> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organizations> organizations) {
        this.organizations = organizations;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.description);
        hash = 43 * hash + (this.isHero ? 1 : 0);
        hash = 43 * hash + Objects.hashCode(this.powers);
        hash = 43 * hash + Objects.hashCode(this.organizations);
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
        final Superheros other = (Superheros) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.isHero != other.isHero) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Superheros{" + "id=" + id + ", name=" + name + ", description=" + description + ", isHero=" + isHero + ", powers=" + powers + ", organizations=" + organizations + '}';
    }

    
}
