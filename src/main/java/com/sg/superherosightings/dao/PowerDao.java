/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entity.Powers;
import java.util.List;

/**
 *
 * @author SHIVALI
 */
public interface PowerDao {
    
     Powers getPowerById(int id);
    List<Powers> getAllPowers();
    Powers addPower(Powers power);
    void updatePower(Powers power);
    void deletePowerById(int id);
    
}
