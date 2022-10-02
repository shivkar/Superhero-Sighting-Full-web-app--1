/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entity.Locations;
import java.util.List;

/**
 *
 * @author SHIVALI
 */
public interface LocationDao {
    
    Locations getLocationById(int id);
    List<Locations> getAllLocations();
    Locations addLocation(Locations location);
    void updateLocation(Locations location);
    void deleteLocationById(int id);
    
}
