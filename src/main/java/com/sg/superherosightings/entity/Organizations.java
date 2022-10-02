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
public class Organizations {

    public int id;
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 100, message = "Name must be less than 100 characters.")
    private String name;

    @NotBlank(message = "Description must not be empty.")
    @Size(max = 500, message = "Description must be less than 500 characters.")
    private String description;

    @NotBlank(message = "Hotline must not be empty.")
    @Size(max = 14, message = "Hotline  must be less than 14 characters.")
    private String hotline;

    private List<Superheros> members;

    public Organizations() {
    }

    public Organizations(int id, String name, String description, String hotline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hotline = hotline;
    }

    public Organizations(int id, String name, String description, String hotline, List<Superheros> members) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hotline = hotline;
        this.members = members;
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

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public List<Superheros> getMembers() {
        return members;
    }

    public void setMembers(List<Superheros> members) {
        this.members = members;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.hotline);
        hash = 53 * hash + Objects.hashCode(this.members);
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
        final Organizations other = (Organizations) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.hotline, other.hotline)) {
            return false;
        }
        if (!Objects.equals(this.members, other.members)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Organizations{" + "id=" + id + ", name=" + name + ", description=" + description + ", hotline=" + hotline + ", members=" + members + '}';
    }

}
