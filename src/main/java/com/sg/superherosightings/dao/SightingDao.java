/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entity.Sightings;
import java.util.List;

/**
 *
 * @author SHIVALI
 */
public interface SightingDao {
    
    Sightings getSightingById(int id);
    List<Sightings> getAllSightings();
    Sightings addSighting(Sightings sighting);
    void updateSighting(Sightings sighting);
    void deleteSightingById(int id);
    List<Sightings> getLastTenSightings();
    
}
